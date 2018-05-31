package nst.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@JsonInclude(Include.ALWAYS)
public class LEAddressDTO extends AddressDTO {

  @Size(max = 50)
  String premisesName;

  @Size(max = 50)
  String siteName;

  @Size(max = 50)
  String schemeNo;

  @Size(max = 50)
  String fpNo;

  @Size(max = 50)
  String rsNo;

  @Size(max = 50)
  String subPlotNo;

  @Size(max = 50)
  String citySurveyNo;

  @Size(max = 50)
  String country;

}