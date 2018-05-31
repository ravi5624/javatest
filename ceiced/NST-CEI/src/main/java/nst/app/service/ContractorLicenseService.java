package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.ContractorLicenseDTO;
import nst.app.dto.FormIDTO;
import nst.app.dto.FormIEmployerDTO;
import nst.app.dto.OrganizationDetailsDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.ContractorLicenseHelper;
import nst.app.helper.FormIEmployerHelper;
import nst.app.helper.OrganizationDetailsHelper;
import nst.app.manager.ContractorLicenseManager;
import nst.app.model.forms.FormIEmployer;
import nst.app.model.forms.OrganizationDetails;
import nst.app.model.forms.lb.ContractorLicense;
import nst.app.model.forms.le.CommonForm;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import nst.common.error.AppException;
import nst.kernal.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.stream.Collectors;
@Component
@Transactional
public class ContractorLicenseService extends
    CEIBaseService<ContractorLicense, ContractorLicenseDTO> {

  @Autowired
  ContractorLicenseManager manager;

  @Autowired
  ContractorLicenseHelper helper;

  @Autowired
  FormIEmployerHelper formIEmployerHelper;

  @Autowired
  OrganizationDetailsHelper organizationDetailsHelper;

  @Override
  public BaseManager<ContractorLicense> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<ContractorLicense, ContractorLicenseDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.CONLIC;
  }

  public OrganizationDetailsDTO addOrganization(Long id) {
    return organizationDetailsHelper.fromModel(manager.addOrganization(id));
  }

  public FormIDTO getFormI(Long id) {
    return helper.fromModel(manager.getFormI(id));
  }

  public FormIEmployerDTO addEmployer(Long id) {
    FormIEmployer employer = manager.addEmployer(id);
    return formIEmployerHelper.fromModel(employer);
  }

  public void deleteOrganization(Long orgId, Long formId) {
    CommonForm commonForm = getCommonForm(formId);
    if(Objects.isNull(commonForm)){
      throw AppException.createWithMessageCode(SystemConstants.BAD_REQUEST, SystemConstants.Model.INVALID_APPLICATION);
    }
    OrganizationDetails organizationDetails = manager.findOrganization(orgId, formId);
    if(Objects.isNull(organizationDetails)){
      throw AppException.createWithMessageCode(SystemConstants.BAD_REQUEST, SystemConstants.Model.INVALID_ORGANIZATION);
    }
    commonForm.getAttachments().stream()
            .filter(applicationAttachment -> applicationAttachment.getFieldIdentifier().endsWith("." + orgId))
            .map(applicationAttachment -> deleteAttachment(applicationAttachment)).collect(Collectors.toList());
    manager.deleteOrganization(organizationDetails);
  }
  public FormIDTO saveFormI(Long id, FormIDTO dto) {
    ContractorLicense contractorLicense = manager.get(id);
    helper.toModel(contractorLicense.getFormI(), dto);
    save(contractorLicense);
    return helper.fromModel(manager.getFormI(id));
  }
}