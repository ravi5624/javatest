package nst.app.helper;


import nst.app.common.HelperUtil;
import nst.app.dto.ContractorRenewalDTO;
import nst.app.dto.newgen.NGContractorRenewalDTO;
import nst.app.enums.AddressType;
import nst.app.model.PortalUser;
import nst.app.model.forms.lb.ContractorRenewal;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContractorRenewalHelper extends
    BaseHelper<ContractorRenewal, ContractorRenewalDTO> {
  @Autowired
  AddressHelper addressHelper;
  @Autowired
  AttachmentHelper attachmentHelper;

  public ContractorRenewal toModel(ContractorRenewalDTO ContractorRenewalDTO) {
    ContractorRenewal portalUser = new ContractorRenewal();
    return toModel(portalUser, ContractorRenewalDTO);
  }

  @Override
  public ContractorRenewal toModel(ContractorRenewal model,
      ContractorRenewalDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);
    model.setContractorName(dto.getContractorName());
    model.setIsAddressChange(HelperUtil.toBoolean(dto.getIsAddressChange()));
    model.setContractorLicNo(dto.getContractorLicNo());
    model.setIssueDate(AllUtil.toUIDate(dto.getIssueDate()));
    model.setLicenseExpiryDate(AllUtil.toUIDate(dto.getLicenseExpiryDate()));
    model.setOldSupervisorName(dto.getOldSupervisorName());
    model.setOldSupervisorBirthDate(AllUtil.toUIDate(dto.getOldSupervisorBirthDate()));
    model.setOldSupervisorJoineeDate(AllUtil.toUIDate(dto.getOldSupervisorJoineeDate()));
    model.setOldSupervisorLeavingDate(AllUtil.toUIDate(dto.getOldSupervisorLeavingDate()));
    model.setIsWorkingOldSupervisor(HelperUtil.toBoolean(dto.getIsWorkingOldSupervisor()));
    model.setNewSupervisorName(dto.getNewSupervisorName());
    model.setNewSupervisorBirthDate(AllUtil.toUIDate(dto.getNewSupervisorBirthDate()));
    model.setNewSupervisorJoineeDate(AllUtil.toUIDate(dto.getNewSupervisorJoineeDate()));
    model.setIsAddressChange(HelperUtil.toBoolean(dto.getIsAddressChange()));

    model.getForm().setApplicantName(dto.getApplicantName());

    addressHelper.manageAddress(model.getForm(), dto.getParmanentAddress());
    return model;
  }

  public ContractorRenewal blankModel(Object portalUser) {
    ContractorRenewal ownerDetail = new ContractorRenewal();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public ContractorRenewalDTO fromModel(ContractorRenewal model) {
    ContractorRenewalDTO dto = new ContractorRenewalDTO();
    HelperUtil.toDTO(dto, model);
    dto.setContractorName(model.getContractorName());
    dto.setIsAddressChange(HelperUtil.fromBoolean(model.getIsAddressChange()));
    dto.setContractorLicNo(model.getContractorLicNo());
    dto.setIssueDate(AllUtil.formatUIDate(model.getIssueDate()));
    dto.setLicenseExpiryDate(AllUtil.formatUIDate(model.getLicenseExpiryDate()));
    dto.setOldSupervisorName(model.getOldSupervisorName());
    dto.setOldSupervisorBirthDate(AllUtil.formatUIDate(model.getOldSupervisorBirthDate()));
    dto.setOldSupervisorJoineeDate(AllUtil.formatUIDate(model.getOldSupervisorJoineeDate()));
    dto.setOldSupervisorLeavingDate(AllUtil.formatUIDate(model.getOldSupervisorLeavingDate()));
    dto.setIsWorkingOldSupervisor(HelperUtil.fromBoolean(model.getIsWorkingOldSupervisor()));
    dto.setNewSupervisorName(model.getNewSupervisorName());
    dto.setNewSupervisorBirthDate(AllUtil.formatUIDate(model.getNewSupervisorBirthDate()));
    dto.setNewSupervisorJoineeDate(AllUtil.formatUIDate(model.getNewSupervisorJoineeDate()));
    dto.setIsAddressChange(HelperUtil.fromBoolean(model.getIsAddressChange()));
    dto.setParmanentAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.PERMANENT)));
    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));

    return dto;
  }

  public NGContractorRenewalDTO toNGDTO(ContractorRenewal model) {
    NGContractorRenewalDTO dto = new NGContractorRenewalDTO();
    HelperUtil.getNGDTO(model, dto);
    dto.setContractorName(model.getContractorName());
    dto.setIsAddressChange(model.getIsAddressChange());
    dto.setContractorLicNo(model.getContractorLicNo());
    dto.setIssueDate(AllUtil.formatNGDate(model.getIssueDate()));
    dto.setLicenseExpiryDate(AllUtil.formatNGDate(model.getLicenseExpiryDate()));
    dto.setOldSupervisorName(model.getOldSupervisorName());
    dto.setOldSupervisorBirthDate(AllUtil.formatNGDate(model.getOldSupervisorBirthDate()));
    dto.setOldSupervisorJoineeDate(AllUtil.formatNGDate(model.getOldSupervisorJoineeDate()));
    dto.setOldSupervisorLeavingDate(AllUtil.formatNGDate(model.getOldSupervisorLeavingDate()));
    dto.setIsWorkingOldSupervisor(model.getIsWorkingOldSupervisor());
    dto.setNewSupervisorName(model.getNewSupervisorName());
    dto.setNewSupervisorBirthDate(AllUtil.formatNGDate(model.getNewSupervisorBirthDate()));
    dto.setNewSupervisorJoineeDate(AllUtil.formatNGDate(model.getNewSupervisorJoineeDate()));
    dto.setIsAddressChange(model.getIsAddressChange());
    dto.setParmanentAddress(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.PERMANENT)));
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));

    return dto;
  }

}