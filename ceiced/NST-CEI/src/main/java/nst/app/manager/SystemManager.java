package nst.app.manager;

import nst.app.enums.ApplicationType;
import nst.app.model.ApplicationFees;
import nst.app.repo.ApplicationDateRepository;
import nst.app.repo.ApplicationFeesRepository;
import nst.common.Manager;
import nst.common.error.AppException;
import nst.kernal.SystemConstants;
import nst.util.AllUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SystemManager implements Manager {

  @Autowired
  protected ApplicationFeesRepository feesRepository;

  @Autowired
  protected ApplicationDateRepository dateRepository;

  public ApplicationFees findFees(ApplicationType applicationType) {
    return feesRepository.loadFees(applicationType.getType(), AllUtil.getDayStart());
  }

  public void isEnabled(ApplicationType applicationType) {
    if (dateRepository.loadDate(applicationType.getType(), AllUtil.getDayStart()) == null){
      throw AppException.createWithMessage(SystemConstants.Model.INVALID_REQUEST,"Not Enabled.");
    }
  }
}
