package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import nst.common.base.BaseDTO;

@Data
@JsonInclude(Include.ALWAYS)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class NGBaseDTO extends BaseDTO {

  private Long id;
  private String applicationId;
  private String fileNumber;
  private String applicationName;
  private String type;
  private String status;
  private Boolean iagree;
  private String createdBy;
  public NGBaseDTO() {
  }
}
