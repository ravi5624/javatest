package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.ContractorLicenseDTO;
import nst.app.lookups.LookupUtil;
import nst.app.model.forms.lb.ContractorLicense;
import nst.app.service.ContractorLicenseService;
import nst.common.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/contractorLicense")
public class ContractorLicenseController extends
    CEIBaseController<ContractorLicense, ContractorLicenseDTO> {

  @Autowired
  ContractorLicenseService service;

  public ContractorLicenseController() {
    super(ContractorLicenseDTO.class);
  }

  public CEIBaseService<ContractorLicense, ContractorLicenseDTO> getService() {
    return service;
  }

  @RequestMapping(value = "/organizationTypes", method = RequestMethod.GET)
  public BaseResponse organizationTypes() {
    return BaseResponse.createSuccess(LookupUtil.organizationType());
  }

  @RequestMapping(value = "/{id}/addOrg", method = RequestMethod.GET)
  public BaseResponse addOrganization(@PathVariable("id") Long id) {
    return BaseResponse.createSuccess(service.addOrganization(id));
  }

  /*@RequestMapping(value = "/{id}/getFormI", method = RequestMethod.GET)
  public BaseResponse getFormI(@PathVariable("id") Long id) {
    return BaseResponse.createSuccess(service.getFormI(id));
  }

  @RequestMapping(value = "/{id}/addEmployer", method = RequestMethod.GET)
  public BaseResponse addForFormI(@PathVariable("id") Long id) {
    return BaseResponse.createSuccess(service.addEmployer(id));
  }

  @RequestMapping(value = "/{id}/saveFormI", method = RequestMethod.POST, consumes = SystemConstants.Rest.APPLICATION_JSON )
  public BaseResponse saveFormI(@PathVariable("id") Long id, @RequestBody String body) {
    return BaseResponse.createSuccess(service.saveFormI(id, JSONUtil.getDataDTO(body, FormIDTO.class)));
  }*/

  @RequestMapping(value = "/designation", method = RequestMethod.GET)
  public BaseResponse designation() {
    return BaseResponse.createSuccess(LookupUtil.getDesignation());
  }

  @RequestMapping(value = "/org/{formId}/delete/{orgId}", method = RequestMethod.DELETE)
  public BaseResponse deleteOrganization(@PathVariable("formId") Long formId, @PathVariable("orgId") Long orgId) {
    service.deleteOrganization(orgId, formId);
    return BaseResponse.createSuccess();
  }

}