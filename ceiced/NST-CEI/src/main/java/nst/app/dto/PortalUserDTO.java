package nst.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseModelDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class PortalUserDTO extends BaseModelDTO {

  @NotNull
  @Size(max = 25)
  private String userName;

  @NotNull
  @Size(max = 25)
  private String firstName;

  @NotNull
  @Size(max = 25)
  private String lastName;

  private String contactNo;

  private String dob;

  @NotNull
  @Size(max = 100)
  private String email;

  private List<ApplicationTypeDTO> operations;

  @NotNull
  @Size(max = 30, message = "ttt.ddd.xxx")
  private String password;
}