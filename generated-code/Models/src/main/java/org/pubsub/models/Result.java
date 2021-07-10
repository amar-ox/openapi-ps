package org.pubsub.models;

import java.util.Objects;
import org.pubsub.models.CommonFields;
import org.pubsub.models.DateTime;
import org.pubsub.models.List;
import org.pubsub.models.ResultAllOf;
import org.pubsub.models.array;
import org.pubsub.models.string;

/**
 * Result values as measured by the agent.
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result   {
  
  private String name = null;
  private String type = null;
  private String operationId = null;
  private String when = null;
  private Date ts = null;
  private String target = null;
  private List resultColumns = null;


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

  public Result () {

  }

  public Result (String name, String type, String operationId, String when, Date ts, String target, List resultColumns, ResultEnum result, List resultValues) {
    this.name = name;
    this.type = type;
    this.operationId = operationId;
    this.when = when;
    this.ts = ts;
    this.target = target;
    this.resultColumns = resultColumns;
    this.result = result;
    this.resultValues = resultValues;
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
    Result Result = (Result) o;
    return Objects.equals(name, Result.name) &&
        Objects.equals(type, Result.type) &&
        Objects.equals(operationId, Result.operationId) &&
        Objects.equals(when, Result.when) &&
        Objects.equals(ts, Result.ts) &&
        Objects.equals(target, Result.target) &&
        Objects.equals(resultColumns, Result.resultColumns) &&
        Objects.equals(result, Result.result) &&
        Objects.equals(resultValues, Result.resultValues);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type, operationId, when, ts, target, resultColumns, result, resultValues);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Result {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    operationId: ").append(toIndentedString(operationId)).append("\n");
    sb.append("    when: ").append(toIndentedString(when)).append("\n");
    sb.append("    ts: ").append(toIndentedString(ts)).append("\n");
    sb.append("    target: ").append(toIndentedString(target)).append("\n");
    sb.append("    resultColumns: ").append(toIndentedString(resultColumns)).append("\n");
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
