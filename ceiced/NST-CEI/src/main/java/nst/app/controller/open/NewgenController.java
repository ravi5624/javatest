package nst.app.controller.open;

import com.fasterxml.jackson.core.JsonProcessingException;
import nst.app.dto.newgen.NGQueryRequestDTO;
import nst.app.dto.newgen.NGRequestDTO;
import nst.app.service.NewgenService;
import nst.common.AbstractController;
import nst.common.base.BaseResponse;
import nst.config.MyLogger;
import nst.dto.APIRequestDTO;
import nst.dto.APIResponseDTO;
import nst.kernal.CommunicationType;
import nst.kernal.ExternalCommunication;
import nst.kernal.SystemConstants;
import nst.service.BackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public/newgen")
public class NewgenController extends AbstractController {

  @Autowired
  NewgenService newgenService;

  @Autowired
  BackgroundService backgroundService;
  /*
  {
    "id": "26272",
    "status": "Success",
    "fileStatus": "Approved",
    "applicationId": "20180129-WIREXM-KNMX9",
    "remarks": "New WI Created",
    "fileNo": "20180129-WIREXM-L0ER0",
    "pid": "ExamInterstate-0000000313-process",
    "comments": []
  }
  */
  @RequestMapping(value = "/status", method = {RequestMethod.POST, RequestMethod.PUT})
  public BaseResponse request(@RequestBody String body) throws JsonProcessingException {
    MyLogger.logService("NewgenController", "status", body);
    NGRequestDTO ngRequestDTO = NGRequestDTO.fromJSON(body);

    //TODO: update service url
    APIRequestDTO requestDTO = APIRequestDTO
            .create("http://localhost:8080", SystemConstants.Rest.METHOD_POST);
    requestDTO.setSender("NewgenController");
    requestDTO.setService("status");
    requestDTO.setJsonBody(body);
    APIResponseDTO responseDTO = null;

    long startTime = System.currentTimeMillis();
    try {
      newgenService.request(ngRequestDTO);

      long serviceTime = System.currentTimeMillis() - startTime;

      responseDTO = new APIResponseDTO();
      responseDTO.setCode(200);
      responseDTO.setTime(serviceTime);

      return BaseResponse.createSuccess();
    }catch (Throwable throwable){
      MyLogger.logError("NewgenController.status",
              throwable.getMessage());
      throw throwable;
    }finally {
      ExternalCommunication submitApplication = new ExternalCommunication(
              CommunicationType.INTERNAL, "status", null);
      submitApplication.setDetails(requestDTO, responseDTO);
      backgroundService.add(submitApplication);
    }
  }

  @RequestMapping(value = "/query", method = {RequestMethod.POST, RequestMethod.PUT})
  public BaseResponse query(@RequestBody String body) {
    MyLogger.logService("NewgenController", "query", body);
    NGQueryRequestDTO ngRequestDTO = NGQueryRequestDTO.fromJSON(body);

    APIRequestDTO requestDTO = APIRequestDTO
            .create("http://localhost:8080", SystemConstants.Rest.METHOD_PUT);
    requestDTO.setSender("NewgenController");
    requestDTO.setService("query");
    requestDTO.setJsonBody(body);
    APIResponseDTO responseDTO = null;

    long startTime = System.currentTimeMillis();
    try{
      newgenService.queryRequested(ngRequestDTO);

      long serviceTime = System.currentTimeMillis() - startTime;

      responseDTO = new APIResponseDTO();
      responseDTO.setCode(200);
      responseDTO.setTime(serviceTime);
      return BaseResponse.createSuccess();
    }catch (Throwable throwable){
      throw throwable;
    }finally {
      ExternalCommunication submitApplication = new ExternalCommunication(
              CommunicationType.INTERNAL, "query", null);
      submitApplication.setDetails(requestDTO, responseDTO);
      backgroundService.add(submitApplication);
    }
  }

  public static void main(String[] args) {
    System.out.println(BaseResponse.createSuccess().toJSON());
  }
}