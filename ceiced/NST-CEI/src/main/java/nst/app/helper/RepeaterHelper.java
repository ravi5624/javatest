package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.RepeaterDTO;
import nst.app.dto.newgen.NGRepeaterDTO;
import nst.app.model.PortalUser;
import nst.app.model.forms.lb.Repeater;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepeaterHelper extends
    BaseHelper<Repeater, RepeaterDTO> {

  @Autowired AttachmentHelper attachmentHelper;

  public Repeater toModel(RepeaterDTO RepeaterDTO) {
    Repeater portalUser = new Repeater();
    return toModel(portalUser, RepeaterDTO);
  }

  @Override
  public Repeater toModel(Repeater model,
      RepeaterDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);
    model.setSurname(dto.getSurname());
    model.setFirstName(dto.getFirstName());
    model.setMiddleName(dto.getMiddleName());
    model.setBirthDate(AllUtil.toUIDate(dto.getBirthDate()));
    model.setAge(dto.getAge());
    model.setExamType(dto.getExamType());
    model.setPrevRollNo(dto.getPrevRollNo());
    model.setPrevCentre(dto.getPrevCentre());
    model.setPrevExamDateMonthYear(AllUtil.toUIDate(dto.getPrevExamDateMonthYear()));
    model.setPreferredExamCentre(dto.getPreferredExamCentre());
    model.setPreferredLangMode(dto.getPreferredLangMode());

    model.getForm().setApplicantName(dto.getApplicantName());

    return model;
  }

  public Repeater blankModel(Object portalUser) {
    Repeater ownerDetail = new Repeater();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public RepeaterDTO fromModel(Repeater model) {
    RepeaterDTO dto = new RepeaterDTO();
    HelperUtil.toDTO(dto, model);
    dto.setSurname(model.getSurname());
    dto.setFirstName(model.getFirstName());
    dto.setMiddleName(model.getMiddleName());
    dto.setBirthDate(AllUtil.formatUIDate(model.getBirthDate()));
    dto.setAge(model.getAge());
    dto.setExamType(model.getExamType());
    dto.setPrevRollNo(model.getPrevRollNo());
    dto.setPrevCentre(model.getPrevCentre());
    dto.setPrevExamDateMonthYear(AllUtil.formatUIDate(model.getPrevExamDateMonthYear()));
    dto.setPreferredExamCentre(model.getPreferredExamCentre());
    dto.setPreferredLangMode(model.getPreferredLangMode());
    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));
    return dto;
  }

  public NGRepeaterDTO toNGDTO(Repeater model) {
    NGRepeaterDTO dto = new NGRepeaterDTO();
    HelperUtil.getNGDTO(model, dto);

    dto.setSurname(model.getSurname());
    dto.setFirstName(model.getFirstName());
    dto.setMiddleName(model.getMiddleName());
    dto.setApplicantName(String.format("%s %s %s", model.getSurname(), model.getFirstName(), model.getMiddleName()));
    dto.setBirthDate(AllUtil.formatNGDate(model.getBirthDate()));
    dto.setAge(model.getAge());
    dto.setExamType(model.getExamType());
    dto.setPrevRollNo(model.getPrevRollNo());
    dto.setPrevCentre(model.getPrevCentre());
    dto.setPrevExamDateMonthYear(AllUtil.formatNGDate(model.getPrevExamDateMonthYear()));
    dto.setPreferredExamCentre(model.getPreferredExamCentre());
    dto.setPreferredLangMode(model.getPreferredLangMode());
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));
    return dto;
  }

}