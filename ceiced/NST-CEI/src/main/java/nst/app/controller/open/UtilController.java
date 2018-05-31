package nst.app.controller.open;

import nst.common.AbstractController;
import nst.kernal.SystemConstants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public/util")
public class UtilController extends AbstractController {

  @RequestMapping(value = "/local", method = RequestMethod.GET, produces = SystemConstants.Rest.APPLICATION_JSON)
  public String local() {
    return "{\"welcomePortalMessage\":{\"key\":\"welcomePortalMessage\",\"en\":\"Welcome to CEICED Application Portal\",\"gu\":\"fdafdsaafdasf1\"}}";
  }
}
