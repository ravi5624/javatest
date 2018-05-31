package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.SupervisorRenewalDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.SupervisorRenewalHelper;
import nst.app.manager.SupervisorRenewalManager;
import nst.app.model.forms.lb.SupervisorRenewal;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SupervisorRenewalService extends
    CEIBaseService<SupervisorRenewal, SupervisorRenewalDTO> {

  @Autowired
  SupervisorRenewalManager manager;

  @Autowired
  SupervisorRenewalHelper helper;

  @Override
  public BaseManager<SupervisorRenewal> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<SupervisorRenewal, SupervisorRenewalDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.SUPREN;
  }
}