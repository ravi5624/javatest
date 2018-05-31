package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.LiftEscalatorRenewalDTO;
import nst.app.dto.newgen.NGLiftEscalatorRenewalDTO;
import nst.app.enums.AddressType;
import nst.app.enums.LiftEscalatorType;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.LiftEscalatorRenewal;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LiftEscalatorRenewalHelper extends
    BaseHelper<LiftEscalatorRenewal, LiftEscalatorRenewalDTO> {

  @Autowired
  AttachmentHelper attachmentHelper;

  @Autowired AddressHelper addressHelper;

  public LiftEscalatorRenewal toModel(LiftEscalatorRenewalDTO LiftEscalatorRenewalDTO) {
    LiftEscalatorRenewal portalUser = new LiftEscalatorRenewal();
    return toModel(portalUser, LiftEscalatorRenewalDTO);
  }

  @Override
  public LiftEscalatorRenewal toModel(LiftEscalatorRenewal model,
      LiftEscalatorRenewalDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);
    model.setLiftEscalatorType(LiftEscalatorType.fromType(dto.getLiftEscalatorType()));
    model.setLicenseNumber(dto.getLicenseNumber());
    model.setLicenseIssueDate(AllUtil.toUIDate(dto.getLicenseIssueDate()));
    model.setLicenseExpiryDate(AllUtil.toUIDate(dto.getLicenseExpiryDate()));
    model.setLicenseeFullName(dto.getLicenseeFullName());
    model.setIsAddressOrOwnerChange(HelperUtil.toBoolean(dto.getIsAddressOrOwnerChange()));
    model.getForm().setApplicantName(dto.getApplicantName());
//    addressHelper.manageAddress(model.getForm(), dto.getPremisesAddress());
    addressHelper.manageLEAddress(model.getForm(), dto.getLiftSiteAddress());
    return model;
  }

  public LiftEscalatorRenewal blankModel(Object portalUser) {
    LiftEscalatorRenewal ownerDetail = new LiftEscalatorRenewal();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public LiftEscalatorRenewalDTO fromModel(LiftEscalatorRenewal model) {
    LiftEscalatorRenewalDTO dto = new LiftEscalatorRenewalDTO();
    HelperUtil.toDTO(dto, model);
    dto.setLiftEscalatorType(HelperUtil.toEnumType(model.getLiftEscalatorType()));
    dto.setLicenseNumber(model.getLicenseNumber());
    dto.setLicenseIssueDate(AllUtil.formatUIDate(model.getLicenseIssueDate()));
    dto.setLicenseExpiryDate(AllUtil.formatUIDate(model.getLicenseExpiryDate()));
    dto.setLicenseeFullName(model.getLicenseeFullName());
    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));
    dto.setIsAddressOrOwnerChange(HelperUtil.fromBoolean(model.getIsAddressOrOwnerChange()));
    dto.setLiftSiteAddress(addressHelper.leAddressDTO(model.getForm().getAddressFor(AddressType.LIFTSITE)));
    return dto;
  }

  public NGLiftEscalatorRenewalDTO toNGDTO(LiftEscalatorRenewal model) {
    NGLiftEscalatorRenewalDTO dto = new NGLiftEscalatorRenewalDTO();
    HelperUtil.getNGDTO(model, dto);
    dto.setLicenseNumber(model.getLicenseNumber());
    dto.setLiftEscalatorType(HelperUtil.toEnumType(model.getLiftEscalatorType()));
    dto.setLicenseIssue(AllUtil.formatDate(model.getLicenseIssueDate()));
    dto.setLicenseExpiry(AllUtil.formatDate(model.getLicenseExpiryDate()));
    dto.setLicenseeFullName(model.getLicenseeFullName());
    dto.setIsAddressOrOwnerChange(model.getIsAddressOrOwnerChange());
    dto.setPremisesAddress(addressHelper.toLINGDTO(model.getForm().getAddressFor(AddressType.LIFTSITE)));
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));
    return dto;
  }
}