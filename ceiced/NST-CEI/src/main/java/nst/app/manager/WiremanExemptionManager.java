package nst.app.manager;

import nst.app.NewgenUtil;
import nst.app.common.CEIBaseManager;
import nst.app.helper.WiremanExemptionHelper;
import nst.app.model.forms.lb.WiremanExemption;
import nst.app.repo.WiremanExemptionRepository;
import nst.common.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WiremanExemptionManager extends CEIBaseManager<WiremanExemption> {

  @Autowired
  WiremanExemptionRepository repository;
  @Autowired
  NewgenUtil newgenUtil;
  @Autowired
  WiremanExemptionHelper helper;

  @Override
  public BaseRepository<WiremanExemption> getRepository() {
    return repository;
  }

  @Override
  public WiremanExemption submitForm(WiremanExemption model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }
}