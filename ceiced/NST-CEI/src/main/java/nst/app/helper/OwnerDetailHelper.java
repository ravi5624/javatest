package nst.app.helper;

import nst.app.dto.OwnerDTO;
import nst.app.manager.PortalUserManager;
import nst.app.model.OwnerDetail;
import nst.app.model.PortalUser;
import nst.common.base.BaseHelper;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OwnerDetailHelper extends BaseHelper<OwnerDetail, OwnerDTO> {

  @Autowired
  PortalUserManager portalUserManager;

  public OwnerDetail toModel(OwnerDTO ownerDTO) {
    OwnerDetail ownerDetail = new OwnerDetail();
    ownerDetail.setUser(portalUserManager.get(ownerDTO.getUserId()));
    toModel(ownerDetail, ownerDTO);
    return ownerDetail;
  }

  @Override
  public OwnerDetail toModel(OwnerDetail ownerDetail, OwnerDTO ownerDTO) {
    ownerDetail.setOwnerName(ownerDTO.getOwnerName());
    return ownerDetail;
  }

  public OwnerDetail blankModel(Object portalUser) {
    OwnerDetail ownerDetail = new OwnerDetail();
    LoginUserUtil.getLoginUser();
    ownerDetail.setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public OwnerDTO fromModel(OwnerDetail ownerDetail) {
    OwnerDTO ownerDTO = new OwnerDTO();
    ownerDTO.setOwnerName(ownerDetail.getOwnerName());
    return ownerDTO;
  }
}