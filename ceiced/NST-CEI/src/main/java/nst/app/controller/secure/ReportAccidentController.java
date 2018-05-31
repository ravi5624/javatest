package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.ReportAccidentDTO;
import nst.app.lookups.LookupUtil;
import nst.app.model.forms.le.ReportAccident;
import nst.app.service.ReportAccidentService;
import nst.common.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/reportAccident")
public class ReportAccidentController extends
    CEIBaseController<ReportAccident, ReportAccidentDTO> {

  @Autowired
  ReportAccidentService service;

  public ReportAccidentController() {
    super(ReportAccidentDTO.class);
  }

  public CEIBaseService<ReportAccident, ReportAccidentDTO> getService() {
    return service;
  }

  @RequestMapping(value = "/genderOfVictim", method = RequestMethod.GET)
  public BaseResponse getGenderOfVictim() {
    return BaseResponse.createSuccess(LookupUtil.getGenderOfVictim());
  }

  @RequestMapping(value = "/typeOfAccident", method = RequestMethod.GET)
  public BaseResponse getTypeOfAccident() {
    return BaseResponse.createSuccess(LookupUtil.getTypeOfAccident());
  }

  @RequestMapping(value = "/{id}/add", method = RequestMethod.GET)
  public BaseResponse addAccidentVictim(@PathVariable("id") Long id) {
    return BaseResponse.createSuccess(service.addAccidentVictim(id));
  }

  @RequestMapping(value = "/{id}/delete/{victimId}", method = RequestMethod.DELETE)
  public BaseResponse deleteExperience(@PathVariable("id") Long id,
                                       @PathVariable("victimId") Long victimId) {
    service.deleteAccidentVictim(id, victimId);
    return BaseResponse.createSuccess();
  }
}