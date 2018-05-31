package nst.app.manager;

import nst.app.NewgenUtil;
import nst.app.common.CEIBaseManager;
import nst.app.helper.SupervisorModificationHelper;
import nst.app.model.forms.lb.SupervisorModification;
import nst.app.repo.SupervisorModificationRepository;
import nst.common.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupervisorModificationManager extends CEIBaseManager<SupervisorModification> {

  @Autowired
  SupervisorModificationRepository repository;


  @Autowired
  NewgenUtil newgenUtil;
  @Autowired
  SupervisorModificationHelper helper;


  @Override
  public BaseRepository<SupervisorModification> getRepository() {
    return repository;
  }

  @Override
  public SupervisorModification submitForm(SupervisorModification model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }

}