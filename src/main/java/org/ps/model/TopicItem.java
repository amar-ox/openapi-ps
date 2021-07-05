package org.ps.model;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.servers.Server;

public class TopicItem {
    
    private Publish publish = null;
    private Subscribe subscribe = null;
    private Content content = null;
    private String summary = null;
    private String description = null;
    private List<Server> servers = null;
    private List<Parameter> parameters = null;
    // private String $ref = null;
    private java.util.Map<String, Object> extensions = null;

    public TopicItem() {}

    public void setPublish(Publish publish) {
        this.publish = publish;
    }
    public Publish getPublish() {
        return publish;
    }
    public TopicItem publish(Publish publish) {
        this.publish = publish;
        return this;
    }

    public Subscribe getSubscribe() {
        return subscribe;
    }
    public void setSubscribe(Subscribe subscribe) {
        this.subscribe = subscribe;
    }
    public TopicItem subscribe(Subscribe subscribe) {
        this.subscribe = subscribe;
        return this;
    }

    public void setContent(Content content) {
        this.content = content;
    }
    public Content getContent() {
        return content;
    }
    public TopicItem content(Content content) {
        this.content = content;
        return this;
    }

    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public TopicItem summary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public TopicItem description(String description) {
        this.description = description;
        return this;
    }

    public List<Server> getServers() {
        return servers;
    }
    public void setServers(List<Server> servers) {
        this.servers = servers;
    }
    public TopicItem servers(List<Server> servers) {
        this.servers = servers;
        return this;
    }
    public TopicItem addServersItem(Server serversItem) {
        if (this.servers == null) {
            this.servers = new ArrayList<>();
        }
        this.servers.add(serversItem);
        return this;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }
    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
    public TopicItem parameters(List<Parameter> parameters) {
        this.parameters = parameters;
        return this;
    }
    public TopicItem addParametersItem(Parameter parametersItem) {
        if (this.parameters == null) {
            this.parameters = new ArrayList<>();
        }
        this.parameters.add(parametersItem);
        return this;
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
    public TopicItem extensions(java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    /* public String get$ref() {
        return $ref;
    }

    public void set$ref(String $ref) {
        this.$ref = $ref;
    }

    public TopicItem $ref(String $ref) {
        set$ref($ref);
        return this;
    } */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TopicItem)) {
            return false;
        }

        TopicItem topicItem = (TopicItem) o;

        if (summary != null ? !summary.equals(topicItem.summary) : topicItem.summary != null) {
            return false;
        }
        if (description != null ? !description.equals(topicItem.description) : topicItem.description != null) {
            return false;
        }
        if (publish != null ? !publish.equals(topicItem.publish) : topicItem.publish != null) {
            return false;
        }
        if (subscribe != null ? !subscribe.equals(topicItem.subscribe) : topicItem.subscribe != null) {
            return false;
        }
        if (content != null ? !content.equals(topicItem.content) : topicItem.content != null) {
            return false;
        }
        if (servers != null ? !servers.equals(topicItem.servers) : topicItem.servers != null) {
            return false;
        }
        if (parameters != null ? !parameters.equals(topicItem.parameters) : topicItem.parameters != null) {
            return false;
        }
        return extensions != null ? extensions.equals(topicItem.extensions) : topicItem.extensions == null;
    }

    @Override
    public int hashCode() {
        int result = summary != null ? summary.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (publish != null ? publish.hashCode() : 0);
        result = 31 * result + (subscribe != null ? subscribe.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (servers != null ? servers.hashCode() : 0);
        result = 31 * result + (parameters != null ? parameters.hashCode() : 0);
        result = 31 * result + (extensions != null ? extensions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TopicItem {\n");
        sb.append("    summary: ").append(toIndentedString(summary)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    publish: ").append(toIndentedString(publish)).append("\n");
        sb.append("    subscribe: ").append(toIndentedString(subscribe)).append("\n");
        sb.append("    content: ").append(toIndentedString(content)).append("\n");
        sb.append("    servers: ").append(toIndentedString(servers)).append("\n");
        sb.append("    parameters: ").append(toIndentedString(parameters)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}