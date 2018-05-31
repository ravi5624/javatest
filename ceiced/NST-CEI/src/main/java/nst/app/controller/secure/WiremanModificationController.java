package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.WiremanModificationDTO;
import nst.app.model.forms.lb.WiremanModification;
import nst.app.service.WiremanModificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/wiremanModification")
public class WiremanModificationController extends
    CEIBaseController<WiremanModification, WiremanModificationDTO> {

  @Autowired
  WiremanModificationService service;

  public WiremanModificationController() {
    super(WiremanModificationDTO.class);
  }

  public CEIBaseService<WiremanModification, WiremanModificationDTO> getService() {
    return service;
  }
}