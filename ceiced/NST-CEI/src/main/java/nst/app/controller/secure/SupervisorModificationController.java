package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.SupervisorModificationDTO;
import nst.app.model.forms.lb.SupervisorModification;
import nst.app.service.SupervisorModificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/supervisorModification")
public class SupervisorModificationController extends
    CEIBaseController<SupervisorModification, SupervisorModificationDTO> {

  @Autowired
  SupervisorModificationService service;

  public SupervisorModificationController() {
    super(SupervisorModificationDTO.class);
  }

  public CEIBaseService<SupervisorModification, SupervisorModificationDTO> getService() {
    return service;
  }
}