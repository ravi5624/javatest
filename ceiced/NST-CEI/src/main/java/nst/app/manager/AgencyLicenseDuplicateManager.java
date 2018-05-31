package nst.app.manager;

import java.util.List;
import nst.app.NewgenUtil;
import nst.app.common.CEIBaseManager;
import nst.app.helper.AgencyLicenseDuplicateHelper;
import nst.app.model.forms.le.AgencyLicenseDuplicate;
import nst.app.repo.AgencyLicenseDuplicateRepository;
import nst.common.base.BaseRepository;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgencyLicenseDuplicateManager extends CEIBaseManager<AgencyLicenseDuplicate> {

  @Autowired
  AgencyLicenseDuplicateRepository repository;

  @Autowired
  AgencyLicenseDuplicateHelper helper;

  @Autowired
  NewgenUtil newgenUtil;

  @Override
  public BaseRepository<AgencyLicenseDuplicate> getRepository() {
    return repository;
  }

  @Override
  public AgencyLicenseDuplicate submitForm(AgencyLicenseDuplicate model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }

  public List<AgencyLicenseDuplicate> getMyApps() {
    return repository.findMyApps(LoginUserUtil.getLoginUser().getUserId());
  }
}