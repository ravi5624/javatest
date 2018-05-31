package nst.app.service;

import java.util.List;
import nst.app.common.CEIBaseService;
import nst.app.dto.LiftEscalatorDuplicateDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.LiftEscalatorDuplicateHelper;
import nst.app.manager.LiftEscalatorDuplicateManager;
import nst.app.model.forms.le.LiftEscalatorDuplicate;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class LiftEscalatorDuplicateService extends
    CEIBaseService<LiftEscalatorDuplicate, LiftEscalatorDuplicateDTO> {

  @Autowired
  LiftEscalatorDuplicateManager manager;

  @Autowired
  LiftEscalatorDuplicateHelper helper;

  public List<LiftEscalatorDuplicateDTO> getMyApps() {
    return helper.fromModel(manager.getMyApps());
  }

  @Override
  public BaseManager<LiftEscalatorDuplicate> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<LiftEscalatorDuplicate, LiftEscalatorDuplicateDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.LEDL;
  }
}