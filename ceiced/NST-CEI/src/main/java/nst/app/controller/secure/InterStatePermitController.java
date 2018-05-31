package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.InterStatePermitDTO;
import nst.app.model.forms.lb.InterStatePermit;
import nst.app.service.InterStatePermitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/interStatePermit")
public class InterStatePermitController extends
    CEIBaseController<InterStatePermit, InterStatePermitDTO> {

  @Autowired
  InterStatePermitService service;

  public InterStatePermitController() {
    super(InterStatePermitDTO.class);
  }

  public CEIBaseService<InterStatePermit, InterStatePermitDTO> getService() {
    return service;
  }
}