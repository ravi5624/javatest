package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.SupervisorExaminationDTO;
import nst.app.dto.newgen.NGSupervisorExaminationDTO;
import nst.app.enums.AddressType;
import nst.app.model.PortalUser;
import nst.app.model.forms.lb.SupervisorExamination;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class SupervisorExaminationHelper extends
    BaseHelper<SupervisorExamination, SupervisorExaminationDTO> {

  @Autowired  ExperienceHelper experieceHelper;
  @Autowired AddressHelper addressHelper;
  @Autowired AttachmentHelper attachmentHelper;

  public SupervisorExamination toModel(SupervisorExaminationDTO SupervisorExaminationDTO) {
    SupervisorExamination portalUser = new SupervisorExamination();
    return toModel(portalUser, SupervisorExaminationDTO);
  }

  @Override
  public SupervisorExamination toModel(SupervisorExamination model,
                                       SupervisorExaminationDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);
    model.setSurname(dto.getSurname());
    model.setFirstName(dto.getFirstName());
    model.setMiddleName(dto.getMiddlename());
    model.setBirthDate(AllUtil.toUIDate(dto.getBirthDate()));
    model.setAge(dto.getAge());
    model.setExamType(dto.getExamType());

    addressHelper.manageAddress(model.getForm(), dto.getParmanentAddress());
    addressHelper.manageAddress(model.getForm(), dto.getTemporaryAddress());
    model.setPermitIssueDate(AllUtil.toUIDate(dto.getPermitIssueDate()));
    model.setGender(dto.getGender());
    model.setMobile(dto.getMobile());
    model.setAltMobile(dto.getAltMobileNumber());
    model.setEmail(dto.getEmail());
    model.setPermitNo(dto.getPermitNo());
    model.setSupExperience(dto.getSupExperience());

    model.getForm().setApplicantName(dto.getApplicantName());

    if (CollectionUtils.isEmpty(dto.getExperiences())) {
      model.getExperiences().clear();
    } else {
      dto.getExperiences().forEach(exp -> {
        experieceHelper.toModel(model.myExp(exp.getId()), exp);
      });
    }

    model.setPreferredExamCentre(dto.getPreferredExamCentre());
    model.setPreferredLangMode(dto.getPreferredLangMode());
    model.setTotalExperience(dto.getTotalExperience());
    model.setWhetherWiremanPermit(dto.getWhetherWiremanPermit());
    return model;

  }

  public SupervisorExamination blankModel(Object portalUser) {
    SupervisorExamination ownerDetail = new SupervisorExamination();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public SupervisorExaminationDTO fromModel(SupervisorExamination model) {
    SupervisorExaminationDTO dto = new SupervisorExaminationDTO();
    HelperUtil.toDTO(dto, model);
    dto.setSurname(model.getSurname());
    dto.setFirstName(model.getFirstName());
    dto.setMiddlename(model.getMiddleName());
    dto.setBirthDate(AllUtil.formatUIDate(model.getBirthDate()));
    dto.setAge(model.getAge());
    dto.setGender(model.getGender());
    dto.setExamType(model.getExamType());
    dto.setParmanentAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.PERMANENT)));
    dto.setTemporaryAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.TEMPORARY)));
    dto.setMobile(model.getMobile());
    dto.setAltMobileNumber(model.getAltMobile());
    dto.setEmail(model.getEmail());
    dto.setPermitNo(model.getPermitNo());
    dto.setPermitIssueDate(AllUtil.formatUIDate(model.getPermitIssueDate()));
    dto.setSupExperience(model.getSupExperience());
    dto.setExperiences(experieceHelper.fromModel(model.getExperiences()));
    dto.setPreferredLangMode(model.getPreferredLangMode());
    dto.setPreferredExamCentre(model.getPreferredExamCentre());
    dto.setTotalExperience(model.getTotalExperience());
    dto.setWhetherWiremanPermit(model.getWhetherWiremanPermit());
    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));
    return dto;
  }

  public NGSupervisorExaminationDTO toNGDTO(SupervisorExamination model) {
    NGSupervisorExaminationDTO dto = new NGSupervisorExaminationDTO();
    HelperUtil.getNGDTO(model, dto);

    dto.setSurname(model.getSurname());
    dto.setFirstname(model.getFirstName());
    dto.setMiddlename(model.getMiddleName());
    dto.setBirthDate(AllUtil.formatNGDate(model.getBirthDate()));
    dto.setAge(model.getAge());
    dto.setGender(model.getGender());
    dto.setSupExamType(model.getExamType());
    dto.setParmanentAddress(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.PERMANENT)));
    dto.setTemporaryAddress(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.TEMPORARY)));
    dto.setMobile(model.getMobile());
    dto.setAltMobileNumber(model.getAltMobile());
    dto.setEmail(model.getEmail());
    dto.setPermitNo(model.getPermitNo());
    dto.setPermitIssueDate(AllUtil.formatNGDate(model.getPermitIssueDate()));
    dto.setSupExperience(model.getSupExperience());
    dto.setExperiences(experieceHelper.fromModelNG(model.getExperiences()));
    dto.setPreferredLangMode(model.getPreferredLangMode());
    dto.setTotalExperience(model.getTotalExperience());
    dto.setWhetherWiremanPermit(model.getWhetherWiremanPermit());
    dto.setPreferredExamCenter(model.getPreferredExamCentre());
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));

    return dto;
  }
}