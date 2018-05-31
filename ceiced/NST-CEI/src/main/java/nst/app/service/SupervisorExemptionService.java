package nst.app.service;

import java.util.List;
import nst.app.common.CEIBaseService;
import nst.app.dto.SupervisorExemptionDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.SupervisorExemptionHelper;
import nst.app.manager.SupervisorExemptionManager;
import nst.app.model.forms.lb.SupervisorExemption;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SupervisorExemptionService extends
    CEIBaseService<SupervisorExemption, SupervisorExemptionDTO> {

  @Autowired
  SupervisorExemptionManager manager;

  @Autowired
  SupervisorExemptionHelper helper;

  public List<SupervisorExemptionDTO> getMyApps() {
    return helper.fromModel(manager.getMyApps());
  }
  @Override
  public BaseManager<SupervisorExemption> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<SupervisorExemption, SupervisorExemptionDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.SUPEXMP;
  }
}