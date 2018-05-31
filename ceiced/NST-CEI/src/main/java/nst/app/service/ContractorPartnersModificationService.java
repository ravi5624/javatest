package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.ContractorPartnersModificationDTO;
import nst.app.dto.OrganizationDetailsDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.ContractorPartnersModificationHelper;
import nst.app.helper.OrganizationDetailsHelper;
import nst.app.manager.ContractorPartnersModificationManager;
import nst.app.model.forms.lb.ContractorPartnersModification;
import nst.app.model.forms.le.EAndMAgencyLicense;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ContractorPartnersModificationService extends
    CEIBaseService<ContractorPartnersModification, ContractorPartnersModificationDTO> {

  @Autowired
  ContractorPartnersModificationManager manager;
  @Autowired
  OrganizationDetailsHelper organizationDetailsHelper;

  @Autowired
  ContractorPartnersModificationHelper helper;

  @Override
  public BaseManager<ContractorPartnersModification> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<ContractorPartnersModification, ContractorPartnersModificationDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.CONPARTMODI;
  }
  public OrganizationDetailsDTO addOrganization(Long id) {
    return organizationDetailsHelper.fromModel(manager.addOrganization(id));
  }

  public void deleteOrganization(Long id, Long orgId) {
    ContractorPartnersModification form = manager.findForm(id);
    form.getPartners().removeIf(org -> org.getId().equals(orgId));
    save(form);
  }
}