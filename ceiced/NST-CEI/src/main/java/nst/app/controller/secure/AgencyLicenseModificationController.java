package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.AgencyLicenseModificationDTO;
import nst.app.lookups.LookupUtil;
import nst.app.model.forms.le.AgencyLicenseModification;
import nst.app.service.AgencyLicenseModificationService;
import nst.common.base.BaseResponse;
import nst.kernal.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/agencyLicenseModification")
public class AgencyLicenseModificationController extends
    CEIBaseController<AgencyLicenseModification, AgencyLicenseModificationDTO> {

  @Autowired
  AgencyLicenseModificationService service;

  public AgencyLicenseModificationController() {
    super(AgencyLicenseModificationDTO.class);
  }

  public CEIBaseService<AgencyLicenseModification, AgencyLicenseModificationDTO> getService() {
    return service;
  }

  @RequestMapping(value = "/getLegalStatus", method = RequestMethod.GET, consumes = SystemConstants.Rest.APPLICATION_JSON)
  public BaseResponse legalStatus() {
    return BaseResponse.createSuccess(  LookupUtil.getLegalStatus());
  }


  @RequestMapping(value = "/getVehiclePosessions", method = RequestMethod.GET, consumes = SystemConstants.Rest.APPLICATION_JSON)
  public BaseResponse vehiclePosessions() {
    return BaseResponse.createSuccess(  LookupUtil.getVehiclePosessions());
  }


  @RequestMapping(value = "/{id}/addLESafetyGadget", method = RequestMethod.GET)
  public BaseResponse addLESafetyGadgetDetails(@PathVariable("id") Long id, @RequestParam("multiple") Boolean multiple) {
    return BaseResponse.createSuccess(service.addLESafetyGadgetDetails(id,multiple));
  }

  @RequestMapping(value = "/{id}/addTestingInstrument", method = RequestMethod.GET)
  public BaseResponse addLETestingInstrumentDetails(@PathVariable("id") Long id,@RequestParam("multiple") Boolean multiple) {
    return BaseResponse.createSuccess(service.addLETestingInstrumentDetails(id,multiple));
  }
  @RequestMapping(value = "/{id}/addStaffDetails", method = RequestMethod.GET)
  public BaseResponse addForFormI(@PathVariable("id") Long id,@RequestParam("multiple") Boolean multiple) {
    return BaseResponse.createSuccess(service.addStaffDetails(id, multiple));
  }


  @RequestMapping(value = "/safetyGadget/{id}/delete/{safetyGadgetId}", method = RequestMethod.DELETE)
  public BaseResponse deleteLESafetyGadgetDetails(@PathVariable("id") Long id,
                                                  @PathVariable("safetyGadgetId") Long safetyGadgetId) {
    service.deleteLESafetyGadgetDetails(id,safetyGadgetId);
    return BaseResponse.createSuccess();
  }

  @RequestMapping(value = "/instrument/{id}/delete/{instumentId}", method = RequestMethod.DELETE)
  public BaseResponse deleteLETestingInstrumentDetails(@PathVariable("id") Long id,
                                                       @PathVariable("instumentId") Long instumentId) {
    service.deleteLETestingInstrumentDetails(id,instumentId);
    return BaseResponse.createSuccess();
  }

  @RequestMapping(value = "/staff/{id}/delete/{staffId}", method = RequestMethod.DELETE)
  public BaseResponse deleteStaffDetails(@PathVariable("id") Long id,
                                         @PathVariable("staffId") Long staffId) {
    service.deleteStaffDetails(id,staffId);
    return BaseResponse.createSuccess();
  }

}