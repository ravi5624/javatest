package nst.app.manager;

import java.util.List;
import nst.app.common.CEIBaseManager;
import nst.app.helper.AgencyLicenseRenewalHelper;
import nst.app.model.forms.le.AgencyLicenseRenewal;
import nst.app.repo.AgencyLicenseRenewalRepository;
import nst.common.base.BaseRepository;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgencyLicenseRenewalManager extends CEIBaseManager<AgencyLicenseRenewal> {

  @Autowired
  AgencyLicenseRenewalRepository repository;

  @Autowired
  AgencyLicenseRenewalHelper helper;

  @Override
  public BaseRepository<AgencyLicenseRenewal> getRepository() {
    return repository;
  }

  @Override
  public AgencyLicenseRenewal submitForm(AgencyLicenseRenewal model) {
    performSubmit(model.getForm(), helper.toNGDTO(model));
    sendEmailOnSubmit(model);
    return repository.save(model);
  }

  public List<AgencyLicenseRenewal> getMyApps() {
    return repository.findMyApps(LoginUserUtil.getLoginUser().getUserId());
  }

}