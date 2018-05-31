package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.ContractorRenewalDTO;
import nst.app.model.forms.lb.ContractorRenewal;
import nst.app.service.ContractorRenewalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/contractorRenewal")
public class ContractorRenewalController extends
    CEIBaseController<ContractorRenewal, ContractorRenewalDTO> {

  @Autowired
  ContractorRenewalService service;

  public ContractorRenewalController() {
    super(ContractorRenewalDTO.class);
  }

  public CEIBaseService<ContractorRenewal, ContractorRenewalDTO> getService() {
    return service;
  }
}