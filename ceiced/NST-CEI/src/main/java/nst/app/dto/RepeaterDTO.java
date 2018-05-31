package nst.app.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseModelDTO;
import nst.dto.AttachmentDTO;
import nst.kernal.validation.MaxDate;
import nst.kernal.validation.PastDate;

@Data
@NoArgsConstructor
public class RepeaterDTO extends BaseModelDTO {


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

  @PastDate
  private String birthDate;

  private Integer age;

  @Size(max = 50)
  private String examType;

  @Size(max = 25)
  @Pattern(regexp="^(0|[1-9][0-9]*)$")
  private String prevRollNo;

  @Size(max = 50)
  private String prevCentre;

  @MaxDate
  private String prevExamDateMonthYear;

  @Size(max = 50)
  private String preferredExamCentre;

  @Size(max = 50)
  private String preferredLangMode;
  public List<AttachmentDTO> attachments;

  @Override
  public String getApplicantName() {
    return String.format("%s %s", nst.app.common.HelperUtil.fromString(surname), nst.app.common.HelperUtil.fromString(firstName)).trim();
  }

}