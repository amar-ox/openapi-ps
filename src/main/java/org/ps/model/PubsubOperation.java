package org.ps.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.servers.Server;

public class PubsubOperation {
    
    protected List<String> entities = null; 
    protected String summary = null;
    protected String description = null;
    protected ExternalDocumentation externalDocs = null;
    protected String operationId = null;
    protected List<Parameter> parameters = null;
    // protected Map<String, Callback> callbacks = null;
    protected Boolean deprecated = null;
    // protected List<SecurityRequirement> security = null;
    protected List<Server> servers = null;
    protected java.util.Map<String, Object> extensions = null;
    protected String qos = null;

    public PubsubOperation() {}

    public List<String> getEntities() {
        return entities;
    }
    public void setEntities(List<String> entities) {
        this.entities = entities;
    }
    public PubsubOperation entities(List<String> entities) {
        this.entities = entities;
        return this;
    }
    public PubsubOperation addEntitiesItem(String entitiesItem) {
        if (this.entities == null) {
            this.entities = new ArrayList<>();
        }
        this.entities.add(entitiesItem);
        return this;
    }

    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public PubsubOperation summary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public PubsubOperation description(String description) {
        this.description = description;
        return this;
    }

    public ExternalDocumentation getExternalDocs() {
        return externalDocs;
    }
    public void setExternalDocs(ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
    }
    public PubsubOperation externalDocs(ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
        return this;
    }

    public String getOperationId() {
        return operationId;
    }
    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }
    public PubsubOperation operationId(String operationId) {
        this.operationId = operationId;
        return this;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }
    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
    public PubsubOperation parameters(List<Parameter> parameters) {
        this.parameters = parameters;
        return this;
    }
    public PubsubOperation addParametersItem(Parameter parametersItem) {
        if (this.parameters == null) {
            this.parameters = new ArrayList<>();
        }
        this.parameters.add(parametersItem);
        return this;
    }

    public Boolean getDeprecated() {
        return deprecated;
    }
    public void setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
    }
    public PubsubOperation deprecated(Boolean deprecated) {
        this.deprecated = deprecated;
        return this;
    }

    public List<Server> getServers() {
        return servers;
    }
    public void setServers(List<Server> servers) {
        this.servers = servers;
    }
    public PubsubOperation servers(List<Server> servers) {
        this.servers = servers;
        return this;
    }
    public PubsubOperation addServersItem(Server serversItem) {
        if (this.servers == null) {
            this.servers = new ArrayList<>();
        }
        this.servers.add(serversItem);
        return this;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PubsubOperation operation = (PubsubOperation) o;
        return Objects.equals(this.entities, operation.entities) &&
                Objects.equals(this.summary, operation.summary) &&
                Objects.equals(this.description, operation.description) &&
                Objects.equals(this.externalDocs, operation.externalDocs) &&
                Objects.equals(this.operationId, operation.operationId) &&
                Objects.equals(this.parameters, operation.parameters) &&
                Objects.equals(this.deprecated, operation.deprecated) &&
                Objects.equals(this.servers, operation.servers) &&
                Objects.equals(this.qos, operation.qos) &&
                Objects.equals(this.extensions, operation.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entities, summary, description, externalDocs, operationId, parameters, deprecated, servers, extensions, qos);
    }

    public java.util.Map<String, Object> getExtensions() {
        return extensions;
    }

    public void addExtension(String name, Object value) {
        if (name == null || name.isEmpty() || !name.startsWith("x-")) {
            return;
        }
        if (this.extensions == null) {
            this.extensions = new java.util.LinkedHashMap<>();
        }
        this.extensions.put(name, value);
    }

    public void setExtensions(java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
    }

    public PubsubOperation extensions(java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PubsubOperation {\n");

        sb.append("    entities: ").append(toIndentedString(entities)).append("\n");
        sb.append("    summary: ").append(toIndentedString(summary)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    externalDocs: ").append(toIndentedString(externalDocs)).append("\n");
        sb.append("    operationId: ").append(toIndentedString(operationId)).append("\n");
        sb.append("    parameters: ").append(toIndentedString(parameters)).append("\n");
        sb.append("    deprecated: ").append(toIndentedString(deprecated)).append("\n");
        sb.append("    servers: ").append(toIndentedString(servers)).append("\n");
        sb.append("    qos: ").append(toIndentedString(qos)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    protected String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

	public String getQos() {
		return qos;
	}

	public void setQos(String qos) {
		this.qos = qos;
	}
}
