package nst.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import nst.app.enums.UserType;
import nst.app.model.validation.FormPart1;
import nst.app.model.validation.FormPart2;
import nst.common.ValidationUtil;
import nst.common.base.BaseModelDTO;
import nst.common.base.ResponseError;
import nst.common.error.DataValidationException;
import nst.common.security.LoginUser;
import nst.dto.AttachmentDTO;
import nst.util.LoginUserUtil;
import org.hibernate.validator.constraints.Email;

@Data
@NoArgsConstructor
public class EscalatorInstallationDTO extends BaseModelDTO {

  //===== Part 1 ============================

  private String applicationFor;

  @NotNull
  private Long agencyId;

  private String agencyName;

  private String escalatorLicenseNumber;


  @JsonProperty("applicantName")
  private String eiApplicantName;

  @Email(groups = FormPart1.class)
  private String applicantEmail;

  @NotNull(groups = FormPart1.class)
  @Pattern(regexp = "(^$|[0-9]{10})", groups = FormPart1.class)
  private String applicantMobile;

  @Valid
  @NotNull(groups = FormPart1.class)
  public AddressDTO applicantAddress;

  @NotNull(groups = FormPart1.class)
  private String isLocalAgentAppointed;

  private String localAgentName;

  private String localAgentEmail;

  private String localAgentContactNo;

  public AddressDTO localAgentAddress;

  private String escalatorSiteName;

  @Valid
  @NotNull(groups = FormPart1.class)
  private LEAddressDTO liftSiteAddress;

  @NotNull(groups = FormPart1.class)
  private String personName;

  @NotNull(groups = FormPart1.class)
  @Email(groups = FormPart1.class)
  private String personEmail;

  @NotNull(groups = FormPart1.class)
  @Pattern(regexp = "(^$|[0-9]{10})", groups = FormPart1.class)
  private String personMobile;

  @Valid
  @NotNull(groups = FormPart1.class)
  public AddressDTO personAddress;

  @NotNull(groups = FormPart1.class)
  private String makerName;

  @NotNull(groups = FormPart1.class)
  @Email(groups = FormPart1.class)
  private String makerEmail;

  @NotNull(groups = FormPart1.class)
  @Pattern(regexp = "(^$|[0-9]{10})", groups = FormPart1.class)
  private String makerMobile;

  @Valid
  @NotNull(groups = FormPart1.class)
  public AddressDTO makerAddress;

  @NotNull(groups = FormPart1.class)
  private String escalatorIdentification;

  @NotNull(groups = FormPart1.class)
  private Integer fromFloor;

  @NotNull(groups = FormPart1.class)
  private Integer toFloor;

//===== Part 1 ============================
// ===== Part 2 ============================

  //  @NotNull
//  @Max(value = 38)
  private Double ratedSpeed;

  private Double balusmadesWidth;

  private Double horizontalDistance;

  private Double ratedLoad;

  private Integer escalatorPersonCapacity;

  //  @NotNull
//  @Size(max = 35)
  private Double escalatorAngle;

  private Double escalatorWidth;

  private Double verticalRise;

  //  @NotNull
//  @Size(max = 6000)
  private String description;

  //  @NotNull
//  @Size(max = 6000)
  private String constructionDetails;

  @NotNull(groups = FormPart2.class)
  private String commencementWork;

  @NotNull(groups = FormPart2.class)
  private String completionWork;

  public List<AttachmentDTO> attachments;

  @Override
  public String getApplicantName() {
    return String.format("%s", nst.app.common.HelperUtil.fromString(eiApplicantName)).trim();
  }


  @JsonIgnore
  @Override
  public void validateBasic() {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    /*When Agency create a Template*/
      List<ResponseError> errors=validateBasic(loginUser);
      if(errors.size()> 0) {
          throw DataValidationException.create(errors);
      }
  }
   private List<ResponseError>  validateBasic(LoginUser loginUser) {
        List<ResponseError> errors=new ArrayList<>();
        if (loginUser.hasAuthority(UserType.EM_AGENCY.getType())
            && (getAgencyId() != null && loginUser.getUserId().equals(getAgencyId()))) {
            errors= ValidationUtil.validateGroup(this, FormPart1.class, getBasicValidations());
            errors.addAll(ValidationUtil.validateGroup(this, FormPart2.class, getBasicValidations()));
          return errors;
        }

        if (loginUser.hasAuthority(UserType.OWNER.getType())) {
            errors=ValidationUtil.validateGroup(this, FormPart1.class, getBasicValidations());
        } else if (loginUser.hasAuthority(UserType.EM_AGENCY.getType())) {
          errors=ValidationUtil.validateGroup(this, FormPart2.class, getBasicValidations());
        }
        return errors;
    }
  @Override
  public void validateAll() {
      LoginUser loginUser = LoginUserUtil.loadLoginUser();
      List<ResponseError> errors=validateBasic(loginUser);
      if (loginUser.hasAuthority(UserType.OWNER.getType()) || getId().equals(loginUser.getUserId())) {
          errors.addAll(ValidationUtil.validateGroup(this, FormPart1.class, getRequiredValidations()));
    } else {
          errors.addAll(ValidationUtil.validateGroup(this, FormPart2.class, getRequiredValidations()));
    }

    if(errors.size()> 0) {
            throw DataValidationException.create(errors);
    }
  }
}