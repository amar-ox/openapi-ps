package org.ps.generator;

import java.util.LinkedHashMap;
import java.util.Map;

import org.ps.model.Publish;
import org.ps.model.Subscribe;
import org.ps.model.TopicItem;
import org.ps.model.Topics;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.media.Content;

public class TopicsProcessor {

    private Topics topics = null;

    public TopicsProcessor(OpenAPI openAPI) {
    	processTopics(openAPI);
    }
    
    private void processTopics(OpenAPI openAPI) {
        Paths paths = openAPI.getPaths();
        if(paths != null) {
            topics = new Topics();
            for(String pathname : paths.keySet()) {
                TopicItem topic = new TopicItem();
                PathItem path = paths.get(pathname);                
                Map<String, Object> extensions = path.getExtensions();
                boolean isPubsub = false;
                if (extensions != null) {
                    // extract Publish operation
                	if (extensions.containsKey("x-ps-publish")) {
                		LinkedHashMap<String,Object> publishMap = (LinkedHashMap<String,Object>) extensions.get("x-ps-publish");
                		ObjectMapper mapper = new ObjectMapper();
                        Publish publish = mapper.convertValue(publishMap, Publish.class);
                        topic.setPublish(publish);
                        isPubsub = true;
                	}
                    extensions.remove("x-ps-publish");

                    // extract Subscribe operation
                    if (extensions.containsKey("x-ps-subscribe")) {
                		LinkedHashMap<String,Object> subscribeMap = (LinkedHashMap<String,Object>) extensions.get("x-ps-subscribe");
                		ObjectMapper mapper = new ObjectMapper();
                        Subscribe subscribe = mapper.convertValue(subscribeMap, Subscribe.class);
                        topic.setSubscribe(subscribe);
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
    }

    public Topics getTopics() {
        return topics;
    }

    public void setTopics(Topics topics) {
        this.topics = topics;
    }
}