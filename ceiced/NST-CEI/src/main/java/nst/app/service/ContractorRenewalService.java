package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.ContractorRenewalDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.ContractorRenewalHelper;
import nst.app.manager.ContractorRenewalManager;
import nst.app.model.forms.lb.ContractorRenewal;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ContractorRenewalService extends
    CEIBaseService<ContractorRenewal, ContractorRenewalDTO> {

  @Autowired
  ContractorRenewalManager manager;

  @Autowired
  ContractorRenewalHelper helper;

  @Override
  public BaseManager<ContractorRenewal> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<ContractorRenewal, ContractorRenewalDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.CONREN;
  }
}