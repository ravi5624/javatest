package nst.common.base;

import lombok.Data;
import nst.manager.ErrorMessage;
import org.springframework.util.StringUtils;

@Data
public class ResponseError extends BaseDTO {

  private static final long serialVersionUID = -2510331497709421956L;

  private String code;
  private String property;
  private String messageCode;
  private String message;

  public ResponseError(String code) {
    this.code = code;
  }

  public ResponseError(String code, String property) {
    this(code);
    this.property = property;
  }

  public ResponseError(String code, String property, String messageCode, String message) {
    this(code, property);
    this.messageCode = messageCode;
    this.message = ErrorMessage.getMessage(messageCode);
    if (StringUtils.isEmpty(this.message)) {
      this.message = StringUtils.isEmpty(message) ? messageCode : message;
    }
  }

  public static ResponseError create(String errorCode, String propertyPath, String messageCode,
      String message) {
    return new ResponseError(errorCode, propertyPath, messageCode, message);
  }

  public static ResponseError create(String errorCode, String propertyPath, String message) {
    return new ResponseError(errorCode, propertyPath, message, message);
  }

  public static ResponseError create(String errorCode, String propertyPath) {
    return new ResponseError(errorCode, propertyPath);
  }

  public static ResponseError create(String errorCode) {
    return new ResponseError(errorCode);
  }

  public ResponseError setMessage(String dataFormat, Object... data) {
    this.message = String.format(dataFormat, data);
    return this;
  }
}
