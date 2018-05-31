package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.OwnerDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.OwnerDetailHelper;
import nst.app.manager.OwnerDetailManager;
import nst.app.model.OwnerDetail;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OwnerDetailService extends CEIBaseService<OwnerDetail, OwnerDTO> {

  @Autowired
  OwnerDetailManager ownerDetailManager;

  @Autowired
  OwnerDetailHelper ownerDetailHelper;

  public Iterable<OwnerDetail> getAll() {
    return ownerDetailManager.getAll();
  }

  @Override
  public BaseManager<OwnerDetail> getManager() {
    return ownerDetailManager;
  }

  @Override
  public BaseHelper<OwnerDetail, OwnerDTO> getHelper() {
    return ownerDetailHelper;
  }

  public ApplicationType applicationType() {
    return null;
  }
}