package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.WiremanModificationDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.WiremanModificationHelper;
import nst.app.manager.WiremanModificationManager;
import nst.app.model.forms.lb.WiremanModification;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class WiremanModificationService extends
    CEIBaseService<WiremanModification, WiremanModificationDTO> {

  @Autowired
  WiremanModificationManager manager;

  @Autowired
  WiremanModificationHelper helper;

  @Override
  public BaseManager<WiremanModification> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<WiremanModification, WiremanModificationDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.WIRMODI;
  }
}