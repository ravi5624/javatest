package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.PortalUserDTO;
import nst.app.dto.SupervisorExaminationDTO;
import nst.app.lookups.LookupUtil;
import nst.app.model.forms.lb.SupervisorExamination;
import nst.app.service.PortalUserService;
import nst.app.service.SupervisorExaminationService;
import nst.common.base.BaseResponse;
import nst.common.security.LoginUser;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/supervisorExamination")
public class SupervisorExaminationController extends
    CEIBaseController<SupervisorExamination, SupervisorExaminationDTO> {

  @Autowired
  SupervisorExaminationService service;

  @Autowired
  PortalUserService portalUserService;

  public SupervisorExaminationController() {
    super(SupervisorExaminationDTO.class);
  }

  public CEIBaseService<SupervisorExamination, SupervisorExaminationDTO> getService() {
    return service;
  }

//  @RequestMapping(value = "/exp/{id}/add", method = RequestMethod.GET)
  @RequestMapping(value = "/{id}/add", method = RequestMethod.GET)
  public BaseResponse addExperience(@PathVariable("id") Long id) {
    return BaseResponse.createSuccess(service.addExperience(id));
  }

  @RequestMapping(value = "/exp/{id}/delete/{expId}", method = RequestMethod.DELETE)
  public BaseResponse deleteExperience(@PathVariable("id") Long id,
                                       @PathVariable("expId") Long expId) {
    service.deleteExperience(id, expId);
    return BaseResponse.createSuccess();
  }


  @RequestMapping(value = "/examinationCenters", method = RequestMethod.GET)
  public BaseResponse submitForm() {
    return BaseResponse.createSuccess(LookupUtil.getExaminationCenters());
  }

  @RequestMapping(value = "/modeOfLanguage", method = RequestMethod.GET)
  public BaseResponse modeOfLanguage() {
    return BaseResponse.createSuccess(LookupUtil.modeOfLanguage());
  }

  @RequestMapping(value = "/examType", method = RequestMethod.GET)
  public BaseResponse examType() {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    PortalUserDTO portalUserDTO =portalUserService.getDetail(loginUser.getUserId());
    String userType = portalUserDTO.getType();
    return BaseResponse.createSuccess(LookupUtil.examType(userType));
  }

  @RequestMapping(value = "/experience", method = RequestMethod.GET)
  public BaseResponse experience(@RequestParam("examType") String examType) {
    return BaseResponse.createSuccess(LookupUtil.getExperience(examType));
  }

}