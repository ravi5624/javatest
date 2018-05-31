package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.WiremanExemptionDTO;
import nst.app.dto.newgen.NGWiremanExemptionDTO;
import nst.app.enums.AddressType;
import nst.app.model.PortalUser;
import nst.app.model.forms.lb.WiremanExemption;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WiremanExemptionHelper extends
    BaseHelper<WiremanExemption, WiremanExemptionDTO> {

  @Autowired AddressHelper addressHelper;
  @Autowired ExperienceHelper experienceHelper;
  @Autowired AttachmentHelper attachmentHelper;

  public WiremanExemption toModel(WiremanExemptionDTO WiremanExemptionDTO) {
    WiremanExemption portalUser = new WiremanExemption();
    return toModel(portalUser, WiremanExemptionDTO);
  }

  @Override
  public WiremanExemption toModel(WiremanExemption model,
      WiremanExemptionDTO dto) {
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
    model.setPassYear(dto.getPassYear());
    model.setQualificationState(dto.getQualificationState());
    model.setNoPermit(dto.getNoPermit());

    model.getForm().setApplicantName(dto.getApplicantName());
    return model;
  }

  public WiremanExemption blankModel(Object portalUser) {
    WiremanExemption ownerDetail = new WiremanExemption();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public WiremanExemptionDTO fromModel(WiremanExemption model) {
    WiremanExemptionDTO dto = new WiremanExemptionDTO();
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
    dto.setPassYear(model.getPassYear());
    dto.setQualificationState(model.getQualificationState());
    dto.setNoPermit(model.getNoPermit());

    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));
    return dto;
  }

  public NGWiremanExemptionDTO toNGDTO(WiremanExemption model) {
    NGWiremanExemptionDTO dto = new NGWiremanExemptionDTO();
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
    dto.setPassYear(""+ model.getPassYear());
    dto.setQualificationState(model.getQualificationState());
    dto.setNoPermit(model.getNoPermit());
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));

    return dto;
  }
}