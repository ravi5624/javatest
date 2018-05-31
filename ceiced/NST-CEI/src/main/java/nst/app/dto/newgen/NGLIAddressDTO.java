package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
@JsonInclude(Include.ALWAYS)
@JsonIgnoreProperties({ "country", "addrLine1", "addrLine2", "village", "premisesName" })
public class NGLIAddressDTO extends NGLiftEscalatorAddressDTO {

  String premisesName;
  String siteName;
  String schemeNo;
  String fpNo;
  String rsNo;
  String subPlotNo;
  String citySurveyNo;
  String tenamentNo;
}