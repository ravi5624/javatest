package nst.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public abstract class AbstractController implements Controller {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  @ResponseBody
  String index() {
    return "Welcome to " + this.getClass().getName();
  }
}
