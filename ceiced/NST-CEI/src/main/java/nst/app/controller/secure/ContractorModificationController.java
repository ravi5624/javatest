package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.ContractorModificationDTO;
import nst.app.model.forms.lb.ContractorModification;
import nst.app.service.ContractorModificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/contractorModification")
public class ContractorModificationController extends
    CEIBaseController<ContractorModification, ContractorModificationDTO> {

  @Autowired
  ContractorModificationService service;

  public ContractorModificationController() {
    super(ContractorModificationDTO.class);
  }

  public CEIBaseService<ContractorModification, ContractorModificationDTO> getService() {
    return service;
  }
}