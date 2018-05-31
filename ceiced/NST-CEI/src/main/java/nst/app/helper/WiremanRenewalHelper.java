package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.WiremanRenewalDTO;
import nst.app.dto.newgen.NGWiremanRenewalDTO;
import nst.app.model.PortalUser;
import nst.app.model.forms.lb.WiremanRenewal;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.stereotype.Component;

@Component
public class WiremanRenewalHelper extends
    BaseHelper<WiremanRenewal, WiremanRenewalDTO> {

  public WiremanRenewal toModel(WiremanRenewalDTO WiremanRenewalDTO) {
    WiremanRenewal portalUser = new WiremanRenewal();
    return toModel(portalUser, WiremanRenewalDTO);
  }

  @Override
  public WiremanRenewal toModel(WiremanRenewal model, WiremanRenewalDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);
    model.setSurname(dto.getSurname());
    model.setFirstName(dto.getFirstName());
    model.setMiddleName(dto.getMiddleName());
    model.setPermitNo(dto.getPermitNo());
    model.setIssueDate(AllUtil.toUIDate(dto.getIssueDate()));
    model.setFromDate(AllUtil.toUIDate(dto.getFromDate()));
    model.setToDate(AllUtil.toUIDate(dto.getToDate()));
    model.getForm().setApplicantName(dto.getApplicantName());
    return model;
  }

  public WiremanRenewal blankModel(Object portalUser) {
    WiremanRenewal ownerDetail = new WiremanRenewal();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public WiremanRenewalDTO fromModel(WiremanRenewal model) {
    WiremanRenewalDTO dto = new WiremanRenewalDTO();
    HelperUtil.toDTO(dto, model);
    dto.setSurname(model.getSurname());
    dto.setFirstName(model.getFirstName());
    dto.setMiddleName(model.getMiddleName());
    dto.setPermitNo(model.getPermitNo());
    dto.setIssueDate(AllUtil.formatUIDate(model.getIssueDate()));
    dto.setFromDate(AllUtil.formatUIDate(model.getFromDate()));
    dto.setToDate(AllUtil.formatUIDate(model.getToDate()));
    dto.setId(model.getApplicationId());
    dto.setType(model.getApplicationType().getType());
    dto.setApplicationName(model.getApplicationType().getName());
    return dto;
  }

  public NGWiremanRenewalDTO toNGDTO(WiremanRenewal model) {
    NGWiremanRenewalDTO dto = new NGWiremanRenewalDTO();
    HelperUtil.getNGDTO(model, dto);
    dto.setSurname(model.getSurname());
    dto.setFirstName(model.getFirstName());
    dto.setMiddleName(model.getMiddleName());
    dto.setPermitNo(model.getPermitNo());
    dto.setIssueDate(AllUtil.formatNGDate(model.getIssueDate()));
    dto.setFromDate(AllUtil.formatNGDate(model.getFromDate()));
    dto.setToDate(AllUtil.formatNGDate(model.getToDate()));
    return dto;
  }

}