package org.pubsub.models;

import java.util.Objects;
import org.pubsub.models.List;
import org.pubsub.models.array;
import org.pubsub.models.string;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultAllOf   {
  


  public enum ResultEnum {
    

    private String value;

    ResultEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private ResultEnum result = null;
  private List resultValues = null;

  public ResultAllOf () {

  }

  public ResultAllOf (ResultEnum result, List resultValues) {
    this.result = result;
    this.resultValues = resultValues;
  }

    
  @JsonProperty("result")
  public ResultEnum getResult() {
    return result;
  }
  public void setResult(ResultEnum result) {
    this.result = result;
  }

    
  @JsonProperty("resultValues")
  public List getResultValues() {
    return resultValues;
  }
  public void setResultValues(List resultValues) {
    this.resultValues = resultValues;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResultAllOf ResultUnderscoreallOf = (ResultAllOf) o;
    return Objects.equals(result, ResultUnderscoreallOf.result) &&
        Objects.equals(resultValues, ResultUnderscoreallOf.resultValues);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result, resultValues);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResultAllOf {\n");
    
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    resultValues: ").append(toIndentedString(resultValues)).append("\n");
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
