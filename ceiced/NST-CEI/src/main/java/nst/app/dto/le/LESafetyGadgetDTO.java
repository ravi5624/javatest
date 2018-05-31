package nst.app.dto.le;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.common.base.BaseDTO;

import javax.validation.constraints.Size;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class LESafetyGadgetDTO extends BaseDTO {

    private Long id;
    private String name;
    @Size(max=25)
    private String make;

    @Size(max=5)
    private String number;

    @Size(max=100)
    private String remarks;

    private Boolean isNew;

}
