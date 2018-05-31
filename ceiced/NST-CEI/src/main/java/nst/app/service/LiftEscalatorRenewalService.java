package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.LiftEscalatorRenewalDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.LiftEscalatorRenewalHelper;
import nst.app.manager.LiftEscalatorRenewalManager;
import nst.app.model.forms.le.LiftEscalatorRenewal;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class LiftEscalatorRenewalService extends
    CEIBaseService<LiftEscalatorRenewal, LiftEscalatorRenewalDTO> {

  @Autowired
  LiftEscalatorRenewalManager manager;

  @Autowired
  LiftEscalatorRenewalHelper helper;

  @Override
  public BaseManager<LiftEscalatorRenewal> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<LiftEscalatorRenewal, LiftEscalatorRenewalDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.LERL;
  }
}