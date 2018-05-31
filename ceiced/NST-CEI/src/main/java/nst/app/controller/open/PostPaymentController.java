package nst.app.controller.open;

import nst.app.service.FormService;
import nst.common.AbstractController;
import nst.common.base.BaseResponse;
import nst.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public/payment")
public class PostPaymentController extends AbstractController {

  @Autowired
  FormService service;

  @RequestMapping(value = "/post")
  public BaseResponse postPayment(
      @RequestParam(value = "transactionId") String transactionId,
      @RequestParam(value = "status") String status) {
    TransactionDTO dto = service.postPayment(transactionId, status);
    //ToDo: Redirect to UI URL.
    return BaseResponse.createSuccess(dto);
  }

  @RequestMapping(value = "/respond", method = RequestMethod.GET, produces = "text/html")
  public String respond() {
    return "<!DOCTYPE html>\n"
        + "<html lang=\"en\">\n"
        + "<head>\n"
        + "  <meta charset=\"UTF-8\">\n"
        + "  <title>Post Payment</title>\n"
        + "</head>\n"
        + "<body>\n"
        + "<form action=\"http://nascentlayers.com/cei/\" method=\"get\">\n"
        + "  Request JSON:<br/>\n"
        + "  TransactionId: <input name=\"transactionId\" rows=\"20\" cols=\"50\">\n"
        + "  <br/>\n"
        + "  Status : <input name=\"status\" rows=\"20\" cols=\"50\" value=\"SUCCESS\">\n"
        + "  <br/>\n"
        + "  <button type=\"submit\" name=\"Submit\">Submit</button>\n"
        + "</form>\n"
        + "</body>\n"
        + "</html>";
  }
}