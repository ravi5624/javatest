package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.common.base.BaseDTO;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGExperienceDTO extends BaseDTO {
  private Long id;
  private String firmName;
  private String supervisorName;
  private String fromDate;
  private String toDate;
  private String contractorLicenceNo;
  private String supervisorPermitNo;
  private Boolean isCurrent;
}
