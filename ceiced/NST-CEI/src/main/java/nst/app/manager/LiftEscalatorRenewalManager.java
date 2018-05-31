package nst.app.manager;

import java.util.List;
import nst.app.common.CEIBaseManager;
import nst.app.helper.LiftEscalatorRenewalHelper;
import nst.app.model.forms.le.LiftEscalatorRenewal;
import nst.app.repo.LiftEscalatorRenewalRepository;
import nst.common.base.BaseRepository;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LiftEscalatorRenewalManager extends CEIBaseManager<LiftEscalatorRenewal> {

  @Autowired
  LiftEscalatorRenewalRepository repository;

  @Autowired
  LiftEscalatorRenewalHelper helper;

  @Override
  public BaseRepository<LiftEscalatorRenewal> getRepository() {
    return repository;
  }

  @Override
  public LiftEscalatorRenewal submitForm(LiftEscalatorRenewal model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }

  public List<LiftEscalatorRenewal> getMyApps() {
    return repository.findMyApps(LoginUserUtil.getLoginUser().getUserId());
  }
}