package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.ContractorModificationDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.ContractorModificationHelper;
import nst.app.manager.ContractorModificationManager;
import nst.app.model.forms.lb.ContractorModification;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ContractorModificationService extends
    CEIBaseService<ContractorModification, ContractorModificationDTO> {

  @Autowired
  ContractorModificationManager manager;

  @Autowired
  ContractorModificationHelper helper;

  @Override
  public BaseManager<ContractorModification> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<ContractorModification, ContractorModificationDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.CONMODI;
  }
}