package nst.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseModelDTO;
import nst.dto.AttachmentDTO;

import java.util.List;

@Data
@NoArgsConstructor
public class LiftEscalatorRenewalDTO extends BaseModelDTO {

  private String liftEscalatorType;

  private String licenseNumber;

  private String licenseIssueDate;

  private String licenseExpiryDate;

  private String licenseeFullName;

  private LEAddressDTO liftSiteAddress;

  private String isAddressOrOwnerChange;

  public List<AttachmentDTO> attachments;

  @Override
  public String getApplicantName() {
    return String.format("%s", nst.app.common.HelperUtil.fromString(licenseeFullName)).trim();
  }
}