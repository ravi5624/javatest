package nst.app.controller.common;

import nst.app.common.CEIBaseController;
import nst.app.common.CEIBaseService;
import nst.app.dto.OwnerDTO;
import nst.app.model.OwnerDetail;
import nst.app.service.OwnerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/itAgency")
public class IAndTAgencyController extends CEIBaseController<OwnerDetail, OwnerDTO> {

  @Autowired
  OwnerDetailService service;

  @Override
  public CEIBaseService<OwnerDetail, OwnerDTO> getService() {
    return service;
  }


}