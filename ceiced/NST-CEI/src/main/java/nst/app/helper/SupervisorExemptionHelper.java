package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.SupervisorExemptionDTO;
import nst.app.dto.newgen.NGSupervisorExemptionDTO;
import nst.app.enums.AddressType;
import nst.app.model.PortalUser;
import nst.app.model.forms.lb.SupervisorExemption;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupervisorExemptionHelper extends
    BaseHelper<SupervisorExemption, SupervisorExemptionDTO> {

  @Autowired AddressHelper addressHelper;

  @Autowired AttachmentHelper attachmentHelper;

  public SupervisorExemption toModel(SupervisorExemptionDTO ownerDTO) {
    SupervisorExemption portalUser = new SupervisorExemption();
    return toModel(portalUser, ownerDTO);
  }

  @Override
  public SupervisorExemption toModel(SupervisorExemption model,
                                     SupervisorExemptionDTO dto) {
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
    model.setTechnicalQualification(dto.getTechnicalQualification());
    model.setSupEnrollmentNo(dto.getSupEnrollmentNo());
    model.setSupCollegeDist(dto.getSupCollegeDist());
    model.setSupUniversityName(dto.getSupUniversityName());
    model.setPassYear(dto.getPassYear());
    model.setQualificationState(dto.getQualificationState());
    model.setTotalExperience(dto.getTotalExperience());
    model.setSupInstituteCollegeName(dto.getSupInstituteCollegeName());
    model.setNoPermit(dto.getNoPermit());
    model.getForm().setApplicantName(dto.getApplicantName());

    return model;
  }

  public SupervisorExemption blankModel(Object portalUser) {
    SupervisorExemption ownerDetail = new SupervisorExemption();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public SupervisorExemptionDTO fromModel(SupervisorExemption model) {
    SupervisorExemptionDTO dto = new SupervisorExemptionDTO();
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
    dto.setTechnicalQualification(model.getTechnicalQualification());
    dto.setSupEnrollmentNo(model.getSupEnrollmentNo());
    dto.setSupCollegeDist(model.getSupCollegeDist());
    dto.setSupUniversityName(model.getSupUniversityName());
    dto.setSupInstituteCollegeName(model.getSupInstituteCollegeName());
    dto.setPassYear(model.getPassYear());
    dto.setQualificationState(model.getQualificationState());
    dto.setTotalExperience(model.getTotalExperience());
      dto.setNoPermit(model.getNoPermit());
    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));
    return dto;
  }

  public NGSupervisorExemptionDTO toNGDTO(SupervisorExemption model) {
    NGSupervisorExemptionDTO dto = new NGSupervisorExemptionDTO();
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
    dto.setTechnicalQualification(model.getTechnicalQualification());
    dto.setSupEnrollmentNo(model.getSupEnrollmentNo());
    dto.setSupCollegeDist(model.getSupCollegeDist());
    dto.setSupUniversityName(model.getSupUniversityName());
    dto.setPassYear(model.getPassYear());
    dto.setQualificationState(model.getQualificationState());
    dto.setTotalExperience(model.getTotalExperience());
    dto.setNoPermit(model.getNoPermit());
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));
    return dto;
  }
}