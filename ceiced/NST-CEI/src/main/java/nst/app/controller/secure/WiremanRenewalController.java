package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.WiremanRenewalDTO;
import nst.app.model.forms.lb.WiremanRenewal;
import nst.app.service.WiremanRenewalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/wiremanRenewal")
public class WiremanRenewalController extends
    CEIBaseController<WiremanRenewal, WiremanRenewalDTO> {

  @Autowired
  WiremanRenewalService service;

  public WiremanRenewalController() {
    super(WiremanRenewalDTO.class);
  }

  public CEIBaseService<WiremanRenewal, WiremanRenewalDTO> getService() {
    return service;
  }
}