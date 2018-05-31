package nst.app.controller.common;

import nst.app.enums.UserType;
import nst.app.service.AgencyService;
import nst.common.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/agency")
public class AgencyController {

  @Autowired
  AgencyService service;

  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  public BaseResponse detail() {
    return BaseResponse.createSuccess(service.getDetail());
  }

  @RequestMapping(value = "/{type}", method = RequestMethod.GET)
  public BaseResponse getFor(@PathVariable("type") String type) {
    return BaseResponse.createSuccess(service.getFor(UserType.getByType(type)));
  }

  @RequestMapping(value = "/{type}/search", method = RequestMethod.GET)
  public BaseResponse search(@PathVariable("type") String type, @RequestParam("key") String key) {
    return BaseResponse.createSuccess(service.search(type, key));
  }

  @RequestMapping(value = "/license", method = RequestMethod.GET)
  public BaseResponse searchByLicense(@RequestParam(value = "licenseNo", defaultValue = "") String licenseNo) {
    return BaseResponse.createSuccess(service.searchByLicense(licenseNo));
  }

  @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
  public BaseResponse detail(@PathVariable("id") Long agencyId) {
    return BaseResponse.createSuccess(service.getDetail(agencyId));
  }
}