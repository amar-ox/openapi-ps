package org.pubsub.models;

import java.util.Objects;
import org.pubsub.models.CommonFields;
import org.pubsub.models.DateTime;
import org.pubsub.models.List;
import org.pubsub.models.ReceiptAllOf;
import org.pubsub.models.array;
import org.pubsub.models.string;

/**
 * Response to either a specification or an interrupt.
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Receipt   {
  
  private String name = null;
  private String type = null;
  private String operationId = null;
  private String when = null;
  private Date ts = null;
  private String target = null;
  private List resultColumns = null;


  public enum ReceiptEnum {
    

    private String value;

    ReceiptEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private ReceiptEnum receipt = null;
  private List errors = null;

  public Receipt () {

  }

  public Receipt (String name, String type, String operationId, String when, Date ts, String target, List resultColumns, ReceiptEnum receipt, List errors) {
    this.name = name;
    this.type = type;
    this.operationId = operationId;
    this.when = when;
    this.ts = ts;
    this.target = target;
    this.resultColumns = resultColumns;
    this.receipt = receipt;
    this.errors = errors;
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

    
  @JsonProperty("receipt")
  public ReceiptEnum getReceipt() {
    return receipt;
  }
  public void setReceipt(ReceiptEnum receipt) {
    this.receipt = receipt;
  }

    
  @JsonProperty("errors")
  public List getErrors() {
    return errors;
  }
  public void setErrors(List errors) {
    this.errors = errors;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Receipt Receipt = (Receipt) o;
    return Objects.equals(name, Receipt.name) &&
        Objects.equals(type, Receipt.type) &&
        Objects.equals(operationId, Receipt.operationId) &&
        Objects.equals(when, Receipt.when) &&
        Objects.equals(ts, Receipt.ts) &&
        Objects.equals(target, Receipt.target) &&
        Objects.equals(resultColumns, Receipt.resultColumns) &&
        Objects.equals(receipt, Receipt.receipt) &&
        Objects.equals(errors, Receipt.errors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type, operationId, when, ts, target, resultColumns, receipt, errors);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Receipt {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    operationId: ").append(toIndentedString(operationId)).append("\n");
    sb.append("    when: ").append(toIndentedString(when)).append("\n");
    sb.append("    ts: ").append(toIndentedString(ts)).append("\n");
    sb.append("    target: ").append(toIndentedString(target)).append("\n");
    sb.append("    resultColumns: ").append(toIndentedString(resultColumns)).append("\n");
    sb.append("    receipt: ").append(toIndentedString(receipt)).append("\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
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
