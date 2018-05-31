package nst.app.controller.common;

import nst.app.service.LookupService;
import nst.app.service.PortalUserService;
import nst.common.AbstractController;
import nst.common.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/lookup")
public class LookupController extends AbstractController {

  @Autowired
  PortalUserService service;

  @Autowired
  LookupService lookupService;

  @RequestMapping(value = "/state", method = RequestMethod.GET)
  public BaseResponse state() {
    return BaseResponse.createSuccess(lookupService.getAllState());
  }

  @RequestMapping(value = "/statewithOther", method = RequestMethod.GET)
  public BaseResponse statewithOther() {
    return BaseResponse.createSuccess(lookupService.getAllStateWithOther());
  }

  @RequestMapping(value = "/district", method = RequestMethod.GET)
  public BaseResponse district() {
    return BaseResponse.createSuccess(lookupService.getAllDistrict());
  }

  @RequestMapping(value = "/districtByStateId/{stateId}", method = RequestMethod.GET)
  public BaseResponse districtByStateId(@PathVariable("stateId") Long stateId) {
    return BaseResponse.createSuccess(lookupService.getAllDistrictByStateId(stateId));
  }

  @RequestMapping(value = "/districtByStateName/{stateName}", method = RequestMethod.GET)
  public BaseResponse districtByStateName(@PathVariable("stateName") String stateName) {
    return BaseResponse.createSuccess(lookupService.getAllDistrictByStateName(stateName));
  }

  @RequestMapping(value = "/talukaByDistrictId/{districtId}", method = RequestMethod.GET)
  public BaseResponse talukaByDistrictId(@PathVariable("districtId") Long districtId) {
    return BaseResponse.createSuccess(lookupService.getAllTalukaByDistrictId(districtId));
  }

  @RequestMapping(value = "/talukaByDistrictName/{districtName}", method = RequestMethod.GET)
  public BaseResponse talukaByDistrictName(@PathVariable("districtName") String districtName) {
    return BaseResponse.createSuccess(lookupService.getAllTalukaByDistrictName(districtName));
  }


}