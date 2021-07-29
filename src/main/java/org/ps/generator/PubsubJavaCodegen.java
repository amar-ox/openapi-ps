package org.ps.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openapitools.codegen.CodegenConfig;
import org.openapitools.codegen.CodegenOperation;
import org.openapitools.codegen.CodegenType;
import org.openapitools.codegen.DefaultCodegen;
import org.openapitools.codegen.SupportingFile;
import org.openapitools.codegen.utils.StringUtils;
import org.ps.model.TopicItem;
import org.ps.model.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.parameters.RequestBody;

public class PubsubJavaCodegen extends DefaultCodegen implements CodegenConfig {

    private static final Logger LOG = LoggerFactory.getLogger(PubsubJavaCodegen.class);
   
    protected String apiVersion = "1.0.0";
    protected String projectName = "openapi-pubsub";
    
    @Override
    public String apiPackage() {
        return "org.pubsub.controller";
    }
    
    @Override
    public String modelPackage() {
        return "org.pubsub.models";
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
    public String apiFileFolder() {
        return "src" + File.separator + "main" + File.separator + "java";
    }
    
    @Override
    public String modelFileFolder() {
        return "src" + File.separator + "main" + File.separator + "java";
    }

    @Override
    public String apiFilename(String templateName, String tag) {
        String suffix = apiTemplateFiles().get(templateName);
        if (suffix.endsWith(".xml") || suffix.endsWith(".md")) {
        	return outputFolder 
        			+ File.separator + tag
        			+ File.separator + suffix;
        }
        if (suffix.startsWith("Main")) {
        	return outputFolder
            		+ File.separator + tag
            		+ File.separator + apiFileFolder() 
            		+ File.separator + apiPackage().replace('.', File.separatorChar)
            		+ File.separator + suffix;
        }
        return outputFolder
        		+ File.separator + tag
        		+ File.separator + apiFileFolder() 
        		+ File.separator + apiPackage().replace('.', File.separatorChar)
        		+ File.separator + toApiFilename(tag) + suffix;
    }
    
    @Override
    public String modelFilename(String templateName, String modelName) {
    	String suffix = modelTemplateFiles().get(templateName);
        return outputFolder
        		+ File.separator + modelPackage
        		+ File.separator + modelFileFolder() 
        		+ File.separator + modelPackage().replace('.', File.separatorChar)
        		+ File.separator + StringUtils.camelize(modelName) + suffix;
    }
        
    @Override
    public CodegenType getTag() {
        return CodegenType.SERVER;
    }

    @Override
    public String getName() {
        return "pubsub-java";
    }

    @Override
    public String getHelp() {
        return "Generates a pub-sub library for Java with AMQP.";
    }
    
    @Override
    public String escapeReservedWord(String name) {
        if(this.reservedWordsMappings().containsKey(name)) {
            return this.reservedWordsMappings().get(name);
        }
        return "_" + name;
    }

    public PubsubJavaCodegen() {
        super();
        
        // set the output folder here
        outputFolder = "generated-code" + File.separator + "pubsub-java";
        
        /*
         * Models.  You can write model files using the modelTemplateFiles map.
         * if you want to create one template for file, you can do so here.
         * for multiple files for model, just put another entry in the `modelTemplateFiles` with
         * a different extension
         */
        modelTemplateFiles.put("model/model.mustache", ".java");
        
        /*
         * Api classes.  You can write classes for each Api file with the apiTemplateFiles map.
         * as with models, add multiple entries with different extensions for multiple files per
         * class
         */
        apiTemplateFiles.put("api/api.mustache", ".java");
        apiTemplateFiles.put("api/Main.mustache", "Main.java");
        apiTemplateFiles.put("api/callback.mustache", "PubsubCallback.java");
        apiTemplateFiles.put("api/README.mustache", "README.md");
        apiTemplateFiles.put("api/pom.mustache", "pom.xml");
        
        /*
         * Template Location.  This is the location which templates will be read from.  The generator
         * will use the resource stream to attempt to read the templates.
         */
        embeddedTemplateDir = templateDir = "java";

        modelPackage = "Models";
        
        supportingFiles.add(new SupportingFile("model/README.mustache", modelPackage, "README.md"));
        supportingFiles.add(new SupportingFile("model/pom.mustache", modelPackage, "pom.xml"));
        
        /*
         * Additional Properties.  These values can be passed to the templates and
         * are available in models, apis, and supporting files
         */
        additionalProperties.put("apiVersion", apiVersion);
        additionalProperties.put("projectName", projectName);
        
        /*
         * Reserved words.  Override this with reserved words specific to your language
         */
        setReservedWordsLowerCase(
        		Arrays.asList(
                        "break", "case", "class", "catch", "const", "continue",
                        "default", "delete", "do", "else", "extends", "finally",
                        "for", "function", "if", "import", "in", "instanceof", "new",
                        "return", "super", "switch", "this", "throw", "try",
                        "void", "while", "private", "public", "protected"
                )
        );
    }
    
    @Override
    public void preprocessOpenAPI(OpenAPI openAPI) {
        super.preprocessOpenAPI(openAPI);

        TopicsProcessor tp = new TopicsProcessor(openAPI);
        System.out.println(tp.getTopics().toString());
        
        openAPI.setPaths(tp.getPathOpsFromTopics());
    }
    
    @Override
    public Map<String, Object> postProcessSupportingFileData(Map<String, Object> objs) {
        Map<String, Object> ops = (Map<String, Object>) objs.get("operations");    
        try {
            FileWriter myWriter = new FileWriter("openapi-objs.log");
            myWriter.write(""+objs);
            myWriter.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        return super.postProcessSupportingFileData(objs);
    }

    @Override
    public Map<String, Object> postProcessOperationsWithModels(Map<String, Object> objs, List<Object> allModels) {
        Map<String, Object> operations = (Map<String, Object>) super.postProcessOperationsWithModels(objs, allModels).get("operations");
        List<CodegenOperation> ops = (List<CodegenOperation>) operations.get("operation");
        List<PubsubCodegenOperation> newOps = new ArrayList<>();
        for (CodegenOperation o : ops) {
        	PubsubCodegenOperation psco = new PubsubCodegenOperation(o);
            psco.isSubscribe = o.operationId.startsWith("subscribe");
            newOps.add(psco);
        }
        operations.put("operation", newOps);
        return objs;
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
}