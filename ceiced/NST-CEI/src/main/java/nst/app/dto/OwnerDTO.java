package nst.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseModelDTO;

@Data
@NoArgsConstructor
public class OwnerDTO extends BaseModelDTO {

  @NotNull
  @Size(max = 100)
  private String ownerName;

  private Long userId;
}