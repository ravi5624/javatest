package nst.app.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import nst.app.CEIUtil;
import nst.common.base.BaseModelDTO;
import nst.dto.AttachmentDTO;
import nst.kernal.validation.PastDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


/**
 * contains all the fields for Modification of Contractor Partner
 *
 */

@Data
@NoArgsConstructor
public class ContractorPartnersModificationDTO extends BaseModelDTO {

  @NotNull
  @Pattern(regexp = "[a-zA-Z]")
  @Size(max = 100)
  private String applicantContractorName;

  @Pattern(regexp = CEIUtil.CONTRACTOR_LICENSE, message = "Invalid LicenseNo")
  @Size(max = 100)
  private String contractorLicenseNo;

  @PastDate
  private String licenseIssueDate;

  @Size(max = 100)
  private String organizationType;

  private List<OrganizationDetailsDTO> partners;

  public List<AttachmentDTO> attachments;

  @Override
  public String getApplicantName() {
    return applicantContractorName;
  }

}