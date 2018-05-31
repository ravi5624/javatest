package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.LiftEscalatorRenewalDTO;
import nst.app.model.forms.le.LiftEscalatorRenewal;
import nst.app.service.LiftEscalatorRenewalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/liftEscalatorRenewal")
public class LiftEscalatorRenewalController extends
    CEIBaseController<LiftEscalatorRenewal, LiftEscalatorRenewalDTO> {

  public LiftEscalatorRenewalController() {
    super(LiftEscalatorRenewalDTO.class);
  }

  @Autowired
  LiftEscalatorRenewalService service;

  public CEIBaseService<LiftEscalatorRenewal, LiftEscalatorRenewalDTO> getService() {
    return service;
  }
}