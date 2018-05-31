package nst.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nst.app.common.HelperUtil;
import nst.common.ValidationUtil;
import nst.common.base.BaseModelDTO;
import nst.common.base.ResponseError;
import nst.dto.AttachmentDTO;
import nst.kernal.SystemConstants;
import nst.kernal.validation.CombiDate;
import nst.kernal.validation.PastDate;
import nst.util.JSONUtil;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@CombiDate(
    startField = "IssueDate",
    endField = "LicenseExpiryDate",
    message = "Licence dates are invalid.")
public class ContractorRenewalDTO extends BaseModelDTO {

  @NotNull
  @Pattern(regexp="[a-zA-Z]")
  @Size(max = 100)
  private String contractorName;

  @NotNull
  @Size(max = 100)
  private String contractorLicNo;

  @PastDate
  private String issueDate;

  private String licenseExpiryDate;
  @NotNull
  @Pattern(regexp="[a-zA-Z]")
  @Size(max = 75)
  private String oldSupervisorName;

  @PastDate
  private String oldSupervisorBirthDate;
  @PastDate
  private String oldSupervisorJoineeDate;
  @PastDate
  private String oldSupervisorLeavingDate;

  @Size(max = 5)
  private String isWorkingOldSupervisor;

  @Pattern(regexp="[a-zA-Z]")
  @Size(max = 75)
  private String newSupervisorName;
  @PastDate
  private String newSupervisorBirthDate;
  @PastDate
  private String newSupervisorJoineeDate;

  @Size(max = 5)
  private String isAddressChange;

  private AddressDTO parmanentAddress;

  public List<AttachmentDTO> attachments;

  @Override
  protected List<ResponseError> otherValidateBasic() {
    List<ResponseError> errors = new ArrayList<>();
    if (HelperUtil.toBoolean(this.isAddressChange) && this.parmanentAddress == null) {
      errors.add(ResponseError.create(SystemConstants.Rest.ERROR_BAD_REQUEST, "parmanentAddress", SystemConstants.Rest.ADDRESS_NULL));
    }
    return errors;
  }

  @Override
  public String getApplicantName() {
    return contractorName;
  }

  public static void main(String[] args) {
    ContractorRenewalDTO dto = new ContractorRenewalDTO();
    dto.setIssueDate("2000-12-01T00:00:00");
    dto.setLicenseExpiryDate("2017-01-01T00:00:00");
    System.out.println(JSONUtil.toJSON(ValidationUtil.validateFor(dto, CombiDate.class)));

    dto.setIssueDate("2008-12-01T00:00:00");
    dto.setLicenseExpiryDate("2004-01-01T00:00:00");
    System.out.println(JSONUtil.toJSON(ValidationUtil.validateFor(dto, CombiDate.class)));

    dto.setIssueDate("2008-12-01T00:00:00");
    dto.setLicenseExpiryDate(null);
    System.out.println(JSONUtil.toJSON(ValidationUtil.validateFor(dto, CombiDate.class)));

    dto.setIssueDate(null);
    dto.setLicenseExpiryDate(null);
    System.out.println(JSONUtil.toJSON(ValidationUtil.validateFor(dto, CombiDate.class)));
  }
}