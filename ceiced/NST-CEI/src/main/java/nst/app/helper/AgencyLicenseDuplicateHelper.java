package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.AgencyLicenseDuplicateDTO;
import nst.app.dto.newgen.NGAgencyLicenseDuplicateDTO;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.AgencyLicenseDuplicate;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgencyLicenseDuplicateHelper extends
    BaseHelper<AgencyLicenseDuplicate, AgencyLicenseDuplicateDTO> {
  @Autowired
  AttachmentHelper attachmentHelper;

  public AgencyLicenseDuplicate toModel(AgencyLicenseDuplicateDTO dto) {
    AgencyLicenseDuplicate portalUser = new AgencyLicenseDuplicate();
    return toModel(portalUser, dto);
  }

  @Override
  public AgencyLicenseDuplicate toModel(AgencyLicenseDuplicate model,
      AgencyLicenseDuplicateDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);
    model.setAgencyType(dto.getAgencyType());
    model.setAgencyAuthNo(dto.getAgencyAuthNo());
    model.setIssueDate(AllUtil.toUIDate(dto.getIssueDate()));
    model.setExpiryDate(AllUtil.toUIDate(dto.getExpiryDate()));
    model.setAgencyType(dto.getAgencyType());
    model.setReason(dto.getReason());
    model.setReasonIfOther(dto.getReasonIfOther());
    return model;

  }

  public AgencyLicenseDuplicate blankModel(Object portalUser) {
    AgencyLicenseDuplicate ownerDetail = new AgencyLicenseDuplicate();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public AgencyLicenseDuplicateDTO fromModel(AgencyLicenseDuplicate model) {
    AgencyLicenseDuplicateDTO dto = new AgencyLicenseDuplicateDTO();
    HelperUtil.toDTO(dto, model);
    dto.setAgencyType(model.getAgencyType());
    dto.setAgencyAuthNo(model.getAgencyAuthNo());
    dto.setAgencyType(model.getAgencyType());
    dto.setExpiryDate(AllUtil.formatUIDate(model.getExpiryDate()));
    dto.setIssueDate(AllUtil.formatUIDate(model.getIssueDate()));
    dto.setReason(model.getReason());
    dto.setReasonIfOther(model.getReasonIfOther());
    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));

    return dto;
  }
 public NGAgencyLicenseDuplicateDTO toNGDTO(AgencyLicenseDuplicate model) {
   NGAgencyLicenseDuplicateDTO dto = new NGAgencyLicenseDuplicateDTO();
   HelperUtil.getNGDTO(model, dto);
   dto.setAgencyType(model.getAgencyType());
   dto.setAgencyAuthNo(model.getAgencyAuthNo());
   dto.setAgencyType(model.getAgencyType());
   dto.setExpiryDate(AllUtil.formatNGDate(model.getExpiryDate()));
   dto.setIssueDate(AllUtil.formatNGDate(model.getIssueDate()));
   dto.setReason(model.getReason());
   dto.setReasonIfOther(model.getReasonIfOther());
   dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));


    return dto;
  }
}