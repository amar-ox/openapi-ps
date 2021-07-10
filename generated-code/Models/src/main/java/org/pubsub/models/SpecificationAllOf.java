package org.pubsub.models;

import java.util.Objects;
import org.pubsub.models.string;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpecificationAllOf   {
  


  public enum SpecificationEnum {
    

    private String value;

    SpecificationEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private SpecificationEnum specification = null;

  public SpecificationAllOf () {

  }

  public SpecificationAllOf (SpecificationEnum specification) {
    this.specification = specification;
  }

    
  @JsonProperty("specification")
  public SpecificationEnum getSpecification() {
    return specification;
  }
  public void setSpecification(SpecificationEnum specification) {
    this.specification = specification;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SpecificationAllOf SpecificationUnderscoreallOf = (SpecificationAllOf) o;
    return Objects.equals(specification, SpecificationUnderscoreallOf.specification);
  }

  @Override
  public int hashCode() {
    return Objects.hash(specification);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SpecificationAllOf {\n");
    
    sb.append("    specification: ").append(toIndentedString(specification)).append("\n");
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
