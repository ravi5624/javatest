package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.InterStateRenewalDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.InterStateRenewalHelper;
import nst.app.manager.InterStateRenewalManager;
import nst.app.model.forms.lb.InterStateRenewal;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class InterStateRenewalService extends
    CEIBaseService<InterStateRenewal, InterStateRenewalDTO> {

  @Autowired
  InterStateRenewalManager manager;

  @Autowired
  InterStateRenewalHelper helper;

  @Override
  public BaseManager<InterStateRenewal> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<InterStateRenewal, InterStateRenewalDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return null;
  }
}