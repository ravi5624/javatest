package nst.app.controller.common;

import nst.app.dto.LicenseSearchDTO;
import nst.app.dto.PortalUserDTO;
import nst.app.enums.ApplicationType;
import nst.app.enums.UserType;
import nst.app.service.FormService;
import nst.app.service.LocaleService;
import nst.app.service.PortalUserService;
import nst.common.AbstractController;
import nst.common.base.BaseResponse;
import nst.common.security.LoginUser;
import nst.dto.ApplicationDetailsDTO;
import nst.kernal.SystemConstants;
import nst.util.JSONUtil;
import nst.util.LoginUserUtil;
import nst.util.RepoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/user")
public class UserController extends AbstractController {

  @Autowired
  PortalUserService service;

  @Autowired
  FormService fmservice;

  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  public BaseResponse detail() {
    return BaseResponse.createSuccess(service.getDetail());
  }

  @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
  public BaseResponse dashboard() {
    return BaseResponse.createSuccess(service.getAppStatusCount());
  }

  @RequestMapping(value = "/myApps", method = RequestMethod.GET)
  public BaseResponse myApps(
      @RequestParam(value = "page", defaultValue = "1") int pageNo,
      @RequestParam(value = "type", defaultValue = "") String type,
      @RequestParam(value = "applicantName", defaultValue = "") String applicantName,
      @RequestParam(value = "limit", defaultValue = "" + RepoUtil.PAGE_SIZE) int pageSize
  ) {

    ApplicationType applicationType = StringUtils.isEmpty(type) ? null : ApplicationType.valueOf(type);

    return BaseResponse
        .createSuccess(service.getMyApps(applicationType, applicantName, RepoUtil.pagination(pageNo, pageSize)));
  }

  @RequestMapping(value = "/myNotifications", method = RequestMethod.GET)
  public BaseResponse myNotifications(
      @RequestParam(value = "page", defaultValue = "1") int pageNo,
      @RequestParam(value = "limit", defaultValue = "" + RepoUtil.PAGE_SIZE) int pageSize
  ) {
    return BaseResponse
        .createSuccess(service.getMyNotifications(RepoUtil.pagination(pageNo, pageSize)));
  }

  @RequestMapping(value = "/notificationCount", method = RequestMethod.GET)
  public BaseResponse notificationCount() {
    return BaseResponse.createSuccess(service.myNotificationCount());
  }

  @RequestMapping(value = "/searchApplication", method = RequestMethod.POST)
  public BaseResponse searchApplication(@RequestBody String body) {
    LicenseSearchDTO searchDTO = JSONUtil.getDataDTO(body, LicenseSearchDTO.class);
    return BaseResponse.createSuccess(service.searchApplication(searchDTO));
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = SystemConstants.Rest.APPLICATION_JSON)
  public BaseResponse update(@RequestBody @Valid PortalUserDTO portalUser) {
    service.update(portalUser);
    return BaseResponse.createSuccess();
  }

  @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
  public BaseResponse changePassword(@RequestParam String oldPassword, @RequestParam String pass) {
    service.changePassword(oldPassword, pass);
    return BaseResponse.createSuccess();
  }

  @RequestMapping(value = "/getapp", method = RequestMethod.GET)
  public BaseResponse getApplicationDetails(@RequestParam(value = "transId") String transId) {
    ApplicationDetailsDTO applicationDetailsDTO = fmservice.postPayment(transId);
    return BaseResponse.createSuccess(applicationDetailsDTO);
  }
  @Autowired
  LocaleService localeService;

  @RequestMapping(value = "/localeMsg", method = RequestMethod.GET)
  public BaseResponse localeMessage() {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    UserType userType = null;
    if(loginUser != null){
      if(loginUser.hasAuthority(UserType.WIREMAN.getType())){
        userType = UserType.WIREMAN;
      }
      if (loginUser.hasAuthority(UserType.SUPERVISOR.getType())){
        userType = UserType.SUPERVISOR;
      }
    }

    return BaseResponse.createSuccess(localeService.getAll(userType));
  }
}

