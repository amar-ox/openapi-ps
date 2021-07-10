package org.pubsub.models;

import java.util.Objects;
import org.pubsub.models.CapabilityAllOf;
import org.pubsub.models.CommonFields;
import org.pubsub.models.DateTime;
import org.pubsub.models.List;
import org.pubsub.models.array;
import org.pubsub.models.string;

/**
 * A capability provided by the agent.
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Capability   {
  
  private String name = null;
  private String type = null;
  private String operationId = null;
  private String when = null;
  private Date ts = null;
  private String target = null;
  private List resultColumns = null;


  public enum CapabilityEnum {
    

    private String value;

    CapabilityEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private CapabilityEnum capability = null;

  public Capability () {

  }

  public Capability (String name, String type, String operationId, String when, Date ts, String target, List resultColumns, CapabilityEnum capability) {
    this.name = name;
    this.type = type;
    this.operationId = operationId;
    this.when = when;
    this.ts = ts;
    this.target = target;
    this.resultColumns = resultColumns;
    this.capability = capability;
  }

    
  @JsonProperty("name")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

    
  @JsonProperty("type")
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

    
  @JsonProperty("operationId")
  public String getOperationId() {
    return operationId;
  }
  public void setOperationId(String operationId) {
    this.operationId = operationId;
  }

    
  @JsonProperty("when")
  public String getWhen() {
    return when;
  }
  public void setWhen(String when) {
    this.when = when;
  }

    
  @JsonProperty("ts")
  public Date getTs() {
    return ts;
  }
  public void setTs(Date ts) {
    this.ts = ts;
  }

    
  @JsonProperty("target")
  public String getTarget() {
    return target;
  }
  public void setTarget(String target) {
    this.target = target;
  }

    
  @JsonProperty("resultColumns")
  public List getResultColumns() {
    return resultColumns;
  }
  public void setResultColumns(List resultColumns) {
    this.resultColumns = resultColumns;
  }

    
  @JsonProperty("capability")
  public CapabilityEnum getCapability() {
    return capability;
  }
  public void setCapability(CapabilityEnum capability) {
    this.capability = capability;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Capability Capability = (Capability) o;
    return Objects.equals(name, Capability.name) &&
        Objects.equals(type, Capability.type) &&
        Objects.equals(operationId, Capability.operationId) &&
        Objects.equals(when, Capability.when) &&
        Objects.equals(ts, Capability.ts) &&
        Objects.equals(target, Capability.target) &&
        Objects.equals(resultColumns, Capability.resultColumns) &&
        Objects.equals(capability, Capability.capability);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type, operationId, when, ts, target, resultColumns, capability);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Capability {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    operationId: ").append(toIndentedString(operationId)).append("\n");
    sb.append("    when: ").append(toIndentedString(when)).append("\n");
    sb.append("    ts: ").append(toIndentedString(ts)).append("\n");
    sb.append("    target: ").append(toIndentedString(target)).append("\n");
    sb.append("    resultColumns: ").append(toIndentedString(resultColumns)).append("\n");
    sb.append("    capability: ").append(toIndentedString(capability)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
