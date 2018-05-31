package nst.common.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.common.DTO;
import nst.util.AllUtil;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public abstract class BaseDTO implements DTO {

  private static final long serialVersionUID = 1L;

  public String toJSON() {
    return AllUtil.toJSON(this);
  }
}
