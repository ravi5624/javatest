package nst.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;
import lombok.Data;
import nst.common.base.BaseDTO;
import nst.common.error.AppException;
import nst.kernal.SystemConstants;
import nst.kernal.SystemConstants.Rest;
import nst.util.ExternalServiceUtil;
import org.springframework.util.StringUtils;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class APIRequestDTO extends BaseDTO {

  private String url;
  private String method;
  private String body;
  private String contentType;
  private String sender;
  private String service;
  private Integer timeOut;
  private Map<String, String> params;
  private Map<String, String> headers;

  private APIRequestDTO(String serviceUrl, String httpMethod) {
    if (StringUtils.isEmpty(serviceUrl)) {
      throw AppException.create("INVALID_URL");
    }

    if (StringUtils.isEmpty(httpMethod) || !(Rest.METHOD_PUT.equalsIgnoreCase(httpMethod) || Rest.METHOD_GET.equalsIgnoreCase(httpMethod) || Rest.METHOD_POST
        .equalsIgnoreCase(httpMethod))) {
      throw AppException.create("INVALID_API_METHOD");
    }

    this.url = serviceUrl;
    this.method = httpMethod;
  }

  public static APIRequestDTO create(String serviceUrl, String httpMethod) {
    return new APIRequestDTO(serviceUrl, httpMethod);
  }


  public String getEndPoint() {
    return isGet() ? ExternalServiceUtil.buildEncodedURL(url, params) : getUrl();
  }

  public boolean isGet() {
    return Rest.METHOD_GET.equalsIgnoreCase(method);
  }

  public boolean isPost() {
    return Rest.METHOD_POST.equalsIgnoreCase(method) || Rest.METHOD_PUT.equalsIgnoreCase(method);
  }

  public void setJsonBody(String body) {
    this.contentType = SystemConstants.Rest.APPLICATION_JSON;
    this.body = body;
  }
}