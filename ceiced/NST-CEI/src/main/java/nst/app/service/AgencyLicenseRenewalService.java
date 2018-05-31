package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.AgencyLicenseRenewalDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.AgencyLicenseRenewalHelper;
import nst.app.manager.AgencyLicenseRenewalManager;
import nst.app.model.forms.le.AgencyLicenseRenewal;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AgencyLicenseRenewalService extends
    CEIBaseService<AgencyLicenseRenewal, AgencyLicenseRenewalDTO> {

  @Autowired
  AgencyLicenseRenewalManager manager;

  @Autowired
  AgencyLicenseRenewalHelper helper;

  @Override
  public BaseManager<AgencyLicenseRenewal> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<AgencyLicenseRenewal, AgencyLicenseRenewalDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.ALRENE;
  }
}