package nst.app.dto;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import nst.app.CEIUtil;
import nst.common.ValidationUtil;
import nst.common.base.BaseModelDTO;
import nst.dto.AttachmentDTO;
import nst.kernal.validation.PastDate;
import nst.util.JSONUtil;
import org.hibernate.validator.constraints.Email;

@Data
@NoArgsConstructor
public class SupervisorExaminationDTO extends BaseModelDTO {


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
  private String middlename;
  @PastDate
  private String birthDate;

  private Integer age;

  @NotNull
  @Size(max = 10)
  private String gender;

  @Size(max = 50)
  private String examType;

  @Valid
  @NotNull
  AddressDTO parmanentAddress;

  @Valid
  AddressDTO temporaryAddress;

  @Pattern(regexp="(^$|[0-9]{10})")
  private String mobile;

  @Pattern(regexp="(^$|[0-9]{10})")
  private String altMobileNumber;

  @Size(max = 100)
  @Email
  private String email;


  @NotNull
  @Pattern(regexp = CEIUtil.SUPERVISOR_LICENSE, message = "Invalid LicenseNo")
  @Size(max = 50)
  private String permitNo;

  @PastDate
  private String permitIssueDate;

  private String supExperience;

  private List<ExperienceDTO> experiences;

  @Size(max = 50)
  private String preferredExamCentre;

  private String totalExperience;

  private String whetherWiremanPermit;

  @Size(max = 50)
  private String preferredLangMode;

  public List<AttachmentDTO> attachments;

  @Override
  public String getApplicantName() {
    return String.format("%s %s", nst.app.common.HelperUtil.fromString(surname), nst.app.common.HelperUtil.fromString(
        firstName)).trim();
  }

  public static void main(String[] args) {
    SupervisorExaminationDTO portalUserDTO = new SupervisorExaminationDTO();
    portalUserDTO.setPermitNo("G/AHD/SG/0001/01/2017");
    System.out.println(JSONUtil.toJSON(ValidationUtil.validateFor(portalUserDTO, Pattern.class)));
  }
}