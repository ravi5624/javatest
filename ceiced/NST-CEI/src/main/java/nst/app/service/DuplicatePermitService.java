package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.DuplicatePermitDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.DuplicatePermitHelper;
import nst.app.manager.DuplicatePermitManager;
import nst.app.model.forms.lb.DuplicatePermit;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class DuplicatePermitService extends
    CEIBaseService<DuplicatePermit, DuplicatePermitDTO> {

  @Autowired
  DuplicatePermitManager manager;

  @Autowired
  DuplicatePermitHelper helper;

  @Override
  public BaseManager<DuplicatePermit> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<DuplicatePermit, DuplicatePermitDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.DUPPER;
  }
}