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
public class WiremanExaminationDTO extends BaseModelDTO {

  @NotNull
  @Pattern(regexp = "[a-zA-Z]")
  @Size(max = 25)
  private String surname;

  @NotNull
  @Pattern(regexp = "[a-zA-Z]")
  @Size(max = 25)
  private String firstName;

  @NotNull
  @Pattern(regexp = "[a-zA-Z]")
  @Size(max = 25)
  private String middleName;

  @NotNull
  @PastDate("12")
  private String birthDate;

  private Integer age;

  @NotNull
  @Size(max = 10)
  private String gender;

  @Valid
  @NotNull
  private AddressDTO parmanentAddress;

  @Valid
  @NotNull
  private AddressDTO temporaryAddress;

  @Pattern(regexp = "(^$|[0-9]{10})")
  private String mobile;

  @Pattern(regexp = "(^$|[0-9]{10})")
  private String altMobileNumber;

  @Email
  @Size(max = 100)
  private String email;

  @NotNull
  @Size(max = 255)
  private String wmanEligibility;

  @Valid
  @NotNull
  private List<ExperienceDTO> experiences;

  @NotNull
  @Size(max = 50)
  private String preferredExamCentre;

  private String totalExperience;

  @NotNull
  @Size(max = 25)
  private String preferredLangMode;


  public List<AttachmentDTO> attachments;

  @Override
  public String getApplicantName() {
    return String.format("%s %s", nst.app.common.HelperUtil.fromString(surname),
        nst.app.common.HelperUtil.fromString(firstName)).trim();
  }
}