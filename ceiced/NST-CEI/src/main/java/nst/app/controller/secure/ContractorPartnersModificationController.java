package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.ContractorPartnersModificationDTO;
import nst.app.lookups.LookupUtil;
import nst.app.model.forms.lb.ContractorPartnersModification;
import nst.app.service.ContractorPartnersModificationService;
import nst.common.base.BaseResponse;
import nst.kernal.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/contractorPartnersModification")
public class ContractorPartnersModificationController extends
    CEIBaseController<ContractorPartnersModification, ContractorPartnersModificationDTO> {

  @Autowired
  ContractorPartnersModificationService service;

  public ContractorPartnersModificationController() {
    super(ContractorPartnersModificationDTO.class);
  }

  public CEIBaseService<ContractorPartnersModification, ContractorPartnersModificationDTO> getService() {
    return service;
  }
  @RequestMapping(value = "/organizationType", method = RequestMethod.GET, consumes = SystemConstants.Rest.APPLICATION_JSON)
  public BaseResponse organizationType() {
    return BaseResponse.createSuccess(LookupUtil.organizationType());
  }

  @RequestMapping(value = "/{id}/addOrg", method = RequestMethod.GET)
  public BaseResponse addOrganization(@PathVariable("id") Long id) {
    return BaseResponse.createSuccess(service.addOrganization(id));
  }
  @RequestMapping(value = "/detailsofPartner/{id}/delete/{orgId}", method = RequestMethod.DELETE)
  public BaseResponse deleteOrganization(@PathVariable("id") Long id,
                                         @PathVariable("orgId") Long orgId) {
    service.deleteOrganization(id,orgId);
    return BaseResponse.createSuccess();
  }
}