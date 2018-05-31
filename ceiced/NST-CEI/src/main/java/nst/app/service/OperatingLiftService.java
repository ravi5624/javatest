package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.common.LiftEscalatorValidationUtil;
import nst.app.dto.OperatingLiftDTO;
import nst.app.enums.ApplicationType;
import nst.app.enums.UserType;
import nst.app.helper.OperatingLiftHelper;
import nst.app.manager.OperatingLiftManager;
import nst.app.model.forms.Form;
import nst.app.model.forms.le.OperatingLift;
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
public class OperatingLiftService extends
    CEIBaseService<OperatingLift, OperatingLiftDTO> {

  @Autowired
  OperatingLiftManager manager;

  @Autowired
  OperatingLiftHelper helper;

  public List<OperatingLiftDTO> getMyApps() {
    return helper.fromModel(manager.getMyApps());
  }

  @Override
  public BaseManager<OperatingLift> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<OperatingLift, OperatingLiftDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.OLIFT;
  }

  @Override
  public void allowToEdit(Form m) {
    allowToLoad(m);
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    LiftEscalatorValidationUtil.allowToEditForm(loginUser, m);
  }

//  @Override
//  protected OperatingLiftDTO submit(OperatingLift operatingLift) {
//    //TODO [SAGAR]: Verify owner user access then submit form currently handled in manager layer.
//    if(Objects.isNull(operatingLift.getAgency())){
//      throw AppException.createWithMessageCode(SystemConstants.Model.INVALID_REQUEST, "Please specify agency before submit");
//    }
//    return super.submit(operatingLift);
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
      if (Objects.isNull(m.getAssignedTo()) ? m.getOwner().getId().equals(loginUser.getUserId()) : m.getAssignedTo().getId().equals(loginUser.getUserId())) {
        return;
      }
    }
    throw AppException.createWithMessageCode(SystemConstants.BAD_REQUEST, SystemConstants.Rest.UNAUTHORISED_USER);
  }
}