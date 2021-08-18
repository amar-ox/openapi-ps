package org.ps.generator;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.v3.oas.models.OpenAPI;

public class PubsubRiotCodegen extends PubsubCodegen {

    private static final Logger LOG = LoggerFactory.getLogger(PubsubRiotCodegen.class);

    @Override
    public String apiFilename(String templateName, String tag) {
        String suffix = apiTemplateFiles().get(templateName);
        	return outputFolder
            		+ File.separator + tag
            		+ File.separator + suffix;
    }

    @Override
    public String getName() {
        return "pubsub-riot";
    }

    @Override
    public String getHelp() {
        return "Generates a pub-sub library for RIOT-OS with MQTT.";
    }

    public PubsubRiotCodegen() {
        super();
        
        // set the output folder here
        outputFolder = "generated-code" + File.separator + "pubsub-riot";
        
        /*
         * Models.  You can write model files using the modelTemplateFiles map.
         * if you want to create one template for file, you can do so here.
         * for multiple files for model, just put another entry in the `modelTemplateFiles` with
         * a different extension
         */
        modelTemplateFiles.clear();
        
        /*
         * Api classes.  You can write classes for each Api file with the apiTemplateFiles map.
         * as with models, add multiple entries with different extensions for multiple files per
         * class
         */
        apiTemplateFiles.put("main.mustache", "main.c");
        apiTemplateFiles.put("Makefile.mustache", "Makefile");
        apiTemplateFiles.put("README.mustache", "README.md");
        
        /*
         * Template Location.  This is the location which templates will be read from.  The generator
         * will use the resource stream to attempt to read the templates.
         */
        embeddedTemplateDir = templateDir = "riot";

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
    }
    
    @Override
    public Map<String, Object> postProcessOperationsWithModels(Map<String, Object> objs, List<Object> allModels) {
    	Map<String, Object> nObjs = (Map<String, Object>) super.postProcessOperationsWithModels(objs, allModels);
    	Map<String, Object> operations = (Map<String, Object>) nObjs.get("operations");
    	List<PubsubCodegenOperation> ops = (List<PubsubCodegenOperation>) operations.get("operation");
        for (PubsubCodegenOperation o : ops) {
        	String mqttQos = "";
        	switch (o.qos) {
                case "at-least-once":
                	mqttQos = "1";
                    break;
                case "only-once":
                	mqttQos = "2";
                    break;
                default:
                	mqttQos = "0";
        	}
        	o.qos = mqttQos;
        }
        operations.put("operation", ops);
        return nObjs;
    }
}