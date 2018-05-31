package nst.app.manager;

import nst.app.NewgenUtil;
import nst.app.common.CEIBaseManager;
import nst.app.helper.ContractorPartnersModificationHelper;
import nst.app.model.forms.OrganizationDetails;
import nst.app.model.forms.lb.ContractorPartnersModification;
import nst.app.repo.ContractorPartnersModificationRepository;
import nst.app.repo.OrganizationDetailsRepository;
import nst.common.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContractorPartnersModificationManager extends CEIBaseManager<ContractorPartnersModification> {

  @Autowired
  ContractorPartnersModificationRepository repository;

  @Autowired
  OrganizationDetailsRepository organizationDetailsRepository;
  @Autowired
  NewgenUtil newgenUtil;
  @Autowired
  ContractorPartnersModificationHelper helper;
  @Override
  public BaseRepository<ContractorPartnersModification> getRepository() {
    return repository;
  }

  @Override
  public ContractorPartnersModification submitForm(ContractorPartnersModification model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }

  public OrganizationDetails addOrganization(Long id) {
    ContractorPartnersModification form = findForm(id);
    OrganizationDetails organizationDetails = new OrganizationDetails(form.getForm());

    System.out.println("organizationDetails: "+organizationDetails);

    return organizationDetailsRepository.save(organizationDetails);
  }

}