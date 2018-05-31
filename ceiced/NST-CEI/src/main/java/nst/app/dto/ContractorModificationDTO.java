package nst.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nst.app.CEIUtil;
import nst.app.common.HelperUtil;
import nst.common.ValidationUtil;
import nst.common.base.BaseModelDTO;
import nst.common.base.ResponseError;
import nst.dto.AttachmentDTO;
import nst.kernal.SystemConstants;
import nst.kernal.validation.PastDate;
import nst.util.JSONUtil;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * contains all the fields for Modification of Contractor
 *
 */



@Data
@NoArgsConstructor
public class ContractorModificationDTO extends BaseModelDTO {

  @Size(max = 5)
  private String isNameCorrection;

  @Pattern(regexp = "[a-zA-Z]")
  @Size(max = 100)
  private String applicantContractorName;

  @Size(max = 5)
  private String isAddressChange;

  @NotNull
  @Pattern(regexp = CEIUtil.CONTRACTOR_LICENSE, message = "Invalid LicenseNo")
  @Size(max = 100)
  private String contractorLicenseNo;

  @PastDate
  private String licenseNoIssueDate;

  public List<AttachmentDTO> attachments;

  @Override
  public String getApplicantName() {
    return applicantContractorName;
  }

  private AddressDTO officeAddress;

  @Override
  protected List<ResponseError> otherValidateBasic() {
    List<ResponseError> errors = new ArrayList<>();
    if (HelperUtil.toBoolean(this.isAddressChange) && this.officeAddress == null) {
      errors.add(ResponseError.create(SystemConstants.Rest.ERROR_BAD_REQUEST, "officeAddress", SystemConstants.Rest.ADDRESS_NULL));
    }
    return errors;
  }

  public static void main(String[] args) {
    ContractorModificationDTO portalUserDTO = new ContractorModificationDTO();
    portalUserDTO.setContractorLicenseNo("G/SNR/C-1000");
    System.out.println(JSONUtil.toJSON(ValidationUtil.validateFor(portalUserDTO, Pattern.class)));
  }
}