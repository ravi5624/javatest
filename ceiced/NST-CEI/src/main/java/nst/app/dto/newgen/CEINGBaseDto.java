package nst.app.dto.newgen;

import lombok.Data;

@Data
public class CEINGBaseDto extends NGBaseDTO {
  private String licenseNumber;
  private String issueDate;
  private String expiryDate;
}