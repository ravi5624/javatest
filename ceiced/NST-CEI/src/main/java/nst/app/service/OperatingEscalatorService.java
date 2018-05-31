package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.common.LiftEscalatorValidationUtil;
import nst.app.dto.OperatingEscalatorDTO;
import nst.app.enums.ApplicationType;
import nst.app.enums.UserType;
import nst.app.helper.OperatingEscalatorHelper;
import nst.app.manager.OperatingEscalatorManager;
import nst.app.model.forms.Form;
import nst.app.model.forms.le.OperatingEscalator;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import nst.common.error.AppException;
import nst.common.security.LoginUser;
import nst.kernal.SystemConstants;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Component
@Transactional
public class OperatingEscalatorService extends
    CEIBaseService<OperatingEscalator, OperatingEscalatorDTO> {

  @Autowired
  OperatingEscalatorManager manager;

  @Autowired
  OperatingEscalatorHelper helper;

  public List<OperatingEscalatorDTO> getMyApps() {
    return helper.fromModel(manager.getMyApps());
  }

  @Override
  public BaseManager<OperatingEscalator> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<OperatingEscalator, OperatingEscalatorDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.OESCL;
  }

  @Override
  public void allowToEdit(Form m) {
    allowToLoad(m);
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    LiftEscalatorValidationUtil.allowToEditForm(loginUser, m);
  }

//  @Override
//  protected OperatingEscalatorDTO submit(OperatingEscalator operatingEscalator) {
//    if(Objects.isNull(operatingEscalator.getAgency())){
//      throw AppException.createWithMessageCode(SystemConstants.Model.INVALID_REQUEST, "Please specify agency before submit");
//    }
//    return super.submit(operatingEscalator);
//  }

  @Override
  public void allowToLoad(Form m) {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    if (loginUser.hasAuthority(UserType.OWNER.getType())) {
       if (m.getOwner().getId().equals(loginUser.getUserId())) {
          return;
       }
       throw AppException.createWithMessageCode(SystemConstants.BAD_REQUEST, SystemConstants.Rest.UNAUTHORISED_USER);
     }

     if (loginUser.hasAuthority(UserType.EM_AGENCY.getType())) {
        if (Objects.isNull(m.getAssignedTo()) ? m.getOwner().getId().equals(loginUser.getUserId()) : m.getAssignedTo().getId().equals(loginUser.getUserId())){
           return;
        }
        throw AppException.createWithMessageCode(SystemConstants.BAD_REQUEST, SystemConstants.Rest.UNAUTHORISED_AGENCY);
     }
     throw AppException.createWithMessageCode(SystemConstants.BAD_REQUEST, SystemConstants.Rest.UNAUTHORISED_USER);
  }
}