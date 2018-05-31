package nst.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.common.base.BaseDTO;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ApplicationDetailsDTO extends BaseDTO {

    long appId;
    String apiName;
    String url;
}
