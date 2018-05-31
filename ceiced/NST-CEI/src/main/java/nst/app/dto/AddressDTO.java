package nst.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import nst.app.enums.AddressType;
import nst.common.base.BaseDTO;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * this holds information for address
 */

@Data
@JsonInclude(Include.ALWAYS)
public class AddressDTO extends BaseDTO {

  private Long id;

  private String addressType;

  @Pattern(regexp="/^[ A-Za-z0-9_/,]*$/")
  private String houseNo;

  @Pattern(regexp="/^[ A-Za-z0-9_/,]*$/")
  private String tenamentNo;

  @Pattern(regexp="/^[ A-Za-z0-9_/,]*$/")
  @Size(max = 50)
  private String buildingName;

  @Size(max = 50)
  private String state;

  @Size(max = 50)
  private String district;

  @Pattern(regexp="[a-zA-Z]")
  @Size(max = 50)
  private String talukaName;

  @Pattern(regexp="[^[0-9]{1,6}$]")
  private String pincode;

  @Size(max = 100)
  private String addrLine1;

  @Size(max = 100)
  private String addrLine2;

  @Size(max = 100)
  private String addressLine3;

  @Size(max = 50)
  private String village;

  public AddressDTO clear(Boolean flag){
    if(!flag){
      this.houseNo = null;
      this.tenamentNo = null;
      this.buildingName = null;
      this.state = null;
      this.district = null;
      this.talukaName = null;
      this.pincode = null;
      this.addrLine1 = null;
      this.addrLine2 = null;
      this.addressLine3 = null;
      this.village = null;
    }
    return this;
  }

  @JsonIgnore
  public AddressType getAddressTypeEnum() {
    return AddressType.valueOf(addressType);
  }
}