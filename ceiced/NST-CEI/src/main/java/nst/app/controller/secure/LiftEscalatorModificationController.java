package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.LiftEscalatorModificationDTO;
import nst.app.model.forms.le.LiftEscalatorModification;
import nst.app.service.LiftEscalatorModificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/liftEscalatorModification")
public class LiftEscalatorModificationController extends
    CEIBaseController<LiftEscalatorModification, LiftEscalatorModificationDTO> {

  @Autowired
  LiftEscalatorModificationService service;

  public LiftEscalatorModificationController() {
    super(LiftEscalatorModificationDTO.class);
  }

  public CEIBaseService<LiftEscalatorModification, LiftEscalatorModificationDTO> getService() {
    return service;
  }
}