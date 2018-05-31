package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.ContractorLicenseDTO;
import nst.app.dto.FormIDTO;
import nst.app.dto.newgen.NGContractorLicenseDTO;
import nst.app.enums.AddressType;
import nst.app.model.PortalUser;
import nst.app.model.forms.lb.ContractorLicense;
import nst.app.model.forms.lb.FormI;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class ContractorLicenseHelper extends
    BaseHelper<ContractorLicense, ContractorLicenseDTO> {

  @Autowired
  OrganizationDetailsHelper organizationDetailsHelper;

  @Autowired
  FormIHelper formIHelper;
  @Autowired
  AddressHelper addressHelper;
  @Autowired
  AttachmentHelper attachmentHelper;

  @Autowired
  FormIEmployerHelper formIEmployerHelper;

  public ContractorLicense toModel(ContractorLicenseDTO ContractorLicenseDTO) {
    ContractorLicense portalUser = new ContractorLicense();
    return toModel(portalUser, ContractorLicenseDTO);
  }

  @Override
  public ContractorLicense toModel(ContractorLicense model,
      ContractorLicenseDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);
    model.setContractorFromDate(AllUtil.toUIDate(dto.getContractorFromDate()));
    model.setApplicantContractorName(dto.getApplicantContractorName());
    model.setMobile(dto.getMobile());
    model.setEmail(dto.getEmail());
    addressHelper.manageAddress(model.getForm(), dto.getOfficeAddress());
    model.setOrganizationType(dto.getOrganizationType());
    model.setIsLicenseGranted(HelperUtil.toBoolean(dto.getIsLicenseGranted()));
    model.setContractorLicNo(dto.getContractorLicNo());
    model.setIssueDate(AllUtil.toUIDate(dto.getIssueDate()));
    model.setSupervisorName(dto.getSupervisorName());
    model.setSupervisorAge(dto.getSupervisorAge());
    model.setPermitNoOfSupervisor(dto.getPermitNoOfSupervisor());
    model.setSupervisorPermitIssueDate(AllUtil.toUIDate(dto.getSupervisorPermitIssueDate()));
    model.setSupervisorBirthDate(AllUtil.toUIDate(dto.getSupervisorBirthDate()));
    model.setContractorName(dto.getContractorName());
    model.setContractorLicenseNo(dto.getContractorLicenseNo());
    model.setContractorFromDate(AllUtil.toUIDate(dto.getContractorFromDate()));
    model.setContractorToDate(AllUtil.toUIDate(dto.getContractorToDate()));

    model.getForm().setApplicantName(dto.getApplicantName());

    if (CollectionUtils.isEmpty(dto.getOrganizations())) {
      model.getOrganizations().clear();
    } else {
      dto.getOrganizations().forEach(org -> {
        organizationDetailsHelper.toModel(model.myOrg(org.getId()), org);
      });
    }
    if (model.getFormI() != null){
      model.getFormI().setTechnicalContractorName(dto.getApplicantContractorName());
    }

    return model;
  }


  public ContractorLicense blankModel(Object portalUser) {
    ContractorLicense ownerDetail = new ContractorLicense();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public ContractorLicenseDTO fromModel(ContractorLicense model) {
    ContractorLicenseDTO dto = new ContractorLicenseDTO();
    HelperUtil.toDTO(dto, model);
    dto.setContractorFromDate(AllUtil.formatUIDate(model.getContractorFromDate()));
    dto.setApplicantContractorName(model.getApplicantContractorName());
    dto.setMobile(model.getMobile());
    dto.setEmail(model.getEmail());
    dto.setOfficeAddress(
        addressHelper.fromModel(model.getForm().getAddressFor(AddressType.OFFICE)));
    dto.setOrganizationType(model.getOrganizationType());
    dto.setIsLicenseGranted(HelperUtil.fromBoolean(model.getIsLicenseGranted()));
    dto.setContractorLicNo(model.getContractorLicNo());
    dto.setIssueDate(AllUtil.formatUIDate(model.getIssueDate()));
    dto.setSupervisorName(model.getSupervisorName());
    dto.setSupervisorAge(model.getSupervisorAge());
    dto.setPermitNoOfSupervisor(model.getPermitNoOfSupervisor());
    dto.setSupervisorPermitIssueDate(AllUtil.formatUIDate(model.getSupervisorPermitIssueDate()));
    dto.setSupervisorBirthDate(AllUtil.formatUIDate(model.getSupervisorBirthDate()));
    dto.setContractorName(model.getContractorName());
    dto.setContractorLicenseNo(model.getContractorLicenseNo());
    dto.setContractorToDate(AllUtil.formatUIDate(model.getContractorToDate()));
    dto.setOrganizations(organizationDetailsHelper.fromModel(model.getOrganizations()));
    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));
    if (model.getFormI() != null) {
      dto.setFormI(formIHelper.fromModel(model.getFormI()));
    }
    return dto;
  }

  public NGContractorLicenseDTO toNGDTO(ContractorLicense model) {
    NGContractorLicenseDTO dto = new NGContractorLicenseDTO();
    HelperUtil.getNGDTO(model, dto);
    dto.setContractorFromDate(AllUtil.formatNGDate(model.getContractorFromDate()));
    dto.setOfficeAddress(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.OFFICE)));
    dto.setApplicantContractorName(model.getApplicantContractorName());
    dto.setMobile(model.getMobile());
    dto.setEmail(model.getEmail());
    dto.setOrganizationType(model.getOrganizationType());
    dto.setIsLicenseGranted(model.getIsLicenseGranted());
    dto.setContractorLicNo(model.getContractorLicNo());
    dto.setIssueDate(AllUtil.formatNGDate(model.getIssueDate()));
    dto.setSupervisorName(model.getSupervisorName());
    dto.setSupervisorAge(model.getSupervisorAge());
    dto.setPermitNoOfSupervisor(model.getPermitNoOfSupervisor());
    dto.setSupervisorPermitIssueDate(AllUtil.formatNGDate(model.getSupervisorPermitIssueDate()));
    dto.setSupervisorBirthDate(AllUtil.formatNGDate(model.getSupervisorBirthDate()));
    dto.setContractorName(model.getContractorName());
    dto.setContractorLicenseNo(model.getContractorLicenseNo());
    dto.setContractorFromDate(AllUtil.formatNGDate(model.getContractorFromDate()));
    dto.setContractorToDate(AllUtil.formatNGDate(model.getContractorToDate()));
    dto.setOrganizations(organizationDetailsHelper.fromModelNG(model.getOrganizations()));
    if (model.getFormI() != null) {
      dto.setFormI(formIHelper.toNGDTO(model.getFormI()));
    }
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));
    return dto;
  }

  public FormI toModel(FormI model, FormIDTO dto) {
    model.setTechnicalContractorName(dto.getTechnicalContractorName());
    model.setIssueDate(AllUtil.toUIDate(dto.getIssueDate()));

    if (CollectionUtils.isEmpty(dto.getEmployer())) {
      model.getEmployer().clear();
    } else {
      dto.getEmployer().forEach(org -> {
        formIEmployerHelper.toModel(model.myEmp(org.getId()), org);
      });
    }
    /*if (CollectionUtils.isEmpty(dto.getEmployer())) {
      model.getEmployer().clear();
    } else {
      dto.getEmployer().forEach(exp -> {
        formIEmployerHelper.toModel(model.myEmp(exp.getId()), exp);
      });
    }*/
    return model;
  }

  public FormIDTO fromModel(FormI model) {
    FormIDTO dto = new FormIDTO();
    dto.setTechnicalContractorName(model.getTechnicalContractorName());
    dto.setIssueDate(AllUtil.formatUIDate(model.getIssueDate()));
    dto.setEmployer(formIEmployerHelper.fromModel(model.getEmployer()));
    return dto;
  }
}