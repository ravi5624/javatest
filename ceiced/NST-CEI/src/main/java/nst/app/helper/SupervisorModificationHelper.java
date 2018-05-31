package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.SupervisorModificationDTO;
import nst.app.dto.newgen.NGSupervisorModificationDTO;
import nst.app.enums.AddressType;
import nst.app.model.PortalUser;
import nst.app.model.forms.lb.SupervisorModification;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupervisorModificationHelper extends
    BaseHelper<SupervisorModification, SupervisorModificationDTO> {

  @Autowired  AddressHelper addressHelper;

  @Autowired  AttachmentHelper attachmentHelper;



  public SupervisorModification toModel(SupervisorModificationDTO SupervisorModificationDTO) {
    SupervisorModification portalUser = new SupervisorModification();
    return toModel(portalUser, SupervisorModificationDTO);
  }

  @Override
  public SupervisorModification toModel(SupervisorModification model,
                                        SupervisorModificationDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);
    model.setSurname(dto.getSurname());
    model.setFirstName(dto.getFirstName());
    model.setMiddleName(dto.getMiddleName());
    model.setPermitNo(dto.getPermitNo());
    model.setIsDOBCorrection(HelperUtil.toBoolean(dto.getIsDOBCorrection()));
    model.setIsNameCorrection(HelperUtil.toBoolean(dto.getIsNameCorrection()));
    model.setBirthDate(AllUtil.toUIDate(dto.getBirthDate()));
    model.getForm().setApplicantName(dto.getApplicantName());
    return model;
  }

  public SupervisorModification blankModel(Object portalUser) {
    SupervisorModification ownerDetail = new SupervisorModification();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public SupervisorModificationDTO fromModel(SupervisorModification model) {
    SupervisorModificationDTO dto = new SupervisorModificationDTO();
    HelperUtil.toDTO(dto, model);
    dto.setSurname(model.getSurname());
    dto.setFirstName(model.getFirstName());
    dto.setMiddleName(model.getMiddleName());
    dto.setPermitNo(model.getPermitNo());
    dto.setIsDOBCorrection(HelperUtil.fromBoolean(model.getIsDOBCorrection()));
    dto.setIsNameCorrection(HelperUtil.fromBoolean(model.getIsNameCorrection()));
    dto.setBirthDate(AllUtil.formatUIDate(model.getBirthDate()));
    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));

    return dto;
  }

  public NGSupervisorModificationDTO toNGDTO(SupervisorModification model) {
    NGSupervisorModificationDTO dto = new NGSupervisorModificationDTO();
    HelperUtil.getNGDTO(model, dto);
    dto.setSurname(model.getSurname());
    dto.setFirstName(model.getFirstName());
    dto.setMiddleName(model.getMiddleName());
    dto.setPermitNo(model.getPermitNo());
    dto.setIsDOBCorrection(model.getIsDOBCorrection());
    dto.setIsNameCorrection(model.getIsNameCorrection());
    dto.setBirthDate(AllUtil.formatNGDate(model.getBirthDate()));
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));
    return dto;
  }
}