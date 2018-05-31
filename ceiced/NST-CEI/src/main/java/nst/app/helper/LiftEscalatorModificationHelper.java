package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.LiftEscalatorModificationDTO;
import nst.app.dto.newgen.NGLiftEscalatorModificationDTO;
import nst.app.enums.AddressType;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.LiftEscalatorModification;
import nst.common.base.BaseHelper;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LiftEscalatorModificationHelper extends
    BaseHelper<LiftEscalatorModification, LiftEscalatorModificationDTO> {

  @Autowired AttachmentHelper attachmentHelper;
  @Autowired AddressHelper addressHelper;

  public LiftEscalatorModification toModel(
      LiftEscalatorModificationDTO LiftEscalatorModificationDTO) {
    LiftEscalatorModification portalUser = new LiftEscalatorModification();
    return toModel(portalUser, LiftEscalatorModificationDTO);
  }

  @Override
  public LiftEscalatorModification toModel(LiftEscalatorModification model,
      LiftEscalatorModificationDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);
    model.setOwnerName(dto.getOwnerName());
    model.setOwnerEmail(dto.getOwnerEmail());
    model.setOwnerContactNo(dto.getOwnerContactNo());
    model.setChangeBuildingName(dto.getChangeBuildingName());
    model.setIsChangeAgency(HelperUtil.toBoolean(dto.getIsChangeAgency()));
    model.setInstallerPersonName(dto.getInstallerPersonName());
    model.setInstallerPersonEmail(dto.getInstallerPersonEmail());
    model.setInstallerPersonContactNo(dto.getInstallerPersonContactNo());
    model.getForm().setApplicantName(dto.getApplicantName());
    addressHelper.manageAddress(model.getForm(), dto.getOwnerAddress());
    addressHelper.manageAddress(model.getForm(), dto.getInstallerPesonAddress());

    return model;
  }

  public LiftEscalatorModification blankModel(Object portalUser) {
    LiftEscalatorModification ownerDetail = new LiftEscalatorModification();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public LiftEscalatorModificationDTO fromModel(LiftEscalatorModification model) {
    LiftEscalatorModificationDTO dto = new LiftEscalatorModificationDTO();
    HelperUtil.toDTO(dto,model);
    dto.setOwnerName(model.getOwnerName());
    dto.setOwnerEmail(model.getOwnerEmail());
    dto.setOwnerContactNo(model.getOwnerContactNo());
    dto.setChangeBuildingName(model.getChangeBuildingName());
    dto.setIsChangeAgency(HelperUtil.fromBoolean(model.getIsChangeAgency()));
    dto.setInstallerPersonName(model.getInstallerPersonName());
    dto.setInstallerPersonEmail(model.getInstallerPersonEmail());
    dto.setInstallerPersonContactNo(model.getInstallerPersonContactNo());
    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));
    dto.setOwnerAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.OWNER)));
    dto.setInstallerPesonAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.LIFTINSTALLER)));
    return dto;
  }


  public NGLiftEscalatorModificationDTO toNGDTO(LiftEscalatorModification model) {
    NGLiftEscalatorModificationDTO dto = new NGLiftEscalatorModificationDTO();
    HelperUtil.getNGDTO(model,dto);
    dto.setOwnerName(model.getOwnerName());
    dto.setOwnerEmail(model.getOwnerEmail());
    dto.setOwnerContactNo(model.getOwnerContactNo());
    dto.setChangeBuildingName(model.getChangeBuildingName());
    dto.setIsChangeAgency(HelperUtil.fromBoolean(model.getIsChangeAgency()));
    dto.setInstallerPersonName(model.getInstallerPersonName());
    dto.setInstallerPersonEmail(model.getInstallerPersonEmail());
    dto.setInstallerPersonContactNo(model.getInstallerPersonContactNo());
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));
    dto.setOwnerAddress(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.OWNER)));
    dto.setInstallerPersonAddress(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.LIFTINSTALLER)));

    return dto;
  }

}