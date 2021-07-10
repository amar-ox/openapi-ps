package org.pubsub.models;

import java.util.Objects;
import org.pubsub.models.string;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InterruptAllOf   {
  


  public enum InterruptEnum {
    

    private String value;

    InterruptEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private InterruptEnum interrupt = null;

  public InterruptAllOf () {

  }

  public InterruptAllOf (InterruptEnum interrupt) {
    this.interrupt = interrupt;
  }

    
  @JsonProperty("interrupt")
  public InterruptEnum getInterrupt() {
    return interrupt;
  }
  public void setInterrupt(InterruptEnum interrupt) {
    this.interrupt = interrupt;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InterruptAllOf InterruptUnderscoreallOf = (InterruptAllOf) o;
    return Objects.equals(interrupt, InterruptUnderscoreallOf.interrupt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(interrupt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InterruptAllOf {\n");
    
    sb.append("    interrupt: ").append(toIndentedString(interrupt)).append("\n");
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
