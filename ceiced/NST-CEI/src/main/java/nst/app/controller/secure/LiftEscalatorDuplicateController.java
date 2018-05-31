package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.LiftEscalatorDuplicateDTO;
import nst.app.model.forms.le.LiftEscalatorDuplicate;
import nst.app.service.LiftEscalatorDuplicateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/liftEscalatorDuplicate")
public class LiftEscalatorDuplicateController extends
    CEIBaseController<LiftEscalatorDuplicate, LiftEscalatorDuplicateDTO> {

  @Autowired
  LiftEscalatorDuplicateService service;

  public LiftEscalatorDuplicateController() {
    super(LiftEscalatorDuplicateDTO.class);
  }

  public CEIBaseService<LiftEscalatorDuplicate, LiftEscalatorDuplicateDTO> getService() {
    return service;
  }
}