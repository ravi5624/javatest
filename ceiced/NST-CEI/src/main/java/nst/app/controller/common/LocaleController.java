package nst.app.controller.common;

import nst.app.service.LocaleService;
import nst.common.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public/locale")
public class LocaleController {

    @Autowired
    LocaleService localeService;

    @RequestMapping(value = "/localeMsg", method = RequestMethod.GET)
    public BaseResponse localeMessage() {
        return BaseResponse.createSuccess(localeService.getAll(null));
    }
}
