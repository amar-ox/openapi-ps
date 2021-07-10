package org.ps.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.openapitools.codegen.CodegenConfig;
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

public class PubsubRiotCodegen extends DefaultCodegen implements CodegenConfig {

    private static final Logger LOG = LoggerFactory.getLogger(PubsubRiotCodegen.class);
   
    protected String apiVersion = "1.0.0";
    protected String projectName = "openapi-pubsub";

    @Override
    public String apiFilename(String templateName, String tag) {
        String suffix = apiTemplateFiles().get(templateName);
        // if (suffix.startsWith("main")) {
        	return outputFolder
            		+ File.separator + tag
            		+ File.separator + suffix;
        // }
    }
        
    @Override
    public CodegenType getTag() {
        return CodegenType.SERVER;
    }

    @Override
    public String getName() {
        return "pubsub-riot";
    }

    @Override
    public String getHelp() {
        return "Generates a pub-sub library for RIOT-OS with MQTT.";
    }
    
    @Override
    public String escapeReservedWord(String name) {
        if(this.reservedWordsMappings().containsKey(name)) {
            return this.reservedWordsMappings().get(name);
        }
        return "_" + name;
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
    public String escapeUnsafeCharacters(String input) {
        return input.replace("*/", "*_/").replace("/*", "/_*");
    }

    @Override
    public String escapeQuotationMark(String input) {
        // remove " to avoid code injection
        return input.replace("\"", "");
    }
}