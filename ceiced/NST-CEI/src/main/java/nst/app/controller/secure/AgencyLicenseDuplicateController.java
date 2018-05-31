package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.AgencyLicenseDuplicateDTO;
import nst.app.model.forms.le.AgencyLicenseDuplicate;
import nst.app.service.AgencyLicenseDuplicateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/agencyLicenseDuplicate")
public class AgencyLicenseDuplicateController extends
    CEIBaseController<AgencyLicenseDuplicate, AgencyLicenseDuplicateDTO> {

  @Autowired
  AgencyLicenseDuplicateService service;

  public AgencyLicenseDuplicateController() {
    super(AgencyLicenseDuplicateDTO.class);
  }

  public CEIBaseService<AgencyLicenseDuplicate, AgencyLicenseDuplicateDTO> getService() {
    return service;
  }
}