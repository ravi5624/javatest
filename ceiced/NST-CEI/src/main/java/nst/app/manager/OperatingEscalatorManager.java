package nst.app.manager;

import nst.app.common.CEIBaseManager;
import nst.app.common.LiftEscalatorValidationUtil;
import nst.app.enums.FileStatus;
import nst.app.enums.UserType;
import nst.app.helper.OperatingEscalatorHelper;
import nst.app.model.forms.le.OperatingEscalator;
import nst.app.repo.OperatingEscalatorRepository;
import nst.common.base.BaseRepository;
import nst.common.error.AppException;
import nst.common.security.LoginUser;
import nst.kernal.ActivityType;
import nst.kernal.SystemConstants;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class OperatingEscalatorManager extends CEIBaseManager<OperatingEscalator> {

  @Autowired
  OperatingEscalatorRepository repository;

  @Autowired
  OperatingEscalatorHelper helper;

  @Override
  public BaseRepository<OperatingEscalator> getRepository() {
    return repository;
  }

  @Override
  public OperatingEscalator submitForm(OperatingEscalator model) {
    validatePayment(model.getApplicationId(), model.getApplicationType());
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
    printLog(ActivityType.SUBMITTED, model.getId(), "1","2");
    return repository.save(model);
  }

  public List<OperatingEscalator> getMyApps() {
    return repository.findMyApps(LoginUserUtil.getLoginUser().getUserId());
  }

}