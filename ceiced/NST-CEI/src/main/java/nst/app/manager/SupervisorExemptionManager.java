package nst.app.manager;

import java.util.List;
import nst.app.NewgenUtil;
import nst.app.common.CEIBaseManager;
import nst.app.helper.SupervisorExemptionHelper;
import nst.app.model.forms.lb.SupervisorExemption;
import nst.app.repo.SupervisorExemptionRepository;
import nst.common.base.BaseRepository;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupervisorExemptionManager extends CEIBaseManager<SupervisorExemption> {

  @Autowired
  SupervisorExemptionRepository repository;
  @Autowired
  NewgenUtil newgenUtil;
  @Autowired
  SupervisorExemptionHelper helper;

  public Iterable<SupervisorExemption> getAll() {
    return repository.findAll();
  }

  @Override
  public BaseRepository<SupervisorExemption> getRepository() {
    return repository;
  }

  public List<SupervisorExemption> getMyApps() {
    return repository.findMyApps(LoginUserUtil.getLoginUser().getUserId());
  }

  @Override
  public SupervisorExemption submitForm(SupervisorExemption model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }
}