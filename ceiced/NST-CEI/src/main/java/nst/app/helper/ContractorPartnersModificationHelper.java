package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.ContractorPartnersModificationDTO;
import nst.app.dto.newgen.NGContractorPartnersModificationDTO;
import nst.app.model.PortalUser;
import nst.app.model.forms.lb.ContractorPartnersModification;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class ContractorPartnersModificationHelper extends
    BaseHelper<ContractorPartnersModification, ContractorPartnersModificationDTO> {


  @Autowired
  OrganizationDetailsHelper organizationDetailsHelper;

  @Autowired
  AttachmentHelper attachmentHelper;

  public ContractorPartnersModification toModel(
      ContractorPartnersModificationDTO ContractorPartnersModificationDTO) {
    ContractorPartnersModification portalUser = new ContractorPartnersModification();
    return toModel(portalUser, ContractorPartnersModificationDTO);
  }

  @Override
  public ContractorPartnersModification toModel(ContractorPartnersModification model,
      ContractorPartnersModificationDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);
    model.setApplicantContractorName(dto.getApplicantContractorName());
    model.setContractorLicenseNo(dto.getContractorLicenseNo());
    model.setLicenseIssueDate(AllUtil.toUIDate(dto.getLicenseIssueDate()));
    model.setOrganizationType(dto.getOrganizationType());

    model.getForm().setApplicantName(dto.getApplicantName());

    if (CollectionUtils.isEmpty(dto.getPartners())) {
      model.getPartners().clear();
    } else {
      dto.getPartners().forEach(org -> {
        organizationDetailsHelper.toModel(model.myOrg(org.getId()), org);
      });
    }
    return model;
  }

  public ContractorPartnersModification blankModel(Object portalUser) {
    ContractorPartnersModification ownerDetail = new ContractorPartnersModification();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public ContractorPartnersModificationDTO fromModel(ContractorPartnersModification model) {
    ContractorPartnersModificationDTO dto = new ContractorPartnersModificationDTO();
    HelperUtil.toDTO(dto, model);
    dto.setApplicantContractorName(model.getApplicantContractorName());
    dto.setContractorLicenseNo(model.getContractorLicenseNo());
    dto.setLicenseIssueDate(AllUtil.formatUIDate(model.getLicenseIssueDate()));
    dto.setOrganizationType(model.getOrganizationType());
    dto.setPartners(organizationDetailsHelper.fromModel(model.getPartners()));
    dto.setId(model.getApplicationId());
    dto.setType(model.getApplicationType().getType());
    dto.setApplicationName(model.getApplicationType().getName());
    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));
    return dto;
  }

  public NGContractorPartnersModificationDTO toNGDTO(ContractorPartnersModification model) {
    NGContractorPartnersModificationDTO dto = new NGContractorPartnersModificationDTO();
    HelperUtil.getNGDTO(model, dto);
    dto.setContractorName(model.getApplicantContractorName());
    dto.setContractorLicenseNo(model.getContractorLicenseNo());
    dto.setLicenseIssueDate(AllUtil.formatNGDate(model.getLicenseIssueDate()));
    dto.setOrganizationType(model.getOrganizationType());
    dto.setPartners(organizationDetailsHelper.fromModelNG(model.getPartners()));
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));
    return dto;
  }
}