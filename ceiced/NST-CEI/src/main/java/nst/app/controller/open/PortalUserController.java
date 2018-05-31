package nst.app.controller.open;

import nst.app.config.CEIConstants;
import nst.app.dto.PortalUserDTO;
import nst.app.enums.UserType;
import nst.app.model.forms.le.ApplicationAttachment;
import nst.app.service.ApplicationAttachmentService;
import nst.app.service.PortalUserService;
import nst.common.AbstractController;
import nst.common.base.BaseResponse;
import nst.common.error.AppException;
import nst.config.MyLogger;
import nst.dto.LookupDataDTO;
import nst.kernal.SystemConstants;
import nst.util.IOUtil;
import nst.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("public/user")
public class PortalUserController extends AbstractController {

  @Autowired
  PortalUserService portalUserService;

  @Autowired
  ApplicationAttachmentService attachmentService;

  @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = SystemConstants.Rest.APPLICATION_JSON)
  public BaseResponse register(@RequestBody String body) {
    PortalUserDTO register = portalUserService.register(JSONUtil.getDataDTO(body, PortalUserDTO.class));
    return BaseResponse.createSuccess(register);
  }

  @RequestMapping(value = "/validateEmail", method = RequestMethod.POST)
  public BaseResponse validateEmail(@RequestParam String userType,@RequestParam String email) {
    portalUserService.validateEmail(UserType.getByType(userType), email);
    return BaseResponse.createSuccess();
  }

  @RequestMapping(value = "/validateMobileNumber", method = RequestMethod.POST)
  public BaseResponse validateContactNo(@RequestParam String userType, @RequestParam String mobile) {
    portalUserService.validateContactNo(UserType.getByType(userType),mobile);
    return BaseResponse.createSuccess();
  }

  @RequestMapping(value = "/validateUser", method = RequestMethod.POST)
  public BaseResponse validateUser(@RequestParam String userName,
                                   @RequestParam String userType) {
    portalUserService.validateUser(UserType.getByType(userType), userName);
    return BaseResponse.createSuccess();
  }

  @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
  public BaseResponse forgetPassword(@RequestParam String userEmail, @RequestParam String userType) {
    if(StringUtils.isEmpty(userType)){
      throw AppException.create("FIELD_ERROR", "userType", CEIConstants.REQUIRED);
    }
    UserType type = UserType.valueOf(userType.toUpperCase());
    portalUserService.forgetPassword(userEmail, type);
    return BaseResponse.createSuccess();
  }

  @RequestMapping(value = "/resendOtp", method = RequestMethod.POST)
  public BaseResponse resendOtp(@RequestParam Long userId) {
    portalUserService.resendOtp(userId);
    return BaseResponse.createSuccess();
  }

  @RequestMapping(value = "/{uniqueId}/verifyEmail/{code}", method = RequestMethod.GET, produces = SystemConstants.Rest.APPLICATION_JSON)
  public BaseResponse verifyEmail(@PathVariable(value = "uniqueId") String uniqueId,
      @PathVariable(value = "code") String code) {
    portalUserService.verifyEmail(uniqueId, code);
    return BaseResponse.createSuccess();
  }

  @RequestMapping(value = "/otp/verify", method = RequestMethod.GET, produces = SystemConstants.Rest.APPLICATION_JSON)
  public BaseResponse verifyOtp(@RequestParam(value = "otp") String otp,
      @RequestParam(value = "userId") Long userId) {
    portalUserService.verifyOTP(otp, userId);
    return BaseResponse.createSuccess();
  }

  @RequestMapping(value = "/resetPassword", method = RequestMethod.GET, produces = SystemConstants.Rest.APPLICATION_JSON)
  public BaseResponse resetPassword(@RequestParam(value = "encryptKey") String encryptKey,
      @RequestParam(value = "pass") String pass) {
    if(StringUtils.isEmpty(pass)){
      throw AppException.create("FIELD_ERROR", "pass", CEIConstants.REQUIRED);
    }
    portalUserService.resetPassword(encryptKey, pass);
    return BaseResponse.createSuccess();
  }

  @RequestMapping(value = "/{applicationId}/getFile/{fileId}/{uuid}", method = RequestMethod.GET)
  public void getFile(@PathVariable("applicationId") Long applicationId,
                      @PathVariable("fileId") Long fileId,
                      @PathVariable("uuid") String uuid,
                      HttpServletResponse response) {

    ApplicationAttachment attachment = attachmentService.getFile(applicationId, fileId, uuid);
    response.setContentType(attachment.getMimeType());
    response.addHeader("Content-Disposition", "attachment; filename=" + attachment.getRealFileName());
    try {
      IOUtil.copy(new FileInputStream(attachment.getFile()), response.getOutputStream());
      response.getOutputStream().flush();
    } catch (Throwable throwable) {
      MyLogger.logError(throwable);
    }
  }

  @RequestMapping(value = "/{applicationId}/getCert/{uuid}", method = RequestMethod.GET)
  public void getFile(@PathVariable("applicationId") Long applicationId,
                      @PathVariable("uuid") String uuid,
                      HttpServletResponse response) {
    InputStream cert = portalUserService.getCert(applicationId, uuid);
    response.addHeader("Content-Disposition", "attachment; filename=" + applicationId +".pdf");
    try {
      IOUtil.copy(cert, response.getOutputStream());
      response.getOutputStream().flush();
    } catch (Throwable throwable) {
      MyLogger.logError(throwable);
    }
  }

  @RequestMapping(value = "/types", method = RequestMethod.GET, produces = SystemConstants.Rest.APPLICATION_JSON)
  public BaseResponse userType() {
    List<LookupDataDTO> data = new ArrayList<>();
    data.add(LookupDataDTO.create(1l,UserType.OWNER.getType() , UserType.OWNER.getName()));
    data.add(LookupDataDTO.create(2l,UserType.EM_AGENCY.getType() , UserType.EM_AGENCY.getName()));
    data.add(LookupDataDTO.create(3l,UserType.IT_AGENCY.getType() , UserType.IT_AGENCY.getName()));
    data.add(LookupDataDTO.create(4l,UserType.OM_AGENCY.getType() , UserType.OM_AGENCY.getName()));

//    Arrays.stream(UserType.values()).forEach(type -> {
//      data.add(LookupDataDTO.create((long) type.getId(), type.getType(), type.getName()));
//    });
    return BaseResponse.createSuccess(data);
  }

  public static void main(String[] args) {
    PortalUserController pc=new PortalUserController();
    System.out.println(pc.userType().toJSON());
  }

}
