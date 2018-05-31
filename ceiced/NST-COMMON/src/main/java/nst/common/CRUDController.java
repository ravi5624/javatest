package nst.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class CRUDController extends AbstractController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  @ResponseBody
  String index() {
    return "Welcome to " + this.getClass().getName();
  }
}