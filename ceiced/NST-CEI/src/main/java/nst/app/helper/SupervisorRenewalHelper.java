package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.SupervisorRenewalDTO;
import nst.app.dto.newgen.NGSupervisorRenewalDTO;
import nst.app.model.PortalUser;
import nst.app.model.forms.lb.SupervisorRenewal;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.stereotype.Component;

@Component
public class SupervisorRenewalHelper extends
    BaseHelper<SupervisorRenewal, SupervisorRenewalDTO> {

  public SupervisorRenewal toModel(SupervisorRenewalDTO SupervisorRenewalDTO) {
    SupervisorRenewal portalUser = new SupervisorRenewal();
    return toModel(portalUser, SupervisorRenewalDTO);
  }

  @Override
  public SupervisorRenewal toModel(SupervisorRenewal model,
                                   SupervisorRenewalDTO dto) {
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

  public SupervisorRenewal blankModel(Object portalUser) {
    SupervisorRenewal ownerDetail = new SupervisorRenewal();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public SupervisorRenewalDTO fromModel(SupervisorRenewal model) {
    SupervisorRenewalDTO dto = new SupervisorRenewalDTO();
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

  public NGSupervisorRenewalDTO toNGDTO(SupervisorRenewal model) {
    NGSupervisorRenewalDTO dto = new NGSupervisorRenewalDTO();
    HelperUtil.getNGDTO(model, dto);
    dto.setSurname(model.getSurname());
    dto.setFirstName(model.getFirstName());
    dto.setMiddleName(model.getMiddleName());
    dto.setApplicantName(String.format("%s %s %s", model.getSurname(), model.getFirstName(), model.getMiddleName()));
    dto.setPermitNo(model.getPermitNo());
    dto.setIssueDate(AllUtil.formatNGDate(model.getIssueDate()));
    dto.setFromDate(AllUtil.formatNGDate(model.getFromDate()));
    dto.setToDate(AllUtil.formatNGDate(model.getToDate()));
    return dto;
  }
}