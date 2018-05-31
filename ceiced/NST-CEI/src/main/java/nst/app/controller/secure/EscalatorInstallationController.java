package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.EscalatorInstallationDTO;
import nst.app.model.forms.le.EscalatorInstallation;
import nst.app.service.EscalatorInstallationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/escalatorInstallation")
public class EscalatorInstallationController extends
    CEIBaseController<EscalatorInstallation, EscalatorInstallationDTO> {

  Class<EscalatorInstallationDTO> dtoType = EscalatorInstallationDTO.class;

  public EscalatorInstallationController(Class<EscalatorInstallationDTO> dtoType) {
    super();
    this.dtoType = dtoType;
  }

  @Autowired
  EscalatorInstallationService service;

  public EscalatorInstallationController() {
    super(EscalatorInstallationDTO.class);
  }

  public CEIBaseService<EscalatorInstallation, EscalatorInstallationDTO> getService() {
    return service;
  }
}