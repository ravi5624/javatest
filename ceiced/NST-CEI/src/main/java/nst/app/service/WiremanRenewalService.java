package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.WiremanRenewalDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.WiremanRenewalHelper;
import nst.app.manager.WiremanRenewalManager;
import nst.app.model.forms.lb.WiremanRenewal;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class WiremanRenewalService extends
    CEIBaseService<WiremanRenewal, WiremanRenewalDTO> {

  @Autowired
  WiremanRenewalManager manager;

  @Autowired
  WiremanRenewalHelper helper;

  @Override
  public BaseManager<WiremanRenewal> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<WiremanRenewal, WiremanRenewalDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.WIRREN;
  }
}