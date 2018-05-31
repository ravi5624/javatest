package nst.app.manager;

import nst.app.NewgenUtil;
import nst.app.common.CEIBaseManager;
import nst.app.helper.RepeaterHelper;
import nst.app.model.forms.lb.Repeater;
import nst.app.repo.RepeaterRepository;
import nst.common.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepeaterManager extends CEIBaseManager<Repeater> {

  @Autowired
  RepeaterRepository repository;
  @Autowired
  NewgenUtil newgenUtil;
  @Autowired
  RepeaterHelper helper;
  @Override
  public BaseRepository<Repeater> getRepository() {
    return repository;
  }

  @Override
  public Repeater submitForm(Repeater model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }

}