package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import nst.common.base.BaseDTO;

@Data
@JsonInclude(Include.ALWAYS)
public class NGLiftEscalatorAddressDTO extends NGAddressDTO {

  private String country;
}