package nst.app.manager;

import java.util.Date;
import java.util.List;
import nst.app.common.CEIBaseManager;
import nst.app.helper.LiftEscalatorDuplicateHelper;
import nst.app.model.forms.le.LiftEscalatorDuplicate;
import nst.app.repo.LiftEscalatorDuplicateRepository;
import nst.common.base.BaseRepository;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LiftEscalatorDuplicateManager extends CEIBaseManager<LiftEscalatorDuplicate> {

  @Autowired
  LiftEscalatorDuplicateRepository repository;

  @Autowired
  LiftEscalatorDuplicateHelper helper;

  @Override
  public BaseRepository<LiftEscalatorDuplicate> getRepository() {
    return repository;
  }

  @Override
  public LiftEscalatorDuplicate submitForm(LiftEscalatorDuplicate model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }

  public List<LiftEscalatorDuplicate> getMyApps() {
    return repository.findMyApps(LoginUserUtil.getLoginUser().getUserId());
  }

  public LiftEscalatorDuplicate search(String licenseNumber, Date issueDate,
      Date expiryDate) {
    return repository
        .findByLicenseNumberAndIssueDateAndExpiryDate(licenseNumber, issueDate, expiryDate);
  }
}