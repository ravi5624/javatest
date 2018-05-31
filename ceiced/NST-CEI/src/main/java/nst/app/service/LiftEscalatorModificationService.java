package nst.app.service;

import java.util.List;
import nst.app.common.CEIBaseService;
import nst.app.dto.LiftEscalatorModificationDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.LiftEscalatorModificationHelper;
import nst.app.manager.LiftEscalatorModificationManager;
import nst.app.model.forms.le.LiftEscalatorModification;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class LiftEscalatorModificationService extends
    CEIBaseService<LiftEscalatorModification, LiftEscalatorModificationDTO> {

  @Autowired
  LiftEscalatorModificationManager manager;

  @Autowired
  LiftEscalatorModificationHelper helper;

  public List<LiftEscalatorModificationDTO> getMyApps() {
    return helper.fromModel(manager.getMyApps());
  }

  @Override
  public BaseManager<LiftEscalatorModification> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<LiftEscalatorModification, LiftEscalatorModificationDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.LEML;
  }
}