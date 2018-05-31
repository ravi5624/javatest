package nst.app.manager;

import nst.app.NewgenUtil;
import nst.app.common.CEIBaseManager;
import nst.app.helper.ContractorLicenseHelper;
import nst.app.model.forms.FormIEmployer;
import nst.app.model.forms.OrganizationDetails;
import nst.app.model.forms.lb.ContractorLicense;
import nst.app.model.forms.lb.FormI;
import nst.app.repo.ContractorLicenseRepository;
import nst.app.repo.FormIEmployerRepository;
import nst.app.repo.OrganizationDetailsRepository;
import nst.common.base.BaseRepository;
import nst.common.error.AppException;
import nst.kernal.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ContractorLicenseManager extends CEIBaseManager<ContractorLicense> {

  @Autowired
  ContractorLicenseRepository repository;

  @Autowired
  FormIEmployerRepository formIEmployerRepository;

  @Autowired
  OrganizationDetailsRepository organizationDetailsRepository;
  @Autowired
  NewgenUtil newgenUtil;
  @Autowired
  ContractorLicenseHelper helper;

  @Override
  public BaseRepository<ContractorLicense> getRepository() {
    return repository;
  }

  @Override
  public ContractorLicense submitForm(ContractorLicense model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }

  public OrganizationDetails addOrganization(Long id) {
    ContractorLicense form = findForm(id);
    OrganizationDetails organizationDetails = new OrganizationDetails(form.getForm());
    return organizationDetailsRepository.save(organizationDetails);
  }

  public OrganizationDetails findOrganization(Long id, Long formId) {
    OrganizationDetails organizationDetails = organizationDetailsRepository.findByIdAndForm_id(id, formId);
    if (Objects.isNull(organizationDetails)){
      throw AppException.createWithMessageCode(SystemConstants.Model.INVALID_REQUEST, SystemConstants.Model.INVALID_ORGANIZATION);
    }
    return organizationDetails;
  }

  public void deleteOrganization(OrganizationDetails organizationDetails) {
    organizationDetailsRepository.delete(organizationDetails);
  }

  public FormI getFormI(Long applicationId) {
    ContractorLicense contractorLicense = getCEIRepository().findByForm_id(applicationId);
    if (contractorLicense.getFormI() == null) {
      FormI formI = new FormI();
      formI.setForm(contractorLicense.getForm());
      formI.setTechnicalContractorName(contractorLicense.getApplicantContractorName());
      contractorLicense.setFormI(formI);
      save(contractorLicense);
    }
    return contractorLicense.getFormI();
  }

  public FormIEmployer addEmployer(Long applicationId) {
    FormI formI = getFormI(applicationId);
    FormIEmployer employer = new FormIEmployer();
    employer.setForm(formI.getForm());
    return formIEmployerRepository.save(employer);
  }
}