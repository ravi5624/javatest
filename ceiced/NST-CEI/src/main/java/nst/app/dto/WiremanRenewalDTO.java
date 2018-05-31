package nst.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import nst.app.CEIUtil;
import nst.common.ValidationUtil;
import nst.common.base.BaseModelDTO;
import nst.kernal.validation.PastDate;
import nst.util.JSONUtil;

@Data
@NoArgsConstructor
public class WiremanRenewalDTO extends BaseModelDTO {

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


  @NotNull
  @Pattern(regexp = CEIUtil.WIREMAN_LICENSE, message = "Invalid LicenseNo")
  @Size(max = 100)
  private String permitNo;

  @NotNull
  @PastDate
  private String issueDate;

  @NotNull
  private String fromDate;

  @NotNull
  private String toDate;

  @Override
  public String getApplicantName() {
    return String.format("%s %s", nst.app.common.HelperUtil.fromString(surname), nst.app.common.HelperUtil.fromString(firstName)).trim();
  }

  public String getApplicantFullName() {
    return String.format("%s %s %s", nst.app.common.HelperUtil.fromString(surname), nst.app.common.HelperUtil.fromString(firstName), nst.app.common.HelperUtil.fromString(middleName)
    ).trim();
  }

  public static void main(String[] args) {
    WiremanRenewalDTO portalUserDTO = new WiremanRenewalDTO();
    portalUserDTO.setPermitNo("G/AHD/AZ-0001/1/2017");
    System.out.println(JSONUtil.toJSON(ValidationUtil.validateFor(portalUserDTO, Pattern.class)));
  }

}