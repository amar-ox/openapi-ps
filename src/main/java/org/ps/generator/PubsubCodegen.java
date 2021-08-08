package org.ps.generator;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openapitools.codegen.CodegenConfig;
import org.openapitools.codegen.CodegenOperation;
import org.openapitools.codegen.CodegenType;
import org.openapitools.codegen.DefaultCodegen;
import org.openapitools.codegen.utils.StringUtils;
import org.ps.model.PubsubOperation;
import org.ps.model.TopicItem;
import org.ps.model.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.servers.Server;

public class PubsubCodegen extends DefaultCodegen implements CodegenConfig {
	
	private static final Logger LOG = LoggerFactory.getLogger(PubsubCodegen.class);
	   
    protected String apiVersion = "1.0.0";
    protected String projectName = "openapi-pubsub";
    protected Topics topics = null;
    protected List<Server> rootServers = null;
    
    public PubsubCodegen() {
    	super();
    }

    @Override
    public String toApiName(String name) {
        if (name.length() == 0) {
            return "DefaultController";
        }
        return StringUtils.camelize(name);
    }

    @Override
    public String toApiFilename(String name) {
        return toApiName(name);
    }
        
    @Override
    public CodegenType getTag() {
        return CodegenType.CLIENT;
    }
 
    @Override
    public String escapeReservedWord(String name) {
        if(this.reservedWordsMappings().containsKey(name)) {
            return this.reservedWordsMappings().get(name);
        }
        return "_" + name;
    }
    
    @Override
    public void preprocessOpenAPI(OpenAPI openAPI) {
    	topics = extractPubsub(openAPI);
        openAPI.setPaths(fromTopics(topics));
        super.preprocessOpenAPI(openAPI);
        
        rootServers = new ArrayList<Server>();
        rootServers.addAll(parseUrls(openAPI.getServers()));
    }

    @Override
    public Map<String, Object> postProcessOperationsWithModels(Map<String, Object> objs, List<Object> allModels) {
    	 Map<String, Object> operations = (Map<String, Object>) super.postProcessOperationsWithModels(objs, allModels).get("operations");
         List<CodegenOperation> ops = (List<CodegenOperation>) operations.get("operation");
         List<PubsubCodegenOperation> newOps = new ArrayList<>();
         for (CodegenOperation o : ops) {
         	PubsubCodegenOperation psco = new PubsubCodegenOperation(o);
            psco.isSubscribe = o.operationId.startsWith("subscribe");
            psco.qos = (String) o.vendorExtensions.get("_qos");
            newOps.add(psco);
         }
         operations.put("operation", newOps);

         objs.put("servers", fromServers(rootServers));
         return objs;
    }
    
    /* Process Pub-Sub Topics and Operations */
    private Topics extractPubsub(OpenAPI openAPI) {
        Paths paths = openAPI.getPaths();
        Topics topics = new Topics();
        if (paths != null) {
            for(String pathname : paths.keySet()) {
                TopicItem topic = new TopicItem();
                
                // Set topic.servers from path.servers
                PathItem path = paths.get(pathname); 
                if (path.getServers() != null && !path.getServers().isEmpty()) {
            		topic.setServers(parseUrls(path.getServers()));
            	}

                Map<String, Object> extensions = path.getExtensions();
                boolean isPubsub = false;
                if (extensions != null) {
                    // extract Publish operation
                	if (extensions.containsKey("x-ps-publish")) {
                		LinkedHashMap<String,Object> publishMap = (LinkedHashMap<String,Object>) extensions.get("x-ps-publish");
                		ObjectMapper mapper = new ObjectMapper();
                        PubsubOperation publish = mapper.convertValue(publishMap, PubsubOperation.class);
                        topic.setPublish(publish);
                        
                        if (publish.getServers() != null && !publish.getServers().isEmpty()) {
                        	publish.setServers(parseUrls(publish.getServers()));
                        } else {
                        	// use topic servers if defined
                        	if (topic.getServers() != null && !topic.getServers().isEmpty()) {
                        		publish.setServers(topic.getServers());
                        	}
                        }

                        if (publish.getQos() == null) {
                        	publish.setQos("once");
                        }
                        isPubsub = true;
                	}
                    extensions.remove("x-ps-publish");

                    // extract Subscribe operation
                    if (extensions.containsKey("x-ps-subscribe")) {
                		LinkedHashMap<String,Object> subscribeMap = (LinkedHashMap<String,Object>) extensions.get("x-ps-subscribe");
                		ObjectMapper mapper = new ObjectMapper();
                		PubsubOperation subscribe = mapper.convertValue(subscribeMap, PubsubOperation.class);
                        topic.setSubscribe(subscribe);
                        
                        if (subscribe.getServers() != null && !subscribe.getServers().isEmpty()) {
                        	subscribe.setServers(parseUrls(subscribe.getServers()));
                        } else {
                        	// use topic servers if defined
                        	if (topic.getServers() != null && !topic.getServers().isEmpty()) {
                        		subscribe.setServers(topic.getServers());
                        	}
                        }
                        
                        if (subscribe.getQos() == null) {
                        	subscribe.setQos("once");
                        }
                        isPubsub = true;
                	}
                    extensions.remove("x-ps-subscribe");

                    // extract Content
                    if (extensions.containsKey("x-ps-content")) {
                		LinkedHashMap<String,Object> contentMap = (LinkedHashMap<String,Object>) extensions.get("x-ps-content");
                		ObjectMapper mapper = new ObjectMapper();
                        Content content = mapper.convertValue(contentMap, Content.class);
                        topic.setContent(content);
                	}
                    extensions.remove("x-ps-content");
                }
                
                // add if path is a Pub-Sub topic
                if (isPubsub) {
                    topic.setSummary(path.getSummary());
                    topic.setDescription(path.getDescription());
                    topic.setServers(path.getServers());
                    topic.setParameters(path.getParameters());
                    topic.setExtensions(extensions);
                    topics.addTopicItem(pathname, topic);
                }
            }
        }
        return topics;
    }
    
    /*
     * visit Topics, create Paths with Operations
     * uses mapping between publish/subscribe and OpenAPI Operation
     */
    private Paths fromTopics(Topics topics) {
         Paths paths = new Paths();
         for (String topic : topics.keySet()) {
         	TopicItem topicItem = topics.get(topic);
         	PathItem pi = new PathItem();
      
         	if (topicItem.getPublish() != null) {
         		Operation op = new Operation();
         		String opId = "publish" + StringUtils.camelize(topic.substring(1));
         		op.setOperationId(opId);
         		op.setTags(topicItem.getPublish().getEntities());
         		op.setServers(topicItem.getPublish().getServers());
         		op.setExtensions(Collections.singletonMap("_qos", topicItem.getPublish().getQos()));
         		RequestBody rb = new RequestBody();
         		rb.setContent(topicItem.getContent());
         		op.setRequestBody(rb);
         		pi.setPut(op);
         	}
         	if (topicItem.getSubscribe() != null) {
         		Operation op = new Operation();
         		String opId = "subscribeTo" + StringUtils.camelize(topic.substring(1));
         		op.setOperationId(opId);
         		op.setTags(topicItem.getSubscribe().getEntities());
         		op.setServers(topicItem.getSubscribe().getServers());
         		op.setExtensions(Collections.singletonMap("_qos", topicItem.getSubscribe().getQos()));
         		RequestBody rb = new RequestBody();
         		rb.setContent(topicItem.getContent());
         		op.setRequestBody(rb);
         		pi.setGet(op);
         	}
         	paths.addPathItem(topic, pi);
         }
         return paths;
     }

    public Topics getTopics() {
        return topics;
    }

    public void setTopics(Topics topics) {
        this.topics = topics;
    }
    
    @Override
    public String escapeUnsafeCharacters(String input) {
        return input.replace("*/", "*_/").replace("/*", "/_*");
    }

    @Override
    public String escapeQuotationMark(String input) {
        // remove " to avoid code injection
        return input.replace("\"", "");
    }
    
    private List<Server> parseUrls(List<Server> servers) {
    	List<Server> rs = new ArrayList<Server>();
    	rs.addAll(servers);
    	for (Server s : rs) {
    		URL aURL;
			try {
				aURL = new URL(s.getUrl());
				s.setUrl(aURL.getHost() + ":" + aURL.getPort());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
    	}
    	return rs;
    }
}
