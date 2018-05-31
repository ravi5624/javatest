package nst.util;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import nst.common.error.AppException;
import nst.config.MyLogger;
import nst.dto.APIRequestDTO;
import nst.dto.APIResponseDTO;
import nst.kernal.SystemConstants;
import nst.kernal.SystemConstants.Rest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class ExternalServiceUtil {

  public static String buildEncodedURL(String serviceUrl, Map<String, String> parameters) {
    return String.format("%s?%s", serviceUrl, getQuery(parameters, true));
  }

  private static String getQuery(Map<String, String> parameters, boolean encode) {
    if (CollectionUtils.isEmpty(parameters)) {
      return "";
    }
    StringBuilder url = new StringBuilder(256);
    boolean firstParameter = true;
    for (Map.Entry<String, String> parameter : parameters.entrySet()) {
      if (!firstParameter) {
        url.append('&');
      }
      url.append(parameter.getKey()).append('=').append(encode ? encodeURL(parameter.getValue()) : parameter.getValue());
      firstParameter = false;
    }

    return url.toString();
  }

  public static APIResponseDTO getResult(APIRequestDTO apiRequestDTO) {
    Integer responseCode = null;
    APIResponseDTO apiResponseDTO = new APIResponseDTO();
    HttpURLConnection connection = null;
    try {
      long startTime = System.currentTimeMillis();

      URL url = new URL(apiRequestDTO.getEndPoint());
      connection = (HttpURLConnection) url.openConnection();

      if (!StringUtils.isEmpty(apiRequestDTO.getContentType())) {
        connection.setRequestProperty("Content-Type", apiRequestDTO.getContentType());
      }
      connection.setRequestMethod(apiRequestDTO.getMethod());
      connection.setDoInput(true);
      connection.setDoOutput(true);

      if (!CollectionUtils.isEmpty(apiRequestDTO.getHeaders())) {
        apiRequestDTO.getHeaders().forEach(connection::setRequestProperty);
      }

      if (apiRequestDTO.getTimeOut() != null) {
        connection.setConnectTimeout(apiRequestDTO.getTimeOut());
        connection.setReadTimeout(apiRequestDTO.getTimeOut());
      }

      if (apiRequestDTO.isPost() && !StringUtils.isEmpty(apiRequestDTO.getBody())) {
        IOUtil.copy(apiRequestDTO.getBody(), connection.getOutputStream(), Rest.UTF_8);
      }
      if (apiRequestDTO.isPost() && !CollectionUtils.isEmpty(apiRequestDTO.getParams())) {
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        IOUtil.copy(getQuery(apiRequestDTO.getParams(), false), connection.getOutputStream(), Rest.UTF_8);
      }

      long elapsedTime = System.currentTimeMillis() - startTime;

      responseCode = connection.getResponseCode();
      apiResponseDTO.setCode(responseCode);
      apiResponseDTO.setTime(elapsedTime);

      if (responseCode == HttpURLConnection.HTTP_OK) {
        apiResponseDTO.setResponse(IOUtil.readAsString(connection.getInputStream(), Rest.UTF_8));
        return apiResponseDTO;
      }

      if (responseCode < HttpURLConnection.HTTP_BAD_REQUEST) {
        apiResponseDTO.setResponse(IOUtil.readAsString(connection.getInputStream(), Rest.UTF_8));
      } else {
        apiResponseDTO.setResponse(IOUtil.readAsString(connection.getErrorStream(), Rest.UTF_8));
      }

      apiResponseDTO.setError(Boolean.TRUE);
      apiResponseDTO.setErrorCode(SystemConstants.Rest.ERROR_SERVER_SIDE);
      apiResponseDTO.setErrorMessage(SystemConstants.API_SERVICE_ERROR);

    } catch (SocketTimeoutException e) {
      MyLogger.logService(apiRequestDTO.getSender(), apiRequestDTO.getService(), "EXCEPTION ==> ", e.getMessage());
      MyLogger.logError(e);
      apiResponseDTO.setError(Boolean.TRUE);
      apiResponseDTO.setErrorCode(SystemConstants.Rest.ERROR_SERVER_SIDE);
      apiResponseDTO.setErrorMessage(SystemConstants.API_SERVICE_ERROR);
      apiResponseDTO.setResponse(e.getMessage());
      return apiResponseDTO;
    } catch (Exception e) {
      MyLogger.logService(apiRequestDTO.getSender(), apiRequestDTO.getService(), "EXCEPTION ==> ", e.getMessage());
      MyLogger.logError(e);
      apiResponseDTO.setError(Boolean.TRUE);
      apiResponseDTO.setErrorCode(SystemConstants.Rest.ERROR_SERVER_SIDE);
      apiResponseDTO.setErrorMessage(SystemConstants.API_SERVICE_ERROR);
      apiResponseDTO.setResponse(e.getMessage());
      return apiResponseDTO;
    } finally {
      connection.disconnect();
    }
    return apiResponseDTO;
  }

  public static String decodeURL(String data) {
    try {
      return java.net.URLDecoder.decode(data, Rest.UTF_8);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String encodeURL(String data) {
    if (StringUtils.isEmpty(data)) {
      return "";
    }
    try {
      return URLEncoder.encode(data, Rest.UTF_8);
    } catch (Throwable throwable) {
      MyLogger.logError(throwable);
    }
    return null;
  }

  public static void main(String args[]) {
    try {
      APIRequestDTO apiRequestDTO = APIRequestDTO.create("http://localhost:8080/open/hello/hi", Rest.METHOD_GET);
      //apiRequestDTO.setBody("Test");
      Map<String, String> params = new HashMap<>();
      params.put("data", "{\"data\":{\"applicationId\":\"80\",\"fileStatus\":\"Query Raised\"},\"Comments\":[{\"Added By\":\"einad\",\"Field Name\":\"Others\",\"Added On\":\"2017-11-23 00:00:00\",\"Comment\":\"Energy Meter Details are not filled properly. Correct it\"}]}");
      apiRequestDTO.setParams(params);
      //APIRequestDTO apiRequestDTO = APIRequestDTO.create("http://localhost:8080/open/hello/hi", SystemConstants.Rest.METHOD_GET);
      apiRequestDTO.setContentType(SystemConstants.Rest.APPLICATION_JSON);
      System.out.println(getResult(apiRequestDTO));
    } catch (AppException e) {
      e.printStackTrace();
    }
  }
}