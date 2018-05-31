package nst.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseModelDTO;
import nst.common.base.ResponseError;
import nst.dto.AttachmentDTO;
import nst.kernal.SystemConstants;
import nst.kernal.validation.PastDate;
import nst.util.AllUtil;
import org.hibernate.validator.constraints.Email;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class InterStatePermitDTO extends BaseModelDTO {


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

  @Size(max = 10)
  private String gender;

  @Pattern(regexp="(^$|[0-9]{10})")
  private String mobile;
  @Pattern(regexp="(^$|[0-9]{10})")
  private String altMobile;

  @Email
  @Size(max = 100)
  private String email;

  @Size(max = 25)
  private String candidateType;
  @NotNull
  @Pattern(regexp="/^[ A-Za-z0-9_/,]*$/")
  @Size(max = 100)
  private String permitNo;

  private Integer passYear;
  @PastDate
  private String permitIssueDate;

  @Size(max = 100)
  private String presentOrgName;

  @Size(max = 100)
  private String presentOrgAddress;

  private String nameAndAddressAuth;

  @Valid
  @NotNull
  private AddressDTO parmanentAddress;

  @Valid
  @NotNull
  private AddressDTO temporaryAddress;

  public List<AttachmentDTO> attachments;

    @Override
    protected List<ResponseError> otherValidateBasic() {
        List<ResponseError> errors = new ArrayList<>();
        if (this.passYear != null && !StringUtils.isEmpty(this.birthDate)) {
            Integer year = AllUtil.getYear(getBirthDate(), SystemConstants.FORMAT_DATE_COMMA);
            if (this.passYear < (year + SystemConstants.PASSING_MIN_YEAR)) {
                errors.add(ResponseError.create(SystemConstants.Rest.ERROR_BAD_REQUEST, "passYear", SystemConstants.Rest.PASS_YEAR_ERROR));
            }
        }
        return errors;
    }

  @Override
  public String getApplicantName() {
    return String.format("%s %s", nst.app.common.HelperUtil.fromString(surname), nst.app.common.HelperUtil.fromString(firstName)).trim();
  }
}