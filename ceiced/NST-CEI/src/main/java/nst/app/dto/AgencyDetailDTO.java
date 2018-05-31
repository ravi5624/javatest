package nst.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseModelDTO;

@Data
@NoArgsConstructor
public class AgencyDetailDTO extends BaseModelDTO {

  private String agencyName;
  private Long userId;
  private Long formId;
  private String licenseNumber;
  private String issueDate;
  private String expiryDate;
  private String mobileNumber;
  private String email;
  private AddressDTO agencyAddress;
}