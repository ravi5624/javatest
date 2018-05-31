package nst.app.manager;

import nst.app.NewgenUtil;
import nst.app.common.CEIBaseManager;
import nst.app.helper.InterStatePermitHelper;
import nst.app.model.forms.lb.InterStatePermit;
import nst.app.repo.InterStatePermitRepository;
import nst.common.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InterStatePermitManager extends CEIBaseManager<InterStatePermit> {

  @Autowired
  InterStatePermitRepository repository;
  @Autowired
  NewgenUtil newgenUtil;
  @Autowired
  InterStatePermitHelper helper;
  @Override
  public BaseRepository<InterStatePermit> getRepository() {
    return repository;
  }

  @Override
  public InterStatePermit submitForm(InterStatePermit model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }
}