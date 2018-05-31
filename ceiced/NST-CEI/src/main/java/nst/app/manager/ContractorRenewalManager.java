package nst.app.manager;

import nst.app.NewgenUtil;
import nst.app.common.CEIBaseManager;
import nst.app.helper.ContractorRenewalHelper;
import nst.app.model.forms.lb.ContractorRenewal;
import nst.app.repo.ContractorRenewalRepository;
import nst.common.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContractorRenewalManager extends CEIBaseManager<ContractorRenewal> {

  @Autowired
  ContractorRenewalRepository repository;
  @Autowired
  NewgenUtil newgenUtil;
  @Autowired
  ContractorRenewalHelper helper;
  @Override
  public BaseRepository<ContractorRenewal> getRepository() {
    return repository;
  }

  @Override
  public ContractorRenewal submitForm(ContractorRenewal model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }
}