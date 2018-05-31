package nst.app.service;

import nst.app.common.CEIBaseService;
import nst.app.dto.AgencyLicenseDuplicateDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.AgencyLicenseDuplicateHelper;
import nst.app.manager.AgencyLicenseDuplicateManager;
import nst.app.model.forms.le.AgencyLicenseDuplicate;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AgencyLicenseDuplicateService extends
    CEIBaseService<AgencyLicenseDuplicate, AgencyLicenseDuplicateDTO> {

  @Autowired
  AgencyLicenseDuplicateManager manager;

  @Autowired
  AgencyLicenseDuplicateHelper helper;

  @Override
  public AgencyLicenseDuplicateDTO create() {
//    validateFormExist method used to restrict creation of duplicate form.
//    validateFormExist(applicationType());
    return super.create();
  }

  public List<AgencyLicenseDuplicateDTO> getMyApps() {
    return helper.fromModel(manager.getMyApps());
  }
  @Override
  public BaseManager<AgencyLicenseDuplicate> getManager() {
    return manager;
  }

  @Override
  public BaseHelper<AgencyLicenseDuplicate, AgencyLicenseDuplicateDTO> getHelper() {
    return helper;
  }

  public ApplicationType applicationType() {
    return ApplicationType.ALDUPL;
  }
}