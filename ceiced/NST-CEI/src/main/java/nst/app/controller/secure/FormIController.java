package nst.app.controller.secure;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.FormIDTO;
import nst.app.dto.FormIEmployerDTO;
import nst.app.lookups.LookupUtil;
import nst.app.model.forms.lb.FormI;
import nst.app.service.FormIService;
import nst.common.base.BaseResponse;
import nst.kernal.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/formI")
public class FormIController extends
    CEIBaseController<FormI, FormIDTO> {

  @Autowired
  FormIService service;

  public FormIController() {
    super(FormIDTO.class);
  }

  public CEIBaseService<FormI, FormIDTO> getService() {
    return service;
  }
  @RequestMapping(value = "/designation", method = RequestMethod.GET, consumes = SystemConstants.Rest.APPLICATION_JSON)
  public BaseResponse designation() {
    return BaseResponse.createSuccess(LookupUtil.getDesignation());
  }

  @RequestMapping(value = "/{id}/add", method = RequestMethod.GET)
  public BaseResponse addEmployer(@PathVariable("id") Long id) {
    FormIEmployerDTO dto=(FormIEmployerDTO)service.addEmployer(id);
    return BaseResponse.createSuccess(dto);
  }

  @RequestMapping(value = "/empId/{id}/delete/{empId}", method = RequestMethod.DELETE)
  public BaseResponse deleteEmployer(@PathVariable("id") Long id,
                                     @PathVariable("empId") Long empId) {
    service.deleteEmployer(id,empId);
    return BaseResponse.createSuccess();
  }
}