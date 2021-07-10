package org.pubsub.models;

import java.util.Objects;
import org.pubsub.models.List;
import org.pubsub.models.array;
import org.pubsub.models.string;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReceiptAllOf   {
  


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

  public ReceiptAllOf () {

  }

  public ReceiptAllOf (ReceiptEnum receipt, List errors) {
    this.receipt = receipt;
    this.errors = errors;
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
    ReceiptAllOf ReceiptUnderscoreallOf = (ReceiptAllOf) o;
    return Objects.equals(receipt, ReceiptUnderscoreallOf.receipt) &&
        Objects.equals(errors, ReceiptUnderscoreallOf.errors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(receipt, errors);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReceiptAllOf {\n");
    
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
