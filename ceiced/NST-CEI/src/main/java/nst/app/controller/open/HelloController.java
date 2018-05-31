package nst.app.controller.open;

import nst.app.dto.newgen.NGSubmitDTO;
import nst.common.AbstractController;
import nst.common.base.BaseResponse;
import nst.common.error.AppException;
import nst.common.error.TechnicalException;
import nst.config.MyLogger;
import nst.dto.APIRequestDTO;
import nst.kernal.SystemConstants.Rest;
import nst.util.ExternalServiceUtil;
import nst.util.JSONUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("open/hello")
public class HelloController extends AbstractController {

  @RequestMapping(value = "/examinterstate/webresources/examinterstate", method = {RequestMethod.POST, RequestMethod.PUT})
  public NGSubmitDTO newGenSubmit(@RequestBody String body) {
    MyLogger.logService("ExaminterState", "Body=>", body);
    NGSubmitDTO ngRequestDTO = JSONUtil.fromJSON(
        "{\"fileStatus\":\"APPROVED\",\"pid\":\"pid\",\"id\":\"id\",\"applicationId\":\"applicationId\",\"fileNo\":\"fileNo\",\"comments\":null,\"remarks\":\"remarks\",\"status\":\"Success\"}}/Success\"}",
        NGSubmitDTO.class);
    return ngRequestDTO;
  }

  @RequestMapping(value = "/exemptioncontator/webresources/exemptioncontator", method = {RequestMethod.POST, RequestMethod.PUT})
  public NGSubmitDTO exemptioncontator(@RequestBody String body) {
    MyLogger.logService("ExemptionContator", "Body=>", body);
    NGSubmitDTO ngRequestDTO = JSONUtil.fromJSON(
        "{\"fileStatus\":\"APPROVED\",\"pid\":\"pid\",\"id\":\"id\",\"applicationId\":\"applicationId\",\"fileNo\":\"fileNo\",\"comments\":null,\"remarks\":\"remarks\",\"status\":\"Success\"}}/Success\"}",
        NGSubmitDTO.class);
    return ngRequestDTO;
  }

  @RequestMapping(value = "/examinterstate/webresources/queryraise", method = {RequestMethod.POST, RequestMethod.PUT})
  public NGSubmitDTO newGenQuerySubmit(@RequestBody String body) {
    MyLogger.logService("ExaminterState", "Body=>", body);
    NGSubmitDTO ngRequestDTO = JSONUtil.fromJSON(
        "{\"fileStatus\":\"QUERY_REPLIED\",\"pid\":\"pid\",\"id\":\"id\",\"applicationId\":\"applicationId\",\"fileNo\":\"fileNo\",\"comments\":null,\"remarks\":\"remarks\",\"status\":\"Success\"}",
        NGSubmitDTO.class);
    return ngRequestDTO;
  }

  @RequestMapping(value = "/hi", method = RequestMethod.GET)
  public String helloGet(@RequestParam(value = "data") String data) {
    System.out.println("helloGet = Param =" + data);
    return "Done";
  }

  @RequestMapping(value = "/hi", method = RequestMethod.POST)
  public String helloPost(@RequestParam(value = "data") String data, @RequestBody String body) {
    System.out.println("helloPost = Param =" + data);
    System.out.println("helloPost = Body = " + body);
    return "Done";
  }

  public static void main(String args[]) {
    try {
      APIRequestDTO apiRequestDTO = APIRequestDTO
          .create("http://localhost:8080/open/hello/hi", Rest.METHOD_GET);
      Map<String, String> params = new HashMap<>();
      params.put("data",
          "{\"data\":{\"applicationId\":\"80\",\"fileStatus\":\"Query Raised\"},\"Comments\":[{\"Added By\":\"einad\",\"Field Name\":\"Others\",\"Added On\":\"2017-11-23 00:00:00\",\"Comment\":\"Energy Meter Details are not filled properly. Correct it\"}]}");
      apiRequestDTO.setParams(params);

      System.out.println(ExternalServiceUtil.getResult(apiRequestDTO));
      apiRequestDTO.setMethod(Rest.METHOD_POST);
      System.out.println(ExternalServiceUtil.getResult(apiRequestDTO));
    } catch (AppException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping(value = "/error1", method = RequestMethod.GET)
  public BaseResponse error1() {
    if (true) {
      throw AppException.create("TEST_01", "TEST", "Test Error-1");
    }
    return null;
  }

  @RequestMapping(value = "/error2", method = RequestMethod.GET)
  public BaseResponse error2() {
    if (true) {
      throw new TechnicalException(new Error("Test Error-2"));
    }
    return null;
  }

  @RequestMapping(value = "/log", method = RequestMethod.GET)
  public
  @ResponseBody
  String log(@RequestParam(value = "clear", defaultValue = "false") boolean clear) {
    StringBuilder stringBuilder = new StringBuilder();
    MyLogger.AD_MESSAGES.forEach(b -> stringBuilder.append(b).append("<br><hr width=100%'>"));
    if (clear) {
      MyLogger.AD_MESSAGES.clear();
    }
    return stringBuilder.toString();
  }
}
