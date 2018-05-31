package nst.app.manager;

import nst.app.NewgenUtil;
import nst.app.common.CEIBaseManager;
import nst.app.helper.ContractorModificationHelper;
import nst.app.model.forms.lb.ContractorModification;
import nst.app.repo.ContractorModificationRepository;
import nst.common.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContractorModificationManager extends CEIBaseManager<ContractorModification> {

  @Autowired
  ContractorModificationRepository repository;
  @Autowired
  NewgenUtil newgenUtil;
  @Autowired
  ContractorModificationHelper helper;

  @Override
  public BaseRepository<ContractorModification> getRepository() {
    return repository;
  }

  @Override
  public ContractorModification submitForm(ContractorModification model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }

}