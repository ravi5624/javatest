package nst.common.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import nst.common.base.ResponseError;

@Data
public class DataValidationException extends RuntimeException {

  private static final long serialVersionUID = 1l;

  @JsonProperty(value = "errors")
  private List<ResponseError> errors;

  private DataValidationException(List<ResponseError> errors) {
    this.errors = errors;
  }

  public static DataValidationException create(List<ResponseError> allErrors) {
    return new DataValidationException(allErrors);
  }

  public void addError(ResponseError error) {
    if (errors == null) {
      errors = new ArrayList<>();
    }
    errors.add(error);
  }
}