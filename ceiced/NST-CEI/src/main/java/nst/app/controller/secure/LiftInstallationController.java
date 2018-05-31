package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.LiftInstallationDTO;
import nst.app.lookups.LookupUtil;
import nst.app.model.forms.le.LiftInstallation;
import nst.app.service.LiftInstallationService;
import nst.common.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/liftInstallation")
public class LiftInstallationController extends
    CEIBaseController<LiftInstallation, LiftInstallationDTO> {

  public LiftInstallationController() {
    super(LiftInstallationDTO.class);
  }

  @Autowired
  LiftInstallationService service;

  public CEIBaseService<LiftInstallation, LiftInstallationDTO> getService() {
    return service;
  }

  @RequestMapping(value = "/liftApplication", method = RequestMethod.GET)
  public BaseResponse getLiftApplication() {
    return BaseResponse.createSuccess(LookupUtil.getLiftApplication());
  }

  @RequestMapping(value = "/liftTypes", method = RequestMethod.GET)
  public BaseResponse getLiftTypes() {
    return BaseResponse.createSuccess(LookupUtil.getLiftTypes());
  }

  @RequestMapping(value = "/liftSubCategoryOfLifts", method = RequestMethod.GET)
  public BaseResponse getSubCategoryOfLifts() {
    return BaseResponse.createSuccess(LookupUtil.getSubCategoryOfLifts());
  }

  @RequestMapping(value = "/controlLiftMethods", method = RequestMethod.GET)
  public BaseResponse getControlLiftMethods() {
    return BaseResponse.createSuccess(LookupUtil.getControlLiftMethods());
  }

  @RequestMapping(value = "/carDoorDetails", method = RequestMethod.GET)
  public BaseResponse getCarDoorDetails() {
    return BaseResponse.createSuccess(LookupUtil.getCarDoorDetails());
  }

  @RequestMapping(value = "/landingDoorDetails", method = RequestMethod.GET)
  public BaseResponse getLandingDoorDetails() {
    return BaseResponse.createSuccess(LookupUtil.getLandingDoorDetails());
  }

  @RequestMapping(value = "/counterWeightPositions", method = RequestMethod.GET)
  public BaseResponse getCounterWeightPositions() {
    return BaseResponse.createSuccess(LookupUtil.getCounterWeightPositions());
  }

  @RequestMapping(value = "/carBufferTypes", method = RequestMethod.GET)
  public BaseResponse getCarBufferTypes() {
    return BaseResponse.createSuccess(LookupUtil.getCarBufferTypes());
  }

}