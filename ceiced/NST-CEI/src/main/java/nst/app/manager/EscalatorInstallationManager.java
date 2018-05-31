package nst.app.manager;

import nst.app.common.CEIBaseManager;
import nst.app.common.LiftEscalatorValidationUtil;
import nst.app.dto.EscalatorInstallationDTO;
import nst.app.enums.FileStatus;
import nst.app.enums.UserType;
import nst.app.helper.EscalatorInstallationHelper;
import nst.app.model.forms.le.EscalatorInstallation;
import nst.app.repo.EscalatorInstallationRepository;
import nst.common.base.BaseRepository;
import nst.common.error.AppException;
import nst.common.security.LoginUser;
import nst.kernal.SystemConstants;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class EscalatorInstallationManager extends CEIBaseManager<EscalatorInstallation> {

  @Autowired
  EscalatorInstallationRepository repository;

  @Autowired
  EscalatorInstallationHelper helper;

  @Override
  public BaseRepository<EscalatorInstallation> getRepository() {
    return repository;
  }

  @Override
  public EscalatorInstallation submitForm(EscalatorInstallation model) {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
      EscalatorInstallationDTO escalatorInstallationDTO= helper.fromModel(model);
      escalatorInstallationDTO.validateAll();

    if (LiftEscalatorValidationUtil.isAllowSubmitForm(loginUser, model.getForm(), model.getOwner())
            && model.getForm().canSubmit(loginUser)) {
      if(Objects.isNull(model.getAgency())) {
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

  public List<EscalatorInstallation> getMyApps() {
    return repository.findMyApps(LoginUserUtil.getLoginUser().getUserId());
  }
}