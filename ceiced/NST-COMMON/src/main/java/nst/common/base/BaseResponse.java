package nst.common.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import lombok.Data;
import nst.common.error.AppException;

@Data
public class BaseResponse extends BaseDTO {

  private static final long serialVersionUID = 1l;

  @JsonProperty(value = "success")
  private boolean success;

  @JsonProperty(value = "statusCode")
  private String statusCode;

  @JsonProperty(value = "message")
  private String message;

  @JsonProperty(value = "list")
  private boolean list;

  @JsonProperty(value = "totalRecords")
  private Long totalRecords;

  @JsonProperty(value = "data")
  private Object data;

  @JsonProperty(value = "error")
  private ResponseError error;

  @JsonProperty(value = "errors")
  private List<ResponseError> errors;

  private BaseResponse(boolean success, Long totalRecords, List data) {
    this.success = success;
    this.totalRecords = totalRecords;
    this.data = data;
    if (data != null) {
      this.list = isList(data);
    }
  }

  private BaseResponse(boolean success, Object data) {
    this.success = success;
    this.data = data;
    if (data != null) {
      this.list = isList(data);
    }
  }

  @JsonIgnore
  private static boolean isList(Object data) {
    return (data instanceof Collection) ||
        (data instanceof Map) ||
        data.getClass().isArray();
  }

  private BaseResponse() {
    this.success = false;
  }

  public static BaseResponse createSuccess(Object data) {
    return new BaseResponse(true, data);
  }

  public static BaseResponse createSuccess(PageableDOT pageableDOT) {
    return new BaseResponse(true, pageableDOT.getCount(), pageableDOT.getList());
  }

  public static BaseResponse createSuccess() {
    return new BaseResponse(true, null);
  }

  public static BaseResponse createFail(ResponseError error) {
    BaseResponse baseResponse = new BaseResponse(false, null);
    baseResponse.setStatusCode(error.getCode());
    baseResponse.setError(error);
    return baseResponse;
  }

  public static BaseResponse createForErrors(String statusCode) {
    BaseResponse baseResponse = new BaseResponse(false, null);
    baseResponse.setStatusCode(statusCode);
    baseResponse.list = true;
    return baseResponse;
  }

  public static BaseResponse createFail(AppException exception) {
    return createFail(exception.getResponseError());
  }

  public static BaseResponse createFail(String errorCode, ResponseError error) {
    BaseResponse baseResponse = new BaseResponse();
    baseResponse.setStatusCode(errorCode);
    baseResponse.setError(error);
    return baseResponse;
  }

  public void addError(ResponseError error) {
    if (errors == null) {
      errors = new ArrayList<>();
    }
    errors.add(error);
  }
}