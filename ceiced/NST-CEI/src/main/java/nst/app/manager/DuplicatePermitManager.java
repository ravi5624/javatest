package nst.app.manager;

import nst.app.NewgenUtil;
import nst.app.common.CEIBaseManager;
import nst.app.helper.DuplicatePermitHelper;
import nst.app.model.forms.lb.DuplicatePermit;
import nst.app.repo.DuplicatePermitRepository;
import nst.common.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DuplicatePermitManager extends CEIBaseManager<DuplicatePermit> {

  @Autowired
  DuplicatePermitRepository repository;
  @Autowired
  NewgenUtil newgenUtil;
  @Autowired
  DuplicatePermitHelper helper;
  @Override
  public BaseRepository<DuplicatePermit> getRepository() {
    return repository;
  }

  @Override
  public DuplicatePermit submitForm(DuplicatePermit model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }

}