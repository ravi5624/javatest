package nst.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.common.base.BaseDTO;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class SafetyGadgetDTO extends BaseDTO {

  private Long id;
  private String name;
  private String make;
  private String number;
  private String remarks;

}
