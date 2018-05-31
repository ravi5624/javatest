package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.InterStatePermitDTO;
import nst.app.dto.newgen.NGInterStatePermitDTO;
import nst.app.enums.AddressType;
import nst.app.model.PortalUser;
import nst.app.model.forms.lb.InterStatePermit;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InterStatePermitHelper extends BaseHelper<InterStatePermit, InterStatePermitDTO> {


  @Autowired
  AttachmentHelper attachmentHelper;
  @Autowired
  AddressHelper addressHelper;
  public InterStatePermit toModel(InterStatePermitDTO InterStatePermitDTO) {
    InterStatePermit portalUser = new InterStatePermit();
    return toModel(portalUser, InterStatePermitDTO);
  }

  @Override
  public InterStatePermit toModel(InterStatePermit model, InterStatePermitDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);
    model.setSurname(dto.getSurname());
    model.setFirstName(dto.getFirstName());
    model.setMiddleName(dto.getMiddleName());
    model.setPermitNo(dto.getPermitNo());
    model.setNameAndAddressAuth(dto.getNameAndAddressAuth());
    model.setBirthDate(AllUtil.toUIDate(dto.getBirthDate()));
    model.setAge(dto.getAge());
    model.setGender(dto.getGender());
    model.setMobile(dto.getMobile());
    model.setAltMobile(dto.getAltMobile());
    model.setEmail(dto.getEmail());
    model.setCandidateType(dto.getCandidateType());
    model.setPassYear(dto.getPassYear());
    model.setPermitIssueDate(AllUtil.toUIDate(dto.getPermitIssueDate()));
    model.setPresentOrgName(dto.getPresentOrgName());
    model.setPresentOrgAddress(dto.getPresentOrgAddress());

    model.getForm().setApplicantName(dto.getApplicantName());

    addressHelper.manageAddress(model.getForm(), dto.getParmanentAddress());
    addressHelper.manageAddress(model.getForm(), dto.getTemporaryAddress());

    return model;
  }

  public InterStatePermit blankModel(Object portalUser) {
    InterStatePermit ownerDetail = new InterStatePermit();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public InterStatePermitDTO fromModel(InterStatePermit model) {
    InterStatePermitDTO dto = new InterStatePermitDTO();
    HelperUtil.toDTO(dto, model);
    dto.setMiddleName(model.getMiddleName());
    dto.setPermitNo(model.getPermitNo());
    dto.setNameAndAddressAuth(model.getNameAndAddressAuth());
    dto.setBirthDate(AllUtil.formatUIDate(model.getBirthDate()));
    dto.setAge(model.getAge());
    dto.setGender(model.getGender());
    dto.setMobile(model.getMobile());
    dto.setAltMobile(model.getAltMobile());
    dto.setEmail(model.getEmail());
    dto.setCandidateType(model.getCandidateType());
    dto.setPassYear(model.getPassYear());
    dto.setPermitIssueDate(AllUtil.formatUIDate(model.getPermitIssueDate()));
    dto.setPresentOrgName(model.getPresentOrgName());
    dto.setPresentOrgAddress(model.getPresentOrgAddress());
    dto.setFirstName(model.getFirstName());
    dto.setSurname(model.getSurname());
    dto.setParmanentAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.PERMANENT)));
    dto.setTemporaryAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.TEMPORARY)));

    dto.setId(model.getApplicationId());
    dto.setType(model.getApplicationType().getType());
    dto.setApplicationName(model.getApplicationType().getName());
    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));
    return dto;
  }
  public NGInterStatePermitDTO toNGDTO(InterStatePermit model) {
    NGInterStatePermitDTO dto = new NGInterStatePermitDTO();
    HelperUtil.getNGDTO(model, dto);
    dto.setMiddleName(model.getMiddleName());
    dto.setPermitNo(model.getPermitNo());
    dto.setNameAndAddressAuth(model.getNameAndAddressAuth());
    dto.setBirthDate(AllUtil.formatNGDate(model.getBirthDate()));
    dto.setAge(model.getAge());
    dto.setGender(model.getGender());
    dto.setMobile(model.getMobile());
    dto.setAltMobile(model.getAltMobile());
    dto.setEmail(model.getEmail());
    dto.setPassYear(model.getPassYear());
    dto.setPermitIssueDate(AllUtil.formatNGDate(model.getPermitIssueDate()));
    dto.setPresentOrgName(model.getPresentOrgName());
    dto.setPresentOrgAddress(model.getPresentOrgAddress());
    dto.setFirstName(model.getFirstName());
    dto.setSurname(model.getSurname());
    dto.setParmanentAddress(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.PERMANENT)));
    dto.setTemporaryAddress(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.TEMPORARY)));
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));

    return dto;
  }
}