package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.SupervisorExemptionDTO;
import nst.app.lookups.LookupUtil;
import nst.app.model.forms.lb.SupervisorExemption;
import nst.app.service.SupervisorExemptionService;
import nst.common.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/supervisorExemption")
public class SupervisorExemptionController extends
    CEIBaseController<SupervisorExemption, SupervisorExemptionDTO> {

  @Autowired
  SupervisorExemptionService service;

  public SupervisorExemptionController() {
    super(SupervisorExemptionDTO.class);
  }

  @Override
  public CEIBaseService<SupervisorExemption, SupervisorExemptionDTO> getService() {
    return service;
  }

  @RequestMapping(value = "/techQualificationForSupervisor", method = RequestMethod.GET)
  public BaseResponse techQualificationForSupervisor() {
    return BaseResponse.createSuccess(LookupUtil.getTechQualificationForSupervisor());
  }
}