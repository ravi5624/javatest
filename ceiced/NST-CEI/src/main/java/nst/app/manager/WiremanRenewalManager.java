package nst.app.manager;

import nst.app.NewgenUtil;
import nst.app.common.CEIBaseManager;
import nst.app.helper.WiremanRenewalHelper;
import nst.app.model.forms.lb.WiremanRenewal;
import nst.app.repo.WiremanRenewalRepository;
import nst.common.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WiremanRenewalManager extends CEIBaseManager<WiremanRenewal> {

  @Autowired
  WiremanRenewalRepository repository;

  @Autowired
  NewgenUtil newgenUtil;
  @Autowired
  WiremanRenewalHelper helper;

  @Override
  public BaseRepository<WiremanRenewal> getRepository() {
    return repository;
  }

  @Override
  public WiremanRenewal submitForm(WiremanRenewal model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }

}

