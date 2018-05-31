package nst.app;

import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.CommonForm;
import nst.common.security.LoginUser;
import nst.dto.EmailDTO;
import nst.util.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CEIUtil {

  public static final String distcts = "(GNR|SKT|BKT|ARV|MRB|RJK|BVN|AHD|BTD|KTC|SRT|BRC|VLD|NVR|KHD|AND|BRD|PCM|CHU|MHS|DHD|JNG|JMR|PBR|SNR|DVD|GRS|MEH|PTN|AMR|DNG|NRM|PNM|TPI)";

  public static final String CONTRACTOR_LICENSE = "^G/" + distcts + "/C(/|-)[0-9]\\d*";
  public static final String SUPERVISOR_LICENSE = "^G/" + distcts + "/[A-Z]{2}/[0-9]{4}/[0-9]{2}/+[1-9]{1}+[0-9]{3}";
  public static final String WIREMAN_LICENSE = "^G/" + distcts + "/[A-Z]{2}(-)[0-9]{4}/([0-9]{1,2}/|[0-9]{0})+[1-9]{1}+[0-9]{3}";
  public static final String SUPERVISOR_EXEM_LICENSE = "^G/" +"[A-Z]{2}[-]{1}+[A-Z]{1}+[-]{1}+[0-9]{6}[-]{1}+[A-Z]{3}+[-]{1}+[1-9]{1}+[0-9]{3}";
  public static final String WIREMAN_EXEM_LICENSE = "^G/" +"[A-Z]{2}[-]{1}+[A-Z]{1}+[-]{1}+[0-9]{4}[-]{1}+[([A-Z]{1,4})]+[([A-Z]*)]+[-]{1}+[1-9]{1}+[0-9]{3}";
  public static final String WIREMAN_SUPERVISOR_REPLACE="(?i)(Wireman|Supervisor)(\\s)(&|/|-|OR|\\s)(\\s)(Wireman|Supervisor)(?-i)";


  public static PortalUser getLoginUser() {
    LoginUser loginUser = LoginUserUtil.getLoginUser();
    PortalUser portalUser = new PortalUser();
    portalUser.setId(loginUser.getUserId());
    return portalUser;
  }

  public static String getFileNo(ApplicationType applicationType) {
    return AllUtil.formatFlatDate(new Date()) + "-" + applicationType.getType() + "-" + RandomUtil.getWord(5).toUpperCase();
  }

    public static EmailDTO emailOnFileStatusUpdate(CommonForm form, Date date, FileStatus fileStatus, String emailFormat) {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("userName", form.getUser().getUserName());
        dataMap.put("applicationName", form.getApplicationType().getName());
        dataMap.put("fileNumber", form.getFileNumber());
        dataMap.put("date", date.toString());
        dataMap.put("applicationAction", fileStatus.getType());
        String body = ResourceReader.readAsString(emailFormat);

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setBody(VelocityFormatter.format(body, dataMap));
        emailDTO.setTo(form.getUser().getEmail());
        emailDTO.setSubject(fileStatus.getType());

        return emailDTO;
    }
}
