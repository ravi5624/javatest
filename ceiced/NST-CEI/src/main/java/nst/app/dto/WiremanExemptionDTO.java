package nst.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.ValidationUtil;
import nst.common.base.BaseModelDTO;
import nst.common.base.ResponseError;
import nst.dto.AttachmentDTO;
import nst.kernal.SystemConstants;
import nst.kernal.validation.PastDate;
import nst.util.AllUtil;
import nst.util.JSONUtil;
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
public class WiremanExemptionDTO extends BaseModelDTO {

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
    @PastDate("18")
    @Size(max = 30)
    private String birthDate;

    @NotNull
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

    @NotNull
    @Size(max = 30)
    private String mobile;


    @Size(max = 30)
    private String altMobileNumber;

    @NotNull
    @Size(max = 100)
    @Email
    private String email;

    @NotNull
    @Size(max = 255)
    private String technicalQualification;

    //@Pattern(regexp="[^[0-9]{1,4}$]")
    private Integer passYear;

    @NotNull
    @Size(max = 30)
    private String qualificationState;

    private String totalExperience;

    public List<AttachmentDTO> attachments;

    public Boolean noPermit;

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

  public static void main(String[] args) {
    WiremanExemptionDTO portalUserDTO = new WiremanExemptionDTO();
    portalUserDTO.setFirstName("Fdsf");
    portalUserDTO.setBirthDate("2012-12-12T00:00:00");

//    System.out.println(JSONUtil.toJSON(ValidationUtil.validateFor(portalUserDTO, Size.class)));
//    System.out.println(JSONUtil.toJSON(ValidationUtil.validateFor(portalUserDTO, NotNull.class)));
    System.out.println(JSONUtil.toJSON(ValidationUtil.validateFor(portalUserDTO, PastDate.class)));
  }
}