package nst.app.dto.newgen.le;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.common.base.BaseDTO;


@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGLETestingInstrumentDTO extends BaseDTO {

    private Long id;

    private String machineName;

    private String make;

    private String number;

    private String serialNumber;

    private String remarks;

}
