package nst.common.error;

import lombok.Data;
import nst.common.base.ResponseError;

@Data
public class AppException extends RuntimeException {

  private static final long serialVersionUID = 1l;

  private String errorCode;
  private String propertyPath;
  private String messageCode;
  private String message;
  private String[] placeHolders;

  public AppException(String errorCode, String propertyPath, String messageCode, String message) {
    super(message);
    this.errorCode = errorCode;
    this.propertyPath = propertyPath;
    this.messageCode = messageCode;
    this.message = message;
  }

  public AppException(String errorCode, String propertyPath, String messageCode, String message,
      String[] placeHolders) {
    this(errorCode, propertyPath, messageCode, message);
    this.placeHolders = placeHolders;
  }

  public static AppException create(String errorCode, String propertyPath, String messageCode,
      String message) {
    return new AppException(errorCode, propertyPath, messageCode, message);
  }

  public static AppException createDynamic(String errorCode, String... placeHolders) {
    return new AppException(errorCode, null, null, null, placeHolders);
  }

  public static AppException createDynamic(String errorCode, String messageCode,
      String... placeHolders) {
    return new AppException(errorCode, null, messageCode, null, placeHolders);
  }

  public static AppException create(String errorCode, String propertyPath, String messageCode) {
    return new AppException(errorCode, propertyPath, messageCode, messageCode);
  }

  public static AppException create(String errorCode, String propertyPath) {
    return new AppException(errorCode, propertyPath, null, null);
  }

  public static AppException create(String errorCode) {
    return new AppException(errorCode, null, null, null);
  }

  public static AppException createWithMessage(String errorCode, String message) {
    return new AppException(errorCode, null, null, message);
  }

  public static AppException createWithMessageCode(String errorCode, String messageCode) {
    return new AppException(errorCode, null, messageCode, null);
  }

  public ResponseError getResponseError() {
    return ResponseError.create(errorCode, propertyPath, messageCode, message);
  }
}