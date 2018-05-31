package nst.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseModelDTO;

@Data
@NoArgsConstructor
public class EAndMAgencyLicenseDuplicateDTO extends BaseModelDTO {

  @NotNull
  @Size(max = 30)
  private static String ownerName;

  private Long userId;
}