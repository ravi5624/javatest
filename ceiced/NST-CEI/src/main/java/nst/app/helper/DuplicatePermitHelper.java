package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.DuplicatePermitDTO;
import nst.app.dto.newgen.NGDuplicatePermitDTO;
import nst.app.enums.AddressType;
import nst.app.model.PortalUser;
import nst.app.model.forms.lb.DuplicatePermit;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class DuplicatePermitHelper extends
    BaseHelper<DuplicatePermit, DuplicatePermitDTO> {

  @Autowired AddressHelper addressHelper;
  @Autowired AttachmentHelper attachmentHelper;

  public DuplicatePermit toModel(DuplicatePermitDTO DuplicatePermitDTO) {
    DuplicatePermit portalUser = new DuplicatePermit();
    return toModel(portalUser, DuplicatePermitDTO);
  }

  @Override
  public DuplicatePermit toModel(DuplicatePermit model,
      DuplicatePermitDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);
    model.setSurname(dto.getSurname());
    model.setFirstName(dto.getFirstName());
    model.setMiddleName(dto.getMiddleName());
    model.setDuplicateType(String.join(",",dto.getDuplicateType()));
    model.setPermitNo(dto.getPermitNo());
    model.setMobile(dto.getMobile());
    model.setAltMobile(dto.getAltMobile());
    model.setEmail(dto.getEmail());
    model.setPermitIssueDate(AllUtil.toUIDate(dto.getPermitIssueDate()));

    model.getForm().setApplicantName(dto.getApplicantName());
    addressHelper.manageAddress(model.getForm(), dto.getParmanentAddress());
    return model;
  }

  public DuplicatePermit blankModel(Object portalUser) {
    DuplicatePermit ownerDetail = new DuplicatePermit();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public DuplicatePermitDTO fromModel(DuplicatePermit model) {
    DuplicatePermitDTO dto = new DuplicatePermitDTO();
    HelperUtil.toDTO(dto, model);
    dto.setSurname(model.getSurname());
    dto.setFirstName(model.getFirstName());
    dto.setMiddleName(model.getMiddleName());
    if (model.getDuplicateType()!=null){
        dto.setDuplicateType(Arrays.asList(model.getDuplicateType().split("\\,")));
    }
    dto.setPermitNo(model.getPermitNo());
    dto.setMobile(model.getMobile());
    dto.setAltMobile(model.getAltMobile());
    dto.setEmail(model.getEmail());
    dto.setPermitIssueDate(AllUtil.formatUIDate(model.getPermitIssueDate()));
    dto.setParmanentAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.PERMANENT)));
    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));
    return dto;
  }

  public NGDuplicatePermitDTO toNGDTO(DuplicatePermit model) {
    NGDuplicatePermitDTO dto = new NGDuplicatePermitDTO();

    HelperUtil.getNGDTO(model, dto);

    dto.setSurname(model.getSurname());
    dto.setFirstName(model.getFirstName());
    dto.setMiddleName(model.getMiddleName());
    dto.setDuplicateType(model.getDuplicateType());
    dto.setPermitNo(model.getPermitNo());
    dto.setMobile(model.getMobile());
    dto.setAltMobile(model.getAltMobile());
    dto.setEmail(model.getEmail());
    dto.setPermitIssueDate(AllUtil.formatNGDate(model.getPermitIssueDate()));
    dto.setParmanentAddress(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.PERMANENT)));
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));
    return dto;
  }
}