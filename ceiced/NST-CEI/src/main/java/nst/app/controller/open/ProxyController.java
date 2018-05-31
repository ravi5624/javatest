package nst.app.controller.open;

import nst.app.service.FormService;
import nst.common.AbstractController;
import nst.common.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public/proxy")
public class ProxyController extends AbstractController {

  @Autowired
  FormService service;

  @RequestMapping(value = "/application")
  public BaseResponse submit(@RequestBody String body) {
    System.out.println("body = " + body);
    return BaseResponse.createSuccess("{'status':'OK'}");
  }

  @RequestMapping(value = "/query")
  public BaseResponse query(@RequestBody String body) {
    System.out.println("body = " + body);
    return BaseResponse.createSuccess("{'status':'OK'}");
  }
}