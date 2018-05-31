package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.RepeaterDTO;
import nst.app.model.forms.lb.Repeater;
import nst.app.service.RepeaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/repeater")
public class RepeaterController extends
    CEIBaseController<Repeater, RepeaterDTO> {

  @Autowired
  RepeaterService service;

  public RepeaterController() {
    super(RepeaterDTO.class);
  }

  public CEIBaseService<Repeater, RepeaterDTO> getService() {
    return service;
  }
}