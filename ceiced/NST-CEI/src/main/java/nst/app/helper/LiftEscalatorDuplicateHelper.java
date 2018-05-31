package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.LiftEscalatorDuplicateDTO;
import nst.app.dto.newgen.NGLiftEscalatorDuplicateDTO;
import nst.app.enums.ApplicationType;
import nst.app.enums.LiftEscalatorType;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.LiftEscalatorDuplicate;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LiftEscalatorDuplicateHelper extends
    BaseHelper<LiftEscalatorDuplicate, LiftEscalatorDuplicateDTO> {

  @Autowired AttachmentHelper attachmentHelper;

  public LiftEscalatorDuplicate toModel(LiftEscalatorDuplicateDTO LiftEscalatorDuplicateDTO) {
    LiftEscalatorDuplicate portalUser = new LiftEscalatorDuplicate();
    return toModel(portalUser, LiftEscalatorDuplicateDTO);
  }

  @Override
  public LiftEscalatorDuplicate toModel(LiftEscalatorDuplicate model,
      LiftEscalatorDuplicateDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);
    model.setLiftEscalatorType(LiftEscalatorType.fromType(dto.getLiftEscalatorType()));
    model.setLicenseNumber(dto.getLicenseNumber());
    model.setExpiryDate(AllUtil.toUIDate(dto.getExpiryDate()));
    model.setIssueDate(AllUtil.toUIDate(dto.getIssueDate()));

    model.setReason(dto.getReason());
    model.setReasonIfOther(dto.getReasonIfOther());

    return model;
  }

  public LiftEscalatorDuplicate blankModel(Object portalUser) {
    LiftEscalatorDuplicate ownerDetail = new LiftEscalatorDuplicate();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    ownerDetail.getForm().setApplicationType(ApplicationType.LEDL);
    return ownerDetail;
  }

  public LiftEscalatorDuplicateDTO fromModel(LiftEscalatorDuplicate model) {
    LiftEscalatorDuplicateDTO dto = new LiftEscalatorDuplicateDTO();
    HelperUtil.toDTO(dto,model);
    dto.setLiftEscalatorType(HelperUtil.toEnumType(model.getLiftEscalatorType()));
    dto.setLicenseNumber(model.getLicenseNumber());
    dto.setIssueDate(AllUtil.formatUIDate(model.getIssueDate()));
    dto.setExpiryDate(AllUtil.formatUIDate(model.getExpiryDate()));
    dto.setReason(model.getReason());
    dto.setReasonIfOther(model.getReasonIfOther());
    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));
    return dto;
  }

  public NGLiftEscalatorDuplicateDTO toNGDTO(LiftEscalatorDuplicate model) {
    NGLiftEscalatorDuplicateDTO dto = new NGLiftEscalatorDuplicateDTO();
      HelperUtil.getNGDTO(model, dto);
      dto.setLiftEscalatorType(HelperUtil.toEnumType(model.getLiftEscalatorType()));
      dto.setLicenseNumber(model.getLicenseNumber());
      dto.setIssueDate(AllUtil.formatNGDate(model.getIssueDate()));
      dto.setExpiryDate(AllUtil.formatNGDate(model.getExpiryDate()));
      dto.setReason(model.getReason());
      dto.setReasonIfOther(model.getReasonIfOther());
      dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));
    return dto;
  }
}