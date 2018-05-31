package nst.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseModelDTO;
import nst.dto.AttachmentDTO;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class SupervisorModificationDTO extends BaseModelDTO {

  @NotNull
  @Size(max = 100)
  @Pattern(regexp="/^[ A-Za-z0-9_/,]*$/")
  private String permitNo;

  private String isNameCorrection;

  @Pattern(regexp="[a-zA-Z]")
  @Size(max = 25)
  private String surname;

  @Pattern(regexp="[a-zA-Z]")
  @Size(max = 25)
  private String firstName;

  @Pattern(regexp="[a-zA-Z]")
  @Size(max = 25)
  private String middleName;

  private String isDOBCorrection;

  private String birthDate;

  public List<AttachmentDTO> attachments;

  @Override
  public String getApplicantName() {
    return String.format("%s %s", nst.app.common.HelperUtil.fromString(surname), nst.app.common.HelperUtil.fromString(firstName)).trim();
  }
}