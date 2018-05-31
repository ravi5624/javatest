package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.DuplicatePermitDTO;
import nst.app.model.forms.lb.DuplicatePermit;
import nst.app.service.DuplicatePermitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/duplicatePermit")
public class DuplicatePermitController extends
    CEIBaseController<DuplicatePermit, DuplicatePermitDTO> {

  @Autowired
  DuplicatePermitService service;

  public DuplicatePermitController() {
    super(DuplicatePermitDTO.class);
  }

  public CEIBaseService<DuplicatePermit, DuplicatePermitDTO> getService() {
    return service;
  }
}