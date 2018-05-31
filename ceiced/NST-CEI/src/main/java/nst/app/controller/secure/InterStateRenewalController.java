package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.InterStateRenewalDTO;
import nst.app.model.forms.lb.InterStateRenewal;
import nst.app.service.InterStateRenewalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/interStateRenewal")
public class InterStateRenewalController extends
    CEIBaseController<InterStateRenewal, InterStateRenewalDTO> {

  @Autowired
  InterStateRenewalService service;

  public InterStateRenewalController() {
    super(InterStateRenewalDTO.class);
  }

  public CEIBaseService<InterStateRenewal, InterStateRenewalDTO> getService() {
    return service;
  }
}