package nst.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import nst.app.model.validation.FormPart1;
import nst.common.ValidationUtil;
import nst.common.base.BaseModelDTO;
import nst.common.base.ResponseError;
import nst.common.error.DataValidationException;
import nst.dto.AttachmentDTO;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class WiremanModificationDTO extends BaseModelDTO {

  @NotNull
  @Size(max = 100)
  @Pattern(regexp="/^[ A-Za-z0-9_/,]*$/")
  private String permitNo;

  @NotNull(groups = FormPart1.class)
  private String isNameCorrection;

  @NotNull(groups = FormPart1.class)
  @Pattern(regexp="[a-zA-Z]")
  @Size(max = 25)
  private String surname;

  @NotNull(groups = FormPart1.class)
  @Size(max = 25)
  @Pattern(regexp="[a-zA-Z]")
  private String firstName;

  @NotNull(groups = FormPart1.class)
  @Size(max = 25)
  @Pattern(regexp="[a-zA-Z]")
  private String middleName;

  private String birthDate;

  public List<AttachmentDTO> attachments;

  @NotNull
  private String isDOBCorrection;

  @Override
  public String getApplicantName() {
    return String.format("%s %s", nst.app.common.HelperUtil.fromString(surname), nst.app.common.HelperUtil.fromString(firstName)).trim();
  }

    @JsonIgnore
    @Override
    public void validateAll() {
        List<ResponseError> errors = new ArrayList<>();
      if (this.isNameCorrection.equalsIgnoreCase("Yes")){
           errors = ValidationUtil.validateGroup(this, FormPart1.class, getBasicValidations());
          errors.addAll(ValidationUtil.validateGroup(this, FormPart1.class,
                  getRequiredValidations()));

      }else if(this.isNameCorrection.equalsIgnoreCase("No")){
           errors = ValidationUtil.validateFor(this,
                  getBasicValidations());
      }

      if(errors.size()>0) {
            throw DataValidationException.create(errors);
      }
    }
}