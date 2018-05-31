package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import nst.common.base.BaseDTO;

@Data
@JsonInclude(Include.ALWAYS)
public class NGAddressDTO extends BaseDTO {

  private Long id;
  private String addrLine1;
  private String addrLine2;
  private String addrLine3;
  private String addressType;
  private String houseNo;
  private String buildingName;
  private String state;
  private String district;
  private String talukaName;
  private String village;
  private String pincode;
}