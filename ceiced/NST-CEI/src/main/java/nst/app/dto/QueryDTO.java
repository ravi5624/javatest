package nst.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.common.base.BaseDTO;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class QueryDTO extends BaseDTO {
  private Long id;
  private Long queryId;
  private String raiseOn;
  private String raiseBy;
  private String raise;
  private String reply;
}
