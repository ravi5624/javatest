package nst.app.manager;

import nst.app.common.CEIBaseManager;
import nst.app.common.LiftEscalatorValidationUtil;
import nst.app.enums.FileStatus;
import nst.app.enums.UserType;
import nst.app.helper.LiftInstallationHelper;
import nst.app.model.forms.le.LiftInstallation;
import nst.app.repo.LiftInstallationRepository;
import nst.common.base.BaseRepository;
import nst.common.error.AppException;
import nst.common.security.LoginUser;
import nst.kernal.SystemConstants;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class LiftInstallationManager extends CEIBaseManager<LiftInstallation> {

  @Autowired
  LiftInstallationRepository repository;

  @Autowired
  LiftInstallationHelper helper;

  @Override
  public LiftInstallation findForm(Long formId) {
    return repository.findByForm_id(formId);
  }

  @Override
  public BaseRepository<LiftInstallation> getRepository() {
    return repository;
  }

  @Override
  public LiftInstallation submitForm(LiftInstallation model) {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();

    if (LiftEscalatorValidationUtil.isAllowSubmitForm(loginUser, model.getForm(), model.getOwner())
            && model.getForm().canSubmit(loginUser)) {
      if(Objects.isNull(model.getAgency())){
        throw AppException.createWithMessageCode(SystemConstants.Model.INVALID_REQUEST, "Please specify agency before submit");
      }
      model.getForm().setFileStatus(FileStatus.OWNER_SUBMITTED);
      model.setOwnerSubmittedOn(new Date());
      return repository.save(model);
    }

    if (loginUser.hasAuthority(UserType.EM_AGENCY.getType()) && !model.getAssignedTo().getId().equals(loginUser.getUserId())) {
      throw AppException.createWithMessageCode(SystemConstants.BAD_REQUEST, SystemConstants.Rest.UNAUTHORISED_AGENCY);
    }

    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }

  public LiftInstallation get(String identifier1, String identifier2) {
    return get(Long.parseLong(identifier1));
  }
}