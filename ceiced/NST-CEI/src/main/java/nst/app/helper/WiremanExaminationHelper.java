package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.WiremanExaminationDTO;
import nst.app.dto.newgen.NGWiremanExaminationDTO;
import nst.app.enums.AddressType;
import nst.app.model.PortalUser;
import nst.app.model.forms.lb.WiremanExamination;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class WiremanExaminationHelper extends
    BaseHelper<WiremanExamination, WiremanExaminationDTO> {

  @Autowired
  ExperienceHelper experieceHelper;
  @Autowired
  AddressHelper addressHelper;
  @Autowired
  AttachmentHelper attachmentHelper;

  public WiremanExamination toModel(WiremanExaminationDTO WiremanExaminationDTO) {
    WiremanExamination portalUser = new WiremanExamination();
    return toModel(portalUser, WiremanExaminationDTO);
  }

  @Override
  public WiremanExamination toModel(WiremanExamination to, WiremanExamination from) {
    to.setFirstName(from.getFirstName());
    to.setMiddleName(from.getMiddleName());
    return to;
  }

  @Override
  public WiremanExamination toModel(WiremanExamination model, WiremanExaminationDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);
    model.setSurname(dto.getSurname());
    model.setFirstName(dto.getFirstName());
    model.setMiddleName(dto.getMiddleName());
    model.setBirthDate(AllUtil.toUIDate(dto.getBirthDate()));
    model.setAge(dto.getAge());
    model.setGender(dto.getGender());
    addressHelper.manageAddress(model.getForm(), dto.getParmanentAddress());
    addressHelper.manageAddress(model.getForm(), dto.getTemporaryAddress());
    model.setMobile(dto.getMobile());
    model.setAltMobile(dto.getAltMobileNumber());
    model.setEmail(dto.getEmail());
    model.setEligibility(dto.getWmanEligibility());

    model.getForm().setApplicantName(dto.getApplicantName());

    if (CollectionUtils.isEmpty(dto.getExperiences())) {
      model.getExperiences().clear();
    } else {
      dto.getExperiences().forEach(exp -> {
        experieceHelper.toModel(model.myExp(exp.getId()), exp);
      });
    }

    model.setPreferredLangMode(dto.getPreferredLangMode());
    model.setPreferredExamCentre(dto.getPreferredExamCentre());
    model.setTotalExperience(dto.getTotalExperience());
    return model;

  }

  public WiremanExamination blankModel(Object portalUser) {
    WiremanExamination ownerDetail = new WiremanExamination();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public WiremanExaminationDTO fromModel(WiremanExamination model) {
    WiremanExaminationDTO dto = new WiremanExaminationDTO();
    HelperUtil.toDTO(dto, model);
    dto.setSurname(model.getSurname());
    dto.setFirstName(model.getFirstName());
    dto.setMiddleName(model.getMiddleName());
    dto.setBirthDate(AllUtil.formatUIDate(model.getBirthDate()));
    dto.setAge(model.getAge());
    dto.setGender(model.getGender());
    dto.setParmanentAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.PERMANENT)));
    dto.setTemporaryAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.TEMPORARY)));
    dto.setMobile(model.getMobile());
    dto.setAltMobileNumber(model.getAltMobile());
    dto.setEmail(model.getEmail());
    dto.setWmanEligibility(model.getEligibility());
    dto.setExperiences(experieceHelper.fromModel(model.getExperiences()));
    dto.setPreferredLangMode(model.getPreferredLangMode());
    dto.setPreferredExamCentre(model.getPreferredExamCentre());
    dto.setTotalExperience(model.getTotalExperience());
    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));

    return dto;
  }
  public NGWiremanExaminationDTO toNGDTO(WiremanExamination model) {
    NGWiremanExaminationDTO dto = new NGWiremanExaminationDTO();

    HelperUtil.getNGDTO(model, dto);

    dto.setSurname(model.getSurname());
    dto.setFirstName(model.getFirstName());
    dto.setMiddleName(model.getMiddleName());
    dto.setBirthDate(AllUtil.formatNGDate(model.getBirthDate()));
    dto.setAge(model.getAge());
    dto.setGender(model.getGender());
    dto.setParmanentAddress(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.PERMANENT)));
    dto.setTemporaryAddress(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.TEMPORARY)));
    dto.setMobile(model.getMobile());
    dto.setAltMobileNumber(model.getAltMobile());
    dto.setEmail(model.getEmail());
    dto.setWmanEligibility(model.getEligibility());
    dto.setPreferredLangMode(model.getPreferredLangMode());
    dto.setTotalExperience(model.getTotalExperience());
    dto.setPreferredExamCenter(model.getPreferredExamCentre());
    dto.setExperiences(experieceHelper.fromModelNG(model.getExperiences()));
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));

    return dto;
  }
}