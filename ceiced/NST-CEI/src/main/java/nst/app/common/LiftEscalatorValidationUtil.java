package nst.app.common;

import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.enums.UserType;
import nst.app.model.PortalUser;
import nst.app.model.forms.Form;
import nst.app.model.forms.le.CommonForm;
import nst.common.error.AppException;
import nst.common.error.AuthorizationException;
import nst.common.security.LoginUser;
import nst.kernal.SystemConstants;

public class LiftEscalatorValidationUtil {

  public static boolean isAllowSubmitForm(LoginUser loginUser, CommonForm form, PortalUser owner) {

    if (loginUser.hasAuthority(UserType.EM_AGENCY.getType()) && owner.getId().equals(loginUser.getUserId())) {
      throw AppException.createWithMessageCode(SystemConstants.BAD_REQUEST, SystemConstants.Model.AGENCY_CANNOT_SUBMIT_TEMPLATE);
    }

    if (loginUser.hasAuthority(UserType.OWNER.getType()) && form.getUser().getId().equals(loginUser.getUserId())) {
      return Boolean.TRUE;
    }
    if (loginUser.hasAuthority(UserType.EM_AGENCY.getType())) {
      return Boolean.FALSE;
    }
    throw AppException.createWithMessageCode(SystemConstants.BAD_REQUEST, SystemConstants.Rest.UNAUTHORISED_USER);
  }

  public static void allowToEditForm(LoginUser loginUser, Form form) {
    if (loginUser.hasAuthority(UserType.OWNER.getType())) {
      if(FileStatus.DRAFT == form.getFileStatus() && form.getOwner().getId().equals(loginUser.getUserId())) {
        return;
      }
      throw AppException.createWithMessage("INVALID_REQUEST","Current Application Status :" + form.getFileStatus().name());
    }

    if (loginUser.hasAuthority(UserType.EM_AGENCY.getType())) {
      if (FileStatus.DRAFT == form.getFileStatus() && form.getOwner().getId().equals(loginUser.getUserId())) {
        return;
      }
      if(FileStatus.OWNER_SUBMITTED == form.getFileStatus() && !form.getOwner().getId().equals(loginUser.getUserId())){
        return;
      }
      throw AppException.createWithMessage("INVALID_REQUEST",
              "Current Application Status :" + form.getFileStatus().name());
    }
    throw AppException.createWithMessageCode(SystemConstants.Model.INVALID_REQUEST, SystemConstants.Model.INVALID_REQUEST);
  }

  public static void checkFormPermission(LoginUser loginUser, CommonForm commonForm) {
    ApplicationType appType = commonForm.getApplicationType();
    if ((appType == ApplicationType.LIL || appType == ApplicationType.EIL || appType == ApplicationType.OESCL
            || appType == ApplicationType.OLIFT)) {
      if(loginUser.hasAuthority(UserType.OWNER.getType()) && commonForm.getUser().isSameUser(loginUser)
              || (loginUser.hasAuthority(UserType.EM_AGENCY.getType()))) {
        /*TODO: Validate agency is assigned to particular application if no then thorw exception*/
// || (loginUser.hasAuthority(UserType.EM_AGENCY.getType()) && commonForm.getUser().isSameUser(loginUser))
// || (loginUser.hasAuthority(UserType.EM_AGENCY.getType()) && ((Form)commonForm).getAssignedTo().isSameUser(loginUser))
        return;
      }
    }else if(commonForm.getUser().getId().equals(loginUser.getUserId())){
      return;
    }
    throw AuthorizationException.create(String.format("[%s : %s]", commonForm.getId(), commonForm.getApplicationType()), loginUser);
  }
}