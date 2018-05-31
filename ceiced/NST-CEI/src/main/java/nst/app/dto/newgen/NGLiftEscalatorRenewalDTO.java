package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;
import nst.app.dto.LEAddressDTO;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGLiftEscalatorRenewalDTO extends NGBaseDTO {

    String type;

    String licenseNumber;

    String liftEscalatorType;

    String licenseIssue;

    String licenseExpiry;

    String licenseeFullName;

    Boolean isAddressOrOwnerChange;
    LEAddressDTO liftSiteAddress;

    /*ToDo: JSON Missing*/
    NGLIAddressDTO premisesAddress;
    /*
  "premisesAddress":{
    "id": 200,
  "siteName": "siteName",
  "schemeNo": "schemeNo",
  "fpNo": "fpNo",
  "rsNo": "rsNo",
  "subPlotNo": "subPlotNo",
  "tenamentNo":"tenamentNo",
  "citySurveyNo": "citySurveyNo",
    "houseNo": "55",
    "buildingName": "Arise",
    "state": "GUJARAT",
    "district": "Vadodara",
    "talukaName": "vadodara",
    "pincode": "390019",
    "addressType": "BUSINESS",
    "village": "sankheda",
  },
    */

    public List<NGAttachmentDTO> attachments;

}