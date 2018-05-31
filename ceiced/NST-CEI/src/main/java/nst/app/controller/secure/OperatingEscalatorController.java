package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.OperatingEscalatorDTO;
import nst.app.model.forms.le.OperatingEscalator;
import nst.app.service.OperatingEscalatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/operatingEscalator")
public class OperatingEscalatorController extends
    CEIBaseController<OperatingEscalator, OperatingEscalatorDTO> {

  @Autowired
  OperatingEscalatorService service;

  public OperatingEscalatorController() {
    super(OperatingEscalatorDTO.class);
  }

  public CEIBaseService<OperatingEscalator, OperatingEscalatorDTO> getService() {
    return service;
  }
}