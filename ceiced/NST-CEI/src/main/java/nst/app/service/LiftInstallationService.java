package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.common.LiftEscalatorValidationUtil;
import nst.app.dto.LiftInstallationDTO;
import nst.app.enums.ApplicationType;
import nst.app.enums.UserType;
import nst.app.helper.LiftInstallationHelper;
import nst.app.manager.LiftInstallationManager;
import nst.app.model.forms.Form;
import nst.app.model.forms.le.LiftInstallation;
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
public class LiftInstallationService extends CEIBaseService<LiftInstallation, LiftInstallationDTO> {

  @Autowired
  LiftInstallationManager manager;

  @Autowired
  LiftInstallationHelper helper;

  public List<LiftInstallationDTO> getMyForms() {
    Iterable<LiftInstallation> all = manager.getAll();
    return helper.fromModel(all);
  }

  @Override
  public BaseHelper<LiftInstallation, LiftInstallationDTO> getHelper() {
    return helper;
  }

  @Override
  public BaseManager<LiftInstallation> getManager() {
    return manager;
  }

  public ApplicationType applicationType() {
    return ApplicationType.LIL;
  }

  @Override
  public void allowToEdit(Form m) {
    allowToLoad(m);
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    LiftEscalatorValidationUtil.allowToEditForm(loginUser, m);
  }

//  @Override
//  protected LiftInstallationDTO submit(LiftInstallation liftInstallation) {
    //TODO [SAGAR]: Verify owner user access then submit form currently handled in manager layer.
//    if(Objects.isNull(liftInstallation.getAgency())){
//      throw AppException.createWithMessageCode(SystemConstants.Model.INVALID_REQUEST, "Please specify agency before submit");
//    }
//    return super.submit(liftInstallation);
//  }

  @Override
  public void allowToLoad(Form m) {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();

    if (loginUser.hasAuthority(UserType.OWNER.getType())) {
      if(m.getOwner().getId().equals(loginUser.getUserId())) {
        return;
      }
      throw AppException.createWithMessageCode(SystemConstants.BAD_REQUEST, SystemConstants.Rest.UNAUTHORISED_USER);
    }

    if (loginUser.hasAuthority(UserType.EM_AGENCY.getType())) {
      if(Objects.isNull(m.getAssignedTo()) ? m.getOwner().getId().equals(loginUser.getUserId()) : m.getAssignedTo().getId().equals(loginUser.getUserId())){
        return;
      }
      throw AppException.createWithMessageCode(SystemConstants.BAD_REQUEST, SystemConstants.Rest.UNAUTHORISED_AGENCY);
    }
    throw AppException.createWithMessageCode(SystemConstants.BAD_REQUEST, SystemConstants.Rest.UNAUTHORISED_USER);
  }
}