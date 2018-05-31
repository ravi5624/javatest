package nst.app.dto.newgen.le;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.common.base.BaseDTO;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGLESafetyGadgetDTO extends BaseDTO {
    private Long id;
    private String name;
    private String make;
    private String number;
    private String remarks;
    private Boolean isEditable;
}
