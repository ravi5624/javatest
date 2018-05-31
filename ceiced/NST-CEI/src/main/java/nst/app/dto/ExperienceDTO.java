package nst.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import nst.common.ValidationUtil;
import nst.common.base.BaseDTO;
import nst.common.base.ResponseError;
import nst.kernal.validation.CombiDate;

@CombiDate(
    startField = "FromDate",
    endField = "ToDate",
    message = "Licence dates are invalid.")
@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ExperienceDTO extends BaseDTO {

  private Long id;

  /*USed for SupervisorName*/
  private String exam;

  @NotNull
  private String fromDate;

  @NotNull
  private String toDate;

  @NotNull
  @Pattern(regexp = "[a-zA-Z]*")
  @Size(max = 30)
  private String wmanContractorLicenceNo;

  @NotNull
  @Pattern(regexp = "[a-zA-Z]*")
  @Size(max = 30)
  private String wmanOrgSupFirmName;

  @NotNull
  @Pattern(regexp = "[a-zA-Z]*")
  @Size(max = 100)
  private String wmanSupSupervisorPermitNo;

  private String isCurrent;

}