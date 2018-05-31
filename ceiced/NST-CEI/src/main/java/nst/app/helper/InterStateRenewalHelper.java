package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.InterStateRenewalDTO;
import nst.app.dto.newgen.NGInterStateRenewalDTO;
import nst.app.model.PortalUser;
import nst.app.model.forms.lb.InterStateRenewal;
import nst.common.base.BaseHelper;
import nst.util.LoginUserUtil;
import org.springframework.stereotype.Component;

@Component
public class InterStateRenewalHelper extends
    BaseHelper<InterStateRenewal, InterStateRenewalDTO> {

  public InterStateRenewal toModel(InterStateRenewalDTO InterStateRenewalDTO) {
    InterStateRenewal portalUser = new InterStateRenewal();
    return toModel(portalUser, InterStateRenewalDTO);
  }

  @Override
  public InterStateRenewal toModel(InterStateRenewal model,
      InterStateRenewalDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);
    return model;
  }

  public InterStateRenewal blankModel(Object portalUser) {
    InterStateRenewal ownerDetail = new InterStateRenewal();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public InterStateRenewalDTO fromModel(InterStateRenewal model) {
    InterStateRenewalDTO dto = new InterStateRenewalDTO();
    dto.setId(model.getApplicationId());
    dto.setType(model.getApplicationType().getType());
    dto.setApplicationName(model.getApplicationType().getName());
    return dto;
  }

  public NGInterStateRenewalDTO toNGDTO(InterStateRenewal model) {
    NGInterStateRenewalDTO dto = new NGInterStateRenewalDTO();
    return dto;
  }
}