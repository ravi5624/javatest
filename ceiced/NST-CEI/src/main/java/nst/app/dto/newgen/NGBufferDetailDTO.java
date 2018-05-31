package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.common.base.BaseDTO;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGBufferDetailDTO extends BaseDTO {

    private Long id;
    private String type;
    private String bufferType;
    private Integer numberOfBuffer;
    private Integer stroke;
    private String otherType;
}