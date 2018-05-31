package nst.app.manager;

import nst.app.NewgenUtil;
import nst.app.common.CEIBaseManager;
import nst.app.helper.WiremanModificationHelper;
import nst.app.model.forms.lb.WiremanModification;
import nst.app.repo.WiremanModificationRepository;
import nst.common.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WiremanModificationManager extends CEIBaseManager<WiremanModification> {

  @Autowired
  WiremanModificationRepository repository;

  @Autowired
  NewgenUtil newgenUtil;
  @Autowired
  WiremanModificationHelper helper;

  @Override
  public BaseRepository<WiremanModification> getRepository() {
    return repository;
  }

  @Override
  public WiremanModification submitForm(WiremanModification model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }

}