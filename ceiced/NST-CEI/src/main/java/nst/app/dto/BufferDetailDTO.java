package nst.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.app.enums.AddressType;
import nst.app.enums.BufferType;
import nst.common.base.BaseDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * contains all the fields for BufferDetail DTO
 *
 */


@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class BufferDetailDTO extends BaseDTO {

  private Long id;
  @NotNull
  @Size(max = 50)
  private String bufferType;
  private String type;

  @Size(max = 50)
  private String  otherType;
  private Integer numberOfBuffer;
  private Integer stroke;

  @JsonIgnore
  public BufferType getBufferTypeEnum() {
    return BufferType.valueOf(bufferType);
  }


}