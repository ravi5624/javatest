package nst.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseModelDTO;
import nst.dto.AttachmentDTO;
import nst.kernal.validation.PastDate;
import org.hibernate.validator.constraints.Email;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * contains all the fields for License of Contractor
 *
 */

@Data
@NoArgsConstructor
public class ContractorLicenseDTO extends BaseModelDTO {

  @NotNull
  @Pattern(regexp = "[a-zA-Z]")
  @Size(max = 100)
  private String applicantContractorName;

  @Pattern(regexp="(^$|[0-9]{10})")
  private String mobile;

  @Email
  @Size(max = 100)
  private String email;

  @Valid
  @NotNull
  private AddressDTO officeAddress;
  @Size(max = 25)
  private String organizationType;

  private List<OrganizationDetailsDTO> organizations;

  private FormIDTO formI;

  @Size(max = 5)
  private String isLicenseGranted;
  @Size(max = 100)
  private String contractorLicNo;

  @PastDate
  private String issueDate;
  @NotNull
  @Pattern(regexp = "[a-zA-Z]")
  @Size(max = 75)
  private String supervisorName;

  private Integer supervisorAge;

  @Size(max = 100)
  private String permitNoOfSupervisor;

  @PastDate
  private String supervisorPermitIssueDate;
  @PastDate
  private String supervisorBirthDate;

  @NotNull
  @Pattern(regexp = "[a-zA-Z]")
  @Size(max = 100)
  private String contractorName;

  @Size(max = 100)
  private String contractorLicenseNo;
  @PastDate
  private String contractorFromDate;
  @PastDate
  private String contractorToDate;

  public List<AttachmentDTO> attachments;

  @Override
  public String getApplicantName() {
    return applicantContractorName;
  }
}