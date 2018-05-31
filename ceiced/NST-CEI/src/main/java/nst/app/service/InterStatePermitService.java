package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.InterStatePermitDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.InterStatePermitHelper;
import nst.app.manager.InterStatePermitManager;
import nst.app.model.forms.lb.InterStatePermit;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class InterStatePermitService extends
    CEIBaseService<InterStatePermit, InterStatePermitDTO> {

  @Autowired
  InterStatePermitManager manager;

  @Autowired
  InterStatePermitHelper helper;

  @Override
  public BaseManager<InterStatePermit> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<InterStatePermit, InterStatePermitDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.ISPERMIT;
  }
}