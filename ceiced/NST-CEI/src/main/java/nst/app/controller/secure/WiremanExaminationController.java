package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.PortalUserDTO;
import nst.app.dto.WiremanExaminationDTO;
import nst.app.lookups.LookupUtil;
import nst.app.model.forms.lb.WiremanExamination;
import nst.app.service.PortalUserService;
import nst.app.service.WiremanExaminationService;
import nst.common.base.BaseResponse;
import nst.common.security.LoginUser;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/wiremanExamination")
public class WiremanExaminationController extends
    CEIBaseController<WiremanExamination, WiremanExaminationDTO> {

  @Autowired
  WiremanExaminationService service;

  @Autowired
  PortalUserService portalUserService;


  public WiremanExaminationController() {
    super(WiremanExaminationDTO.class);
  }

  public CEIBaseService<WiremanExamination, WiremanExaminationDTO> getService() {
    return service;
  }

  @RequestMapping(value = "/examinationCenters", method = RequestMethod.GET)
  public BaseResponse examinationCenters(@RequestParam("examType") String examType) {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    PortalUserDTO portalUserDTO =portalUserService.getDetail(loginUser.getUserId());
    String userType = portalUserDTO.getType();

    return BaseResponse.createSuccess(LookupUtil.getExaminationCenters(userType, examType));
  }

  @RequestMapping(value = "/wiremanExamEligibility", method = RequestMethod.GET)
  public BaseResponse wiremanExamEligibility() {
    return BaseResponse.createSuccess(LookupUtil.wiremanExamEligibility());
  }


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
}