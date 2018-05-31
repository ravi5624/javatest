package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.AgencyLicenseRenewalDTO;
import nst.app.model.forms.le.AgencyLicenseRenewal;
import nst.app.service.AgencyLicenseRenewalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/agencyLicenseRenewal")
public class AgencyLicenseRenewalController extends
    CEIBaseController<AgencyLicenseRenewal, AgencyLicenseRenewalDTO> {

  @Autowired
  AgencyLicenseRenewalService service;

  public AgencyLicenseRenewalController() {
    super(AgencyLicenseRenewalDTO.class);
  }

  public CEIBaseService<AgencyLicenseRenewal, AgencyLicenseRenewalDTO> getService() {
    return service;
  }
}