package nst.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nst.app.enums.AgencyType;
import nst.common.base.BaseModelDTO;
import nst.dto.AttachmentDTO;
import nst.kernal.validation.CombiDate;
import nst.kernal.validation.PastDate;
import org.hibernate.validator.constraints.Email;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * contains all the fields for Renewal of Agency License
 *
 */



@Data
@CombiDate(
        startField = "IssueDate",
        endField = "ExpiryDate",
        message = "Licence dates are invalid.")
@NoArgsConstructor
public class AgencyLicenseRenewalDTO extends BaseModelDTO {

  private AgencyType agencyType;

  @Size(max = 50)
  private String agencyAuthNo;

  @PastDate
  private String issueDate;

  private String expiryDate;

  private String nameOfAgency;

  @Valid
  @NotNull
  private AddressDTO officeAddress;

  @Email
  private String email;

  private String contactNo;

  private String websiteUrl;

  private String isChangeAnyDetails;

  private List<AttachmentDTO> attachments;

  @Override
  public String getApplicantName() {
      return String.format("%s", nst.app.common.HelperUtil.fromString(nameOfAgency)).trim();
  }
}