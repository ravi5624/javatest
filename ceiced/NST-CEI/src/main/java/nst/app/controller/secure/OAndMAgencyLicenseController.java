package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.OAndMAgencyLicenseDTO;
import nst.app.lookups.LookupUtil;
import nst.app.model.forms.le.OAndMAgencyLicense;
import nst.app.service.OAndMAgencyLicenseService;
import nst.common.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/OAndMAgencyLicense")
public class OAndMAgencyLicenseController extends
    CEIBaseController<OAndMAgencyLicense, OAndMAgencyLicenseDTO> {

  @Autowired
  OAndMAgencyLicenseService service;

  public OAndMAgencyLicenseController() {
    super(OAndMAgencyLicenseDTO.class);
  }

  public CEIBaseService<OAndMAgencyLicense, OAndMAgencyLicenseDTO> getService() {
    return service;
  }

    @RequestMapping(value = "/qualification", method = RequestMethod.GET)
    public BaseResponse getQualification() {
        return BaseResponse.createSuccess(LookupUtil.getTechQualificationForSupervisor());
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public BaseResponse getEmployee() {
        return BaseResponse.createSuccess(LookupUtil.getEmployee());
    }

    @RequestMapping(value = "/occupancyRights", method = RequestMethod.GET)
    public BaseResponse getOccupancyRights() {
        return BaseResponse.createSuccess(LookupUtil.getOccupancyRights());
    }

    @RequestMapping(value = "/{id}/addAgencyLegalStatus", method = RequestMethod.GET)
    public BaseResponse addAgencyLegalStatusDetails(@PathVariable("id") Long id) {
        return BaseResponse.createSuccess(service.addAgencyLegalStatusDetails(id));
    }


    @RequestMapping(value = "/{id}/addLESafetyGadget", method = RequestMethod.GET)
    public BaseResponse addLESafetyGadgetDetails(@PathVariable("id") Long id,@RequestParam("multiple") Boolean multiple) {
        return BaseResponse.createSuccess(service.addLESafetyGadgetDetails(id,multiple));
    }

    @RequestMapping(value = "/{id}/addTestingInstrument", method = RequestMethod.GET)
    public BaseResponse addLETestingInstrumentDetails(@PathVariable("id") Long id,@RequestParam("multiple") Boolean multiple) {
        return BaseResponse.createSuccess(service.addLETestingInstrumentDetails(id,multiple));
    }
   @RequestMapping(value = "/{id}/addStaffDetails", method = RequestMethod.GET)
   public BaseResponse addStaffDetails(@PathVariable("id") Long id,@RequestParam("multiple") Boolean multiple) {
     return BaseResponse.createSuccess(service.addStaffDetails(id,multiple));
   }

   @RequestMapping(value = "/getVehiclePosessions", method = RequestMethod.GET)
   public BaseResponse vehiclePosessions() {
    return BaseResponse.createSuccess(  LookupUtil.getVehiclePosessions());
   }

    @RequestMapping(value = "/legalStatus/{id}/delete/{legalStatusId}", method = RequestMethod.DELETE)
  public BaseResponse deleteAgencyLegalStatusDetails(@PathVariable("id") Long id,
                                                     @PathVariable("legalStatusId") Long legalStatusId) {
    service.deleteAgencyLegalStatusDetails(id,legalStatusId);
    return BaseResponse.createSuccess();
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

    @RequestMapping(value = "/{id}/addAddress", method = RequestMethod.GET)
    public BaseResponse addAddress(@PathVariable("id") Long id) {
        return BaseResponse.createSuccess(service.addAddress(id));
    }

    @RequestMapping(value = "add/{id}/delete/{addId}", method = RequestMethod.DELETE)
    public BaseResponse deleteAddress(@PathVariable("id") Long id,
                                         @PathVariable("addId") Long addId) {
        service.deleteAddress(id, addId);
        return BaseResponse.createSuccess();
    }

}