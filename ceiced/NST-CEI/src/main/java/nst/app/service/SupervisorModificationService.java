package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.SupervisorModificationDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.SupervisorModificationHelper;
import nst.app.manager.SupervisorModificationManager;
import nst.app.model.forms.lb.SupervisorModification;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SupervisorModificationService extends
    CEIBaseService<SupervisorModification, SupervisorModificationDTO> {

  @Autowired
  SupervisorModificationManager manager;

  @Autowired
  SupervisorModificationHelper helper;

  @Override
  public BaseManager<SupervisorModification> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<SupervisorModification, SupervisorModificationDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.SUPMODI;
  }
}