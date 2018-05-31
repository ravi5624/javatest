package nst.app.manager;

import nst.app.NewgenUtil;
import nst.app.common.CEIBaseManager;
import nst.app.helper.InterStateRenewalHelper;
import nst.app.model.forms.lb.InterStateRenewal;
import nst.app.repo.InterStateRenewalRepository;
import nst.common.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InterStateRenewalManager extends CEIBaseManager<InterStateRenewal> {

  @Autowired
  InterStateRenewalRepository repository;
  @Autowired
  NewgenUtil newgenUtil;
  @Autowired
  InterStateRenewalHelper helper;

  @Override
  public BaseRepository<InterStateRenewal> getRepository() {
    return repository;
  }

  @Override
  public InterStateRenewal submitForm(InterStateRenewal model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }

}