package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.WiremanModificationDTO;
import nst.app.dto.newgen.NGWiremanModificationDTO;
import nst.app.enums.AddressType;
import nst.app.model.PortalUser;
import nst.app.model.forms.lb.WiremanModification;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WiremanModificationHelper extends
    BaseHelper<WiremanModification, WiremanModificationDTO> {

  @Autowired AttachmentHelper attachmentHelper;
  public WiremanModification toModel(WiremanModificationDTO WiremanModificationDTO) {
    WiremanModification portalUser = new WiremanModification();
    return toModel(portalUser, WiremanModificationDTO);
  }

  @Override
  public WiremanModification toModel(WiremanModification model,
      WiremanModificationDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);
    model.setSurname(dto.getSurname());
    model.setFirstName(dto.getFirstName());
    model.setMiddleName(dto.getMiddleName());
    model.setPermitNo(dto.getPermitNo());
    model.setIsNameCorrection(HelperUtil.toBoolean(dto.getIsNameCorrection()));
    model.setIsDOBCorrection(HelperUtil.toBoolean(dto.getIsDOBCorrection()));
    model.setBirthDate(AllUtil.toUIDate(dto.getBirthDate()));
    model.getForm().setApplicantName(dto.getApplicantName());
    return model;
  }


  public WiremanModification blankModel(Object portalUser) {
    WiremanModification ownerDetail = new WiremanModification();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public WiremanModificationDTO fromModel(WiremanModification model) {
    WiremanModificationDTO dto = new WiremanModificationDTO();
    HelperUtil.toDTO(dto, model);
    dto.setSurname(model.getSurname());
    dto.setFirstName(model.getFirstName());
    dto.setMiddleName(model.getMiddleName());
    dto.setPermitNo(model.getPermitNo());
    dto.setIsNameCorrection(HelperUtil.fromBoolean(model.getIsNameCorrection()));
    dto.setIsDOBCorrection(HelperUtil.fromBoolean(model.getIsDOBCorrection()));
    dto.setId(model.getApplicationId());
    dto.setType(model.getApplicationType().getType());
    dto.setApplicationName(model.getApplicationType().getName());
    dto.setBirthDate(AllUtil.formatUIDate(model.getBirthDate()));

    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));
    return dto;
  }

  public NGWiremanModificationDTO toNGDTO(WiremanModification model) {
    NGWiremanModificationDTO dto = new NGWiremanModificationDTO();
    HelperUtil.getNGDTO(model, dto);
    dto.setSurname(model.getSurname());
    dto.setFirstName(model.getFirstName());
    dto.setMiddleName(model.getMiddleName());
    dto.setPermitNo(model.getPermitNo());
    dto.setIsNameCorrection(model.getIsNameCorrection());
    dto.setIsDOBCorrection(model.getIsDOBCorrection());
    dto.setBirthDate(AllUtil.formatNGDate(model.getBirthDate()));
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));
    return dto;
  }
}