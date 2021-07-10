package org.pubsub.models;

import java.util.Objects;
import org.pubsub.models.string;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CapabilityAllOf   {
  


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

  public CapabilityAllOf () {

  }

  public CapabilityAllOf (CapabilityEnum capability) {
    this.capability = capability;
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
    CapabilityAllOf CapabilityUnderscoreallOf = (CapabilityAllOf) o;
    return Objects.equals(capability, CapabilityUnderscoreallOf.capability);
  }

  @Override
  public int hashCode() {
    return Objects.hash(capability);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CapabilityAllOf {\n");
    
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
