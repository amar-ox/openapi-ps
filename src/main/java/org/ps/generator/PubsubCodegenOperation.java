package org.ps.generator;

import org.openapitools.codegen.CodegenOperation;

class PubsubCodegenOperation extends CodegenOperation {
    public boolean isSubscribe;
    public String qos;

    public PubsubCodegenOperation(CodegenOperation o) {
        super();

        // Copy all fields of CodegenOperation
        this.responseHeaders.addAll(o.responseHeaders);
        this.hasAuthMethods = o.hasAuthMethods;
        this.hasConsumes = o.hasConsumes;
        this.hasProduces = o.hasProduces;
        this.hasParams = o.hasParams;
        this.hasOptionalParams = o.hasOptionalParams;
        this.returnTypeIsPrimitive = o.returnTypeIsPrimitive;
        this.returnSimpleType = o.returnSimpleType;
        this.subresourceOperation = o.subresourceOperation;
        this.isMap = o.isMap;
        this.isArray = o.isArray;
        this.isMultipart = o.isMultipart;
        this.isResponseBinary = o.isResponseBinary;
        this.hasReference = o.hasReference;
        this.isRestfulIndex = o.isRestfulIndex;
        this.isRestfulShow = o.isRestfulShow;
        this.isRestfulCreate = o.isRestfulCreate;
        this.isRestfulUpdate = o.isRestfulUpdate;
        this.isRestfulDestroy = o.isRestfulDestroy;
        this.isRestful = o.isRestful;
        this.path = o.path;
        this.operationId = o.operationId;
        this.returnType = o.returnType;
        this.httpMethod = o.httpMethod;
        this.returnBaseType = o.returnBaseType;
        this.returnContainer = o.returnContainer;
        this.summary = o.summary;
        this.unescapedNotes = o.unescapedNotes;
        this.notes = o.notes;
        this.baseName = o.baseName;
        this.defaultResponse = o.defaultResponse;
        this.discriminator = o.discriminator;
        this.consumes = o.consumes;
        this.produces = o.produces;
        this.bodyParam = o.bodyParam;
        this.allParams = o.allParams;
        this.bodyParams = o.bodyParams;
        this.pathParams = o.pathParams;
        this.queryParams = o.queryParams;
        this.headerParams = o.headerParams;
        this.formParams = o.formParams;
        this.requiredParams = o.requiredParams;
        this.optionalParams = o.optionalParams;
        this.authMethods = o.authMethods;
        this.tags = o.tags;
        this.servers = o.servers;
        this.responses = o.responses;
        this.imports = o.imports;
        this.examples = o.examples;
        this.externalDocs = o.externalDocs;
        this.vendorExtensions = o.vendorExtensions;
        this.nickname = o.nickname;
        this.operationIdLowerCase = o.operationIdLowerCase;
        this.operationIdCamelCase = o.operationIdCamelCase;
    }
}