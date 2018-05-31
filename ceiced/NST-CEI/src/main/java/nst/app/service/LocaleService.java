package nst.app.service;

import java.util.HashMap;
import java.util.Map;

import nst.app.CEIUtil;
import nst.app.enums.UserType;
import nst.app.helper.LocaleHelper;
import nst.app.manager.LocaleManager;
import nst.common.AbstractService;
import nst.common.security.LoginUser;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class LocaleService extends AbstractService {

    @Autowired
    LocaleManager manager;

    @Autowired
    LocaleHelper helper;

    public Map<String, Map<String, Map<String, String>>> getAll(UserType myUserType) {
        Map<String, Map<String, Map<String, String>>> screenMap = new HashMap<>();
        manager.findAll().forEach(model -> {
            Map<String, Map<String, String>> keyMap = screenMap.get(model.getScreen());
            if(keyMap == null) {
                keyMap = new HashMap<>();
                screenMap.put(model.getScreen(), keyMap);
            }
            Map<String, String> langMap = keyMap.get(model.getKey());
            if (langMap == null) {
                langMap = new HashMap<>();
                keyMap.put(model.getKey(), langMap);
            }
            String regex = CEIUtil.WIREMAN_SUPERVISOR_REPLACE;
            String data = AllUtil.extractData(model.getValue(), regex, 0);
            langMap.put(model.getLang(), myUserType == null ? model.getValue() :
                    model.getValue().trim().replaceAll(regex, data != null && Character.isLowerCase(data.charAt(0)) ? myUserType.getName().toLowerCase() : myUserType.getName())
            );

        });
        return screenMap;
    }
    public static void main(String args[])
    {
        String str="WIREMAN CERTIFICATE AND PERMIT";
        String regex = "(?i)(Wireman|Supervisor)(\\s)(&|/|-|OR|\\s)(\\s)(Wireman|Supervisor)(?-i)";
        String data = AllUtil.extractData(str, regex, 0);
        String finalStr=str.replaceAll(regex, data != null && Character.isLowerCase(data.charAt(0)) ? "supervisor" : "Supervisor");
        System.out.println("final str::"+finalStr);
    }


}
