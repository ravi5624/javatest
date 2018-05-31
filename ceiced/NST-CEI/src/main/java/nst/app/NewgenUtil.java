package nst.app;

import nst.app.dto.newgen.NGWiremanRenewalDTO;
import nst.app.enums.ApplicationType;
import nst.common.base.BaseDTO;
import nst.config.MyLogger;
import nst.dto.APIRequestDTO;
import nst.dto.APIResponseDTO;
import nst.kernal.CommunicationType;
import nst.kernal.ExternalCommunication;
import nst.kernal.SystemConstants.Rest;
import nst.service.BackgroundService;
import nst.util.ExternalServiceUtil;
import nst.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NewgenUtil {

  @Value("${newgen.host}")
  private String newgenHost;

  @Autowired
  BackgroundService backgroundService;

  public static void main(String[] args) {
    NewgenUtil newgenUtil = new NewgenUtil();
    NGWiremanRenewalDTO dto = JSONUtil.fromJSON(
        "{\"id\":20204,\"applicationId\":\"20180119-WIRREN-GGRDU\",\"fileNumber\":\"20180119-WIRREN-R5TC5\",\"applicationName\":\"Interstate Renewal Wireman\",\"type\":\"WIRREN\",\"status\":\"File_in_Processing\",\"surname\":\"Patel\",\"firstName\":\"Vishal\",\"middleName\":\"A\",\"permitNo\":\"GJwewe\",\"issueDate\":\"2018-01-03 00:00:00\"}",
        NGWiremanRenewalDTO.class);

    newgenUtil.submitApplication(ApplicationType.WIRREN, dto );
  }

  public APIResponseDTO submitApplication(ApplicationType applicationType, BaseDTO parameters) {
    APIRequestDTO requestDTO = APIRequestDTO.create(newgenHost + applicationType.getNregenUrl(), Rest.METHOD_PUT);
    requestDTO.setSender("NewGenSubmit");
    requestDTO.setService(applicationType.getType());
    requestDTO.setJsonBody(parameters.toJSON());
    APIResponseDTO responseDTO = null;
    MyLogger.logService("NewgenUtil", "REG JSON=>", parameters.toJSON());
    try {
      MyLogger.logService("NewgenUtil", "submitApplication", "Request", requestDTO.toString());
      responseDTO = ExternalServiceUtil.getResult(requestDTO);

      /*responseDTO = new APIResponseDTO();
      responseDTO.setResponse("{\"data\":{\"fileStatus\":\"File In Processing\",\"pid\":\"pid\",\"id\":\"id\",\"applicationId\":\"applicationId\",\"fileNo\":\"fileNo\",\"comments\":{},\"remarks\":\"remarks\",\"status\":\"Success\"}}");
      responseDTO.setCode(200);
      responseDTO.setTime(200l);*/

      MyLogger.logService("NewgenUtil", "RES JSON=>", responseDTO.getResponse());

      MyLogger.logService("NewgenUtil", "submitApplication", "Response", responseDTO.toString());
      return responseDTO;
    } catch (Throwable throwable) {
      MyLogger
          .logService("NewgenUtil", "submitApplication", "ERROR ====>" + throwable.getMessage());
      MyLogger.logError("NewgenUtil.submitApplication",
          applicationType + " : " + throwable.getMessage());
      throw throwable;
    } finally {
      ExternalCommunication submitApplication = new ExternalCommunication(
          CommunicationType.EXTERNAL, "SubmitApplication", applicationType.getType());
      submitApplication.setDetails(requestDTO, responseDTO);
      backgroundService.add(submitApplication);
    }
  }

  public APIResponseDTO submitQuery(ApplicationType applicationType, BaseDTO parameters) {
    APIRequestDTO requestDTO = APIRequestDTO.create(newgenHost + applicationType.getQueryUrl(), Rest.METHOD_PUT);
    requestDTO.setSender("NewGenSubmitQuery");
    requestDTO.setService(applicationType.getType());
    requestDTO.setJsonBody(parameters.toJSON());
    MyLogger.logService("NewgenUtil", "submitQuery", "Request", requestDTO.toString());
    APIResponseDTO responseDTO = null;
    try {
      responseDTO = ExternalServiceUtil.getResult(requestDTO);

      /*responseDTO = new APIResponseDTO();
      responseDTO.setResponse("{'status':'OK'}");
      responseDTO.setCode(200);
      responseDTO.setTime(200l);*/

      MyLogger.logService("NewgenUtil", "submitApplication", "Response", responseDTO.toString());
      return responseDTO;
    } catch (Throwable throwable) {
      MyLogger.logError("NewgenUtil.submitQuery",applicationType + " : " + throwable.getMessage());
      throw throwable;
    } finally {
      ExternalCommunication submitApplication = new ExternalCommunication(
              CommunicationType.EXTERNAL, "SubmitApplication", applicationType.getType());
      submitApplication.setDetails(requestDTO, responseDTO);
      backgroundService.add(submitApplication);
    }
  }
}