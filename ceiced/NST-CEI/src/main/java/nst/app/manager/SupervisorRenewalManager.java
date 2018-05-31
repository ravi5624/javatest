package nst.app.manager;

import nst.app.NewgenUtil;
import nst.app.common.CEIBaseManager;
import nst.app.helper.SupervisorRenewalHelper;
import nst.app.model.forms.lb.SupervisorRenewal;
import nst.app.repo.SupervisorRenewalRepository;
import nst.common.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupervisorRenewalManager extends CEIBaseManager<SupervisorRenewal> {

  @Autowired
  SupervisorRenewalRepository repository;

  @Autowired
  NewgenUtil newgenUtil;
  @Autowired
  SupervisorRenewalHelper helper;

  @Override
  public BaseRepository<SupervisorRenewal> getRepository() {
    return repository;
  }

  @Override
  public SupervisorRenewal submitForm(SupervisorRenewal model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }

}