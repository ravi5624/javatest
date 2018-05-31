package nst.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO {

  Long id;
  String userId;
}
