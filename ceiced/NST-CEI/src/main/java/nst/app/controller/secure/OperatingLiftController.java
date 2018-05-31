package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.OperatingLiftDTO;
import nst.app.model.forms.le.OperatingLift;
import nst.app.service.OperatingLiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/operatingLift")
public class OperatingLiftController extends
    CEIBaseController<OperatingLift, OperatingLiftDTO> {

  @Autowired
  OperatingLiftService service;

  public OperatingLiftController() {
    super(OperatingLiftDTO.class);
  }

  public CEIBaseService<OperatingLift, OperatingLiftDTO> getService() {
    return service;
  }
}