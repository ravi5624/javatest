package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.ContractorModificationDTO;
import nst.app.dto.newgen.NGContractorModificationDTO;
import nst.app.enums.AddressType;
import nst.app.model.PortalUser;
import nst.app.model.forms.lb.ContractorModification;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContractorModificationHelper extends
    BaseHelper<ContractorModification, ContractorModificationDTO> {

  @Autowired
  AttachmentHelper attachmentHelper;

  @Autowired
  AddressHelper addressHelper;

  public ContractorModification toModel(ContractorModificationDTO ContractorModificationDTO) {
    ContractorModification portalUser = new ContractorModification();
    return toModel(portalUser, ContractorModificationDTO);
  }

  @Override
  public ContractorModification toModel(ContractorModification model,
      ContractorModificationDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);
    model.setApplicantContractorName(dto.getApplicantContractorName());
    model.setIsAddressChange(HelperUtil.toBoolean(dto.getIsAddressChange()));
    addressHelper.manageAddress(model.getForm(), dto.getOfficeAddress());
    model.setContractorLicenseNo(dto.getContractorLicenseNo());
    model.setLicenseNoIssueDate(AllUtil.toUIDate(dto.getLicenseNoIssueDate()));
    model.setIsNameCorrection(HelperUtil.toBoolean(dto.getIsNameCorrection()));

    model.getForm().setApplicantName(dto.getApplicantName());

    return model;
  }

  public ContractorModification blankModel(Object portalUser) {
    ContractorModification ownerDetail = new ContractorModification();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public ContractorModificationDTO fromModel(ContractorModification model) {
    ContractorModificationDTO dto = new ContractorModificationDTO();
    HelperUtil.toDTO(dto, model);
    dto.setApplicantContractorName(model.getApplicantContractorName());
    dto.setIsAddressChange(HelperUtil.fromBoolean(model.getIsAddressChange()));
    dto.setOfficeAddress(
        addressHelper.fromModel(model.getForm().getAddressFor(AddressType.OFFICE)));
    dto.setContractorLicenseNo(model.getContractorLicenseNo());
    dto.setLicenseNoIssueDate(AllUtil.formatUIDate(model.getLicenseNoIssueDate()));
    dto.setIsNameCorrection(HelperUtil.fromBoolean(model.getIsNameCorrection()));
    dto.setId(model.getApplicationId());
    dto.setType(model.getApplicationType().getType());
    dto.setApplicationName(model.getApplicationType().getName());
    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));
    return dto;
  }

  public NGContractorModificationDTO toNGDTO(ContractorModification model) {
    NGContractorModificationDTO dto = new NGContractorModificationDTO();
    HelperUtil.getNGDTO(model, dto);

    dto.setContractorName(model.getApplicantContractorName());
    dto.setIsAddressChange(model.getIsAddressChange());
    dto.setOfficeAddress(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.OFFICE)));
    dto.setContractorLicenseNo(model.getContractorLicenseNo());
    dto.setLicenseNoIssueDate(AllUtil.formatNGDate(model.getLicenseNoIssueDate()));
    dto.setIsNameCorrection(model.getIsNameCorrection());
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));

    return dto;
  }

}