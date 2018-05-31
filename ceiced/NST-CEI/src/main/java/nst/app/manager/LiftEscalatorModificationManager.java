package nst.app.manager;

import java.util.List;
import nst.app.common.CEIBaseManager;
import nst.app.enums.FileStatus;
import nst.app.model.forms.le.LiftEscalatorModification;
import nst.app.repo.LiftEscalatorModificationRepository;
import nst.common.base.BaseRepository;
import nst.kernal.ActivityType;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LiftEscalatorModificationManager extends CEIBaseManager<LiftEscalatorModification> {

  @Autowired
  LiftEscalatorModificationRepository repository;

  @Override
  public BaseRepository<LiftEscalatorModification> getRepository() {
    return repository;
  }

  @Override
  public LiftEscalatorModification submitForm(LiftEscalatorModification model) {
    validatePayment(model.getApplicationId(), model.getApplicationType());
    model.getForm().setFileStatus(FileStatus.SUBMITTED);
    printLog(ActivityType.SUBMITTED, model.getId(), "1","2");

    return repository.save(model);
  }


  public List<LiftEscalatorModification> getMyApps() {
    return repository.findMyApps(LoginUserUtil.getLoginUser().getUserId());
  }

}