package nst.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseDTO;
import nst.common.error.AppException;
import nst.config.MyLogger;
import nst.kernal.SystemConstants.Rest;
import nst.util.JSONUtil;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS)
public class APIResponseDTO extends BaseDTO {

  private Long time;
  private boolean error;
  private Integer code;
  private String errorCode;
  private String errorMessage;
  private String response;

  public <T> T toDTO(Class<T> aClass) {
    try {
      return JSONUtil.fromJSON(response.replace("\n", ""), aClass);
    } catch (AppException e) {
      MyLogger.logError(e);
      throw AppException.createWithMessageCode(Rest.NEWGEN_SUBMIT_ERROR, Rest.NEWGEN_SUBMIT_ERROR);
    }
  }
}
