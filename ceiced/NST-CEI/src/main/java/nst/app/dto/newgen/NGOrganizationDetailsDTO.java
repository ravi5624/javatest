package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.common.base.BaseDTO;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGOrganizationDetailsDTO extends BaseDTO {
    private Long id;
    private String nameOfPartner;
    private String birthDate;
    private String homeAddress;
}
