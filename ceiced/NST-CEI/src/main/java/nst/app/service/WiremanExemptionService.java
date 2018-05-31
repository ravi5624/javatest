package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.WiremanExemptionDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.WiremanExemptionHelper;
import nst.app.manager.WiremanExemptionManager;
import nst.app.model.forms.lb.WiremanExemption;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class WiremanExemptionService extends
    CEIBaseService<WiremanExemption, WiremanExemptionDTO> {

  @Autowired
  WiremanExemptionManager manager;

  @Autowired
  WiremanExemptionHelper helper;

  @Override
  public BaseManager<WiremanExemption> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<WiremanExemption, WiremanExemptionDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.WIREXMP;
  }
}