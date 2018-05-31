package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.RepeaterDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.RepeaterHelper;
import nst.app.manager.RepeaterManager;
import nst.app.model.forms.lb.Repeater;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class RepeaterService extends
    CEIBaseService<Repeater, RepeaterDTO> {

  @Autowired
  RepeaterManager manager;

  @Autowired
  RepeaterHelper helper;

  @Override
  public BaseManager<Repeater> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<Repeater, RepeaterDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.REPEATER;
  }
}