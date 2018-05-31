package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.SupervisorRenewalDTO;
import nst.app.model.forms.lb.SupervisorRenewal;
import nst.app.service.SupervisorRenewalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/supervisorRenewal")
public class SupervisorRenewalController extends
    CEIBaseController<SupervisorRenewal, SupervisorRenewalDTO> {

  @Autowired
  SupervisorRenewalService service;

  public SupervisorRenewalController() {
    super(SupervisorRenewalDTO.class);
  }

  public CEIBaseService<SupervisorRenewal, SupervisorRenewalDTO> getService() {
    return service;
  }
}