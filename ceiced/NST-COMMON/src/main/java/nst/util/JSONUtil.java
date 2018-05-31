package nst.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.IntStream;
import nst.common.base.BaseDTO;
import nst.common.error.AppException;
import nst.config.MyLogger;
import nst.kernal.SystemConstants;

public class JSONUtil {

  public static String toJSON(Object obj) {
    if (obj == null) {
      return null;
    }

    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Throwable throwable) {
      MyLogger.logError(throwable);
      throw AppException.createWithMessageCode(SystemConstants.Rest.ERROR_SERVER_SIDE,
          SystemConstants.JSON_CONVERSION_ERROR);
    }
  }

  public static <T> T fromJSON(String json, Class<T> aClass) {
    if (json == null) {
      return null;
    }

    try {
      return new ObjectMapper().readValue(json, aClass);
    } catch (Throwable throwable) {
      MyLogger.logError("fromJSON", json);
      MyLogger.logError(throwable);
      throw AppException.createWithMessageCode(SystemConstants.Rest.ERROR_SERVER_SIDE,
          SystemConstants.JSON_CONVERSION_ERROR);
    }
  }

  public static <P extends BaseDTO> P getDataDTO(String body, Class<P> dtoType) {
    try {
      JsonNode root = new ObjectMapper().readTree(body);
      JsonNode data = root.path("data");
      return new ObjectMapper().readValue(data.get(0).toString(), dtoType);
    } catch (Exception e) {
      MyLogger.logError("getDataDTO", body);
      MyLogger.logError(e);
      throw AppException.createWithMessageCode(SystemConstants.Rest.ERROR_SERVER_SIDE,
              SystemConstants.JSON_CONVERSION_ERROR);
    }
  }

  public static void findJsonDiff(HashMap<String, Object> map1, HashMap<String, Object> map2) {
    for (Entry<String, Object> entry : map1.entrySet()) {
      if (entry.getValue() instanceof List) {
        System.out.println(entry.getKey() + " List=> " + entry.getValue());
        continue;
      }
      if (entry.getValue() instanceof Number) {
        System.out.println(entry.getKey() + " Number=> " + entry.getValue());
        continue;
      }
      if (entry.getValue() instanceof String) {
        System.out.println(entry.getKey() + " String=> " + entry.getValue());
        continue;
      }
      if (entry.getValue() instanceof Map) {
        System.out.println(entry.getKey() + " Map=> " + entry.getValue());
        continue;
      }
      if (entry.getValue() != null) {
        System.out.println(
            entry.getKey() + " UNKNOWN => " + entry.getValue().getClass() + " => " + entry
                .getValue());
      }
    }
  }

  public static void findJsonDiff(List<Object> list1, List<Object> list2) {
    for (Object entry : list1) {
      if (entry instanceof List) {
        System.out.println("List=> " + entry);
        continue;
      }
      if (entry instanceof Number) {
        System.out.println("Number=> " + entry);
        continue;
      }
      if (entry instanceof String) {
        System.out.println("String=> " + entry);
        continue;
      }
      if (entry instanceof Map) {
        System.out.println("Map=> " + entry);
        continue;
      }
      if (entry != null) {
        System.out.println(
            " UNKNOWN => " + entry.getClass() + " => " + entry
        );
      }
    }
  }

  public static void main(String[] args) throws IOException {
    String json1 = "{\"id\":120,\"uniqueId\":\"20180108-WIREXM-UIYLX\",\"version\":null,\"type\":\"WIREXM\",\"status\":\"DRAFT\",\"applicationName\":\"SN FN\",\"transaction\":null,\"surname\":\"SN\",\"firstName\":\"FN\",\"middleName\":\"MN\",\"birthDate\":\"2017-12-01T00:00:00\",\"age\":20,\"gender\":\"Male\",\"parmanentAddress\":{\"id\":150,\"addressType\":\"PERMANENT\",\"houseNo\":\"55\",\"tenamentNo\":null,\"buildingName\":\"Arise\",\"state\":\"GUJARAT\",\"district\":\"Vadodara\",\"talukaName\":\"vadodara\",\"pincode\":\"390019\",\"addrLine1\":\"line1\",\"addrLine2\":\"Line2\",\"addressLine3\":null,\"village\":\"sankheda\"},\"temporaryAddress\":{\"id\":151,\"addressType\":\"TEMPORARY\",\"houseNo\":\"55\",\"tenamentNo\":null,\"buildingName\":\"Arise\",\"state\":\"GUJARAT\",\"district\":\"Vadodara\",\"talukaName\":\"vadodara\",\"pincode\":\"390019\",\"addrLine1\":\"line1\",\"addrLine2\":\"Line2\",\"addressLine3\":null,\"village\":\"sankheda\"},\"mobile\":\"7878282806\",\"altMobileNumber\":\"7878282806\",\"email\":\"vijay.parmar@email.com\",\"wmanEligibility\":\"MCA\",\"experiences\":[{\"id\":140,\"exam\":null,\"fromDate\":\"2011-09-02T02:50:12\",\"toDate\":\"2012-09-02T02:50:12\",\"wmanContractorLicenceNo\":\"WmanContractorLicenceNo\",\"wmanOrgSupFirmName\":\"setWmanOrgSupFirmName\",\"wmanSupSupervisorPermitNo\":\"setWmanSupSupervisorPermitNo\"},{\"id\":141,\"exam\":null,\"fromDate\":\"2011-09-02T02:50:12\",\"toDate\":\"2012-09-02T02:50:12\",\"wmanContractorLicenceNo\":\"WmanContractorLicenceNo\",\"wmanOrgSupFirmName\":\"setWmanOrgSupFirmName\",\"wmanSupSupervisorPermitNo\":\"setWmanSupSupervisorPermitNo\"},{\"id\":142,\"exam\":null,\"fromDate\":\"2013-09-02T02:50:12\",\"toDate\":\"2014-09-02T02:50:12\",\"wmanContractorLicenceNo\":\"WmanContractorLicenceNo\",\"wmanOrgSupFirmName\":\"setWmanOrgSupFirmName\",\"wmanSupSupervisorPermitNo\":\"setWmanSupSupervisorPermitNo\"}],\"preferredExamCentre\":\"Ahmedabad\",\"preferredLangMode\":\"English\",\"detail\":null,\"attachments\":[{\"id\":130,\"uniqueId\":null,\"version\":null,\"type\":null,\"status\":null,\"applicationName\":null,\"transaction\":null,\"applicationId\":120,\"fileId\":\"YIPMU\",\"relatedFieldName\":null,\"docTypeMapping\":\"json\",\"docName\":\"YIPMU.json\",\"absolutePath\":null,\"realFileName\":\"ceiced.json\",\"formPart\":\"1\",\"labelName\":\"School leaving certificate or matriculation certificate\",\"fieldIdentifier\":\"4.1\",\"portalVariableName\":\"school_leaving\",\"mimeType\":\"image/png\",\"dmsUrl\":null,\"uuid\":\"YIPMU\",\"attachments\":[]},{\"id\":131,\"uniqueId\":null,\"version\":null,\"type\":null,\"status\":null,\"applicationName\":null,\"transaction\":null,\"applicationId\":120,\"fileId\":\"FGJI7\",\"relatedFieldName\":null,\"docTypeMapping\":\"json\",\"docName\":\"FGJI7.json\",\"absolutePath\":null,\"realFileName\":\"ceiced.json\",\"formPart\":\"1\",\"labelName\":\"Attach electricty bill for address proof or any Govt. valid ID proof\",\"fieldIdentifier\":\"6.7\",\"portalVariableName\":\"electricty_bill\",\"mimeType\":\"image/png\",\"dmsUrl\":null,\"uuid\":\"FGJI7\",\"attachments\":[]},{\"id\":132,\"uniqueId\":null,\"version\":null,\"type\":null,\"status\":null,\"applicationName\":null,\"transaction\":null,\"applicationId\":120,\"fileId\":\"SWI5E\",\"relatedFieldName\":null,\"docTypeMapping\":\"json\",\"docName\":\"SWI5E.json\",\"absolutePath\":null,\"realFileName\":\"ceiced.json\",\"formPart\":\"1\",\"labelName\":\"Attach electricty bill for address proof or any Govt. valid ID proof\",\"fieldIdentifier\":\"7.7\",\"portalVariableName\":\"electricty_bill\",\"mimeType\":\"image/png\",\"dmsUrl\":null,\"uuid\":\"SWI5E\",\"attachments\":[]},{\"id\":133,\"uniqueId\":null,\"version\":null,\"type\":null,\"status\":null,\"applicationName\":null,\"transaction\":null,\"applicationId\":120,\"fileId\":\"RHZSR\",\"relatedFieldName\":null,\"docTypeMapping\":\"json\",\"docName\":\"RHZSR.json\",\"absolutePath\":null,\"realFileName\":\"ceiced.json\",\"formPart\":\"1\",\"labelName\":\"form K\",\"fieldIdentifier\":\"12.1\",\"portalVariableName\":\"form_k\",\"mimeType\":\"image/png\",\"dmsUrl\":null,\"uuid\":\"RHZSR\",\"attachments\":[]},{\"id\":134,\"uniqueId\":null,\"version\":null,\"type\":null,\"status\":null,\"applicationName\":null,\"transaction\":null,\"applicationId\":120,\"fileId\":\"HUIAO\",\"relatedFieldName\":null,\"docTypeMapping\":\"json\",\"docName\":\"HUIAO.json\",\"absolutePath\":null,\"realFileName\":\"ceiced.json\",\"formPart\":\"1\",\"labelName\":\"Copy of Form “I” verified by authority or his representative\",\"fieldIdentifier\":\"12.2\",\"portalVariableName\":\"form_i\",\"mimeType\":\"image/png\",\"dmsUrl\":null,\"uuid\":\"HUIAO\",\"attachments\":[]},{\"id\":135,\"uniqueId\":null,\"version\":null,\"type\":null,\"status\":null,\"applicationName\":null,\"transaction\":null,\"applicationId\":120,\"fileId\":\"MPSM1\",\"relatedFieldName\":null,\"docTypeMapping\":\"json\",\"docName\":\"MPSM1.json\",\"absolutePath\":null,\"realFileName\":\"ceiced.json\",\"formPart\":\"1\",\"labelName\":\"Attach a pasport size photograph\",\"fieldIdentifier\":\"15.1\",\"portalVariableName\":\"passport_photograph\",\"mimeType\":\"image/png\",\"dmsUrl\":null,\"uuid\":\"MPSM1\",\"attachments\":[]},{\"id\":136,\"uniqueId\":null,\"version\":null,\"type\":null,\"status\":null,\"applicationName\":null,\"transaction\":null,\"applicationId\":120,\"fileId\":\"ZRVW9\",\"relatedFieldName\":null,\"docTypeMapping\":\"json\",\"docName\":\"ZRVW9.json\",\"absolutePath\":null,\"realFileName\":\"ceiced.json\",\"formPart\":\"1\",\"labelName\":\"Attach a scan copy of signature\",\"fieldIdentifier\":\"16.1\",\"portalVariableName\":\"scan_signature\",\"mimeType\":\"image/png\",\"dmsUrl\":null,\"uuid\":\"ZRVW9\",\"attachments\":[]}]}";
    String json2 = "{\"id\":120,\"uniqueId\":\"20180108-WIREXM-UIYwLX\",\"version\":null,\"type\":\"WIREXM\",\"status\":\"DRAFT\",\"applicationName\":\"SN FN\",\"transaction\":null,\"surname\":\"SN\",\"firstName\":\"FN\",\"middleName\":\"MN\",\"birthDate\":\"2017-12-01T00:00:00\",\"age\":20,\"gender\":\"Male\",\"parmanentAddress\":{\"id\":150,\"addressType\":\"PERMANENT\",\"houseNo\":\"55\",\"tenamentNo\":null,\"buildingName\":\"Arise\",\"state\":\"GUJARAT\",\"district\":\"Vadodara\",\"talukaName\":\"vadodara\",\"pincode\":\"390019\",\"addrLine1\":\"line1\",\"addrLine2\":\"Line2\",\"addressLine3\":null,\"village\":\"sankheda\"},\"temporaryAddress\":{\"id\":151,\"addressType\":\"TEMPORARY\",\"houseNo\":\"55\",\"tenamentNo\":null,\"buildingName\":\"Arise\",\"state\":\"GUJARAT\",\"district\":\"Vadodara\",\"talukaName\":\"vadodara\",\"pincode\":\"390019\",\"addrLine1\":\"line1\",\"addrLine2\":\"Line2\",\"addressLine3\":null,\"village\":\"sankheda\"},\"mobile\":\"7878282806\",\"altMobileNumber\":\"7878282806\",\"email\":\"vijay.parmar@email.com\",\"wmanEligibility\":\"MCA\",\"experiences\":[{\"id\":140,\"exam\":null,\"fromDate\":\"2011-09-02T02:50:12\",\"toDate\":\"2012-09-02T02:50:12\",\"wmanContractorLicenceNo\":\"WmanContractorLicenceNo\",\"wmanOrgSupFirmName\":\"setWmanOrgSupFirmName\",\"wmanSupSupervisorPermitNo\":\"setWmanSupSupervisorPermitNo\"},{\"id\":141,\"exam\":null,\"fromDate\":\"2011-09-02T02:50:12\",\"toDate\":\"2012-09-02T02:50:12\",\"wmanContractorLicenceNo\":\"WmanContractorLicenceNo\",\"wmanOrgSupFirmName\":\"setWmanOrgSupFirmName\",\"wmanSupSupervisorPermitNo\":\"setWmanSupSupervisorPermitNo\"},{\"id\":142,\"exam\":null,\"fromDate\":\"2013-09-02T02:50:12\",\"toDate\":\"2014-09-02T02:50:12\",\"wmanContractorLicenceNo\":\"WmanContractorLicenceNo\",\"wmanOrgSupFirmName\":\"setWmanOrgSupFirmName\",\"wmanSupSupervisorPermitNo\":\"setWmanSupSupervisorPermitNo\"}],\"preferredExamCentre\":\"Ahmedabad\",\"preferredLangMode\":\"English\",\"detail\":null,\"attachments\":[{\"id\":130,\"uniqueId\":null,\"version\":null,\"type\":null,\"status\":null,\"applicationName\":null,\"transaction\":null,\"applicationId\":120,\"fileId\":\"YIPMU\",\"relatedFieldName\":null,\"docTypeMapping\":\"json\",\"docName\":\"YIPMU.json\",\"absolutePath\":null,\"realFileName\":\"ceiced.json\",\"formPart\":\"1\",\"labelName\":\"School leaving certificate or matriculation certificate\",\"fieldIdentifier\":\"4.1\",\"portalVariableName\":\"school_leaving\",\"mimeType\":\"image/png\",\"dmsUrl\":null,\"uuid\":\"YIPMU\",\"attachments\":[]},{\"id\":131,\"uniqueId\":null,\"version\":null,\"type\":null,\"status\":null,\"applicationName\":null,\"transaction\":null,\"applicationId\":120,\"fileId\":\"FGJI7\",\"relatedFieldName\":null,\"docTypeMapping\":\"json\",\"docName\":\"FGJI7.json\",\"absolutePath\":null,\"realFileName\":\"ceiced.json\",\"formPart\":\"1\",\"labelName\":\"Attach electricty bill for address proof or any Govt. valid ID proof\",\"fieldIdentifier\":\"6.7\",\"portalVariableName\":\"electricty_bill\",\"mimeType\":\"image/png\",\"dmsUrl\":null,\"uuid\":\"FGJI7\",\"attachments\":[]},{\"id\":132,\"uniqueId\":null,\"version\":null,\"type\":null,\"status\":null,\"applicationName\":null,\"transaction\":null,\"applicationId\":120,\"fileId\":\"SWI5E\",\"relatedFieldName\":null,\"docTypeMapping\":\"json\",\"docName\":\"SWI5E.json\",\"absolutePath\":null,\"realFileName\":\"ceiced.json\",\"formPart\":\"1\",\"labelName\":\"Attach electricty bill for address proof or any Govt. valid ID proof\",\"fieldIdentifier\":\"7.7\",\"portalVariableName\":\"electricty_bill\",\"mimeType\":\"image/png\",\"dmsUrl\":null,\"uuid\":\"SWI5E\",\"attachments\":[]},{\"id\":133,\"uniqueId\":null,\"version\":null,\"type\":null,\"status\":null,\"applicationName\":null,\"transaction\":null,\"applicationId\":120,\"fileId\":\"RHZSR\",\"relatedFieldName\":null,\"docTypeMapping\":\"json\",\"docName\":\"RHZSR.json\",\"absolutePath\":null,\"realFileName\":\"ceiced.json\",\"formPart\":\"1\",\"labelName\":\"form K\",\"fieldIdentifier\":\"12.1\",\"portalVariableName\":\"form_k\",\"mimeType\":\"image/png\",\"dmsUrl\":null,\"uuid\":\"RHZSR\",\"attachments\":[]},{\"id\":134,\"uniqueId\":null,\"version\":null,\"type\":null,\"status\":null,\"applicationName\":null,\"transaction\":null,\"applicationId\":120,\"fileId\":\"HUIAO\",\"relatedFieldName\":null,\"docTypeMapping\":\"json\",\"docName\":\"HUIAO.json\",\"absolutePath\":null,\"realFileName\":\"ceiced.json\",\"formPart\":\"1\",\"labelName\":\"Copy of Form “I” verified by authority or his representative\",\"fieldIdentifier\":\"12.2\",\"portalVariableName\":\"form_i\",\"mimeType\":\"image/png\",\"dmsUrl\":null,\"uuid\":\"HUIAO\",\"attachments\":[]},{\"id\":135,\"uniqueId\":null,\"version\":null,\"type\":null,\"status\":null,\"applicationName\":null,\"transaction\":null,\"applicationId\":120,\"fileId\":\"MPSM1\",\"relatedFieldName\":null,\"docTypeMapping\":\"json\",\"docName\":\"MPSM1.json\",\"absolutePath\":null,\"realFileName\":\"ceiced.json\",\"formPart\":\"1\",\"labelName\":\"Attach a pasport size photograph\",\"fieldIdentifier\":\"15.1\",\"portalVariableName\":\"passport_photograph\",\"mimeType\":\"image/png\",\"dmsUrl\":null,\"uuid\":\"MPSM1\",\"attachments\":[]},{\"id\":136,\"uniqueId\":null,\"version\":null,\"type\":null,\"status\":null,\"applicationName\":null,\"transaction\":null,\"applicationId\":120,\"fileId\":\"ZRVW9\",\"relatedFieldName\":null,\"docTypeMapping\":\"json\",\"docName\":\"ZRVW9.json\",\"absolutePath\":null,\"realFileName\":\"ceiced.json\",\"formPart\":\"1\",\"labelName\":\"Attach a scan copy of signature\",\"fieldIdentifier\":\"16.1\",\"portalVariableName\":\"scan_signature\",\"mimeType\":\"image/png\",\"dmsUrl\":null,\"uuid\":\"ZRVW9\",\"attachments\":[]}]}";

    ObjectMapper mapper = new ObjectMapper();
    JsonNode actualObj = mapper.readTree(json1);
    JsonNode other = mapper.readTree(json2);

    IntStream.rangeClosed(0, actualObj.size()).forEach(id ->{
      JsonNode jsonNode = actualObj.get(id);
    });

    boolean equals = actualObj.equals(other);
    System.out.println("equals = " + equals);
  }


  private static void testForMap() {
    HashMap<String, HashMap<String, String>> hashMap = fromJSON(
        "{\"id\":{\"NAME\":\"POP\",\"COunt\":\"33\"},\"addrLine1\":{\"NAME\":\"POP\",\"COunt\":\"33\"},\"addrLine2\":{\"NAME\":\"POP\",\"COunt\":\"33\"}}",
        HashMap.class);
    for (Entry<String, HashMap<String, String>> entry : hashMap.entrySet()) {
      System.out.println(entry.getKey());
      for (Entry<String, String> stringEntry : entry.getValue().entrySet()) {
        System.out.println(stringEntry.getKey() + " => " + stringEntry.getValue());
      }
      System.out.println("---------------------------------------");
    }
  }
}