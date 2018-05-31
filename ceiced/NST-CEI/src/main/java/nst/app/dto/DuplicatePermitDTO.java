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

@Data
@NoArgsConstructor
public class DuplicatePermitDTO extends BaseModelDTO {

  @NotNull
  @Pattern(regexp="[a-zA-Z]")
  @Size(max = 25)
  private String surname;

  @NotNull
  @Pattern(regexp="[a-zA-Z]")
  @Size(max = 25)
  private String firstName;
  @NotNull
  @Pattern(regexp="[a-zA-Z]")
  @Size(max = 25)
  private String middleName;

  public List<AttachmentDTO> attachments;
    @NotNull
    @Size(max = 50)
    private List<String> duplicateType;
    @NotNull
  @Size(max = 100)
  private String permitNo;
    @Valid
    @NotNull
    private AddressDTO parmanentAddress;
    @NotNull
    @PastDate
    private String permitIssueDate;

    @Pattern(regexp = "(^$|[0-9]{10})")
    private String altMobile;
  @NotNull
  @Pattern(regexp="(^$|[0-9]{10})")
  private String mobile;
    @NotNull
  @Email
  @Size(max = 100)
  private String email;

  @Override
  public String getApplicantName() {
    return String.format("%s %s", nst.app.common.HelperUtil.fromString(surname), nst.app.common.HelperUtil.fromString(firstName)).trim();
  }
  public String getApplicantFullName() {
    return String.format("%s %s %s", nst.app.common.HelperUtil.fromString(surname), nst.app.common.HelperUtil.fromString(firstName), nst.app.common.HelperUtil.fromString(middleName)
    ).trim();
  }

}