package nst.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import nst.common.base.BaseDTO;

@Data
@JsonInclude(Include.ALWAYS)
public class TestingInstrumentDTO extends BaseDTO {

  private Long id;

  private String machineName;

  private String make;

  private String number;

  private String serialNumber;

  private String remarks;

}