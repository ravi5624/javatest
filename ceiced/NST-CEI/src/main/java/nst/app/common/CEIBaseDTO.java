package nst.app.common;

import lombok.Data;
import nst.common.base.BaseModelDTO;

@Data
public class CEIBaseDTO extends BaseModelDTO {

  private String licenseNumber;
  private String issueDate;
  private String expiryDate;
}
