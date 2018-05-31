package nst.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;
import nst.app.enums.DesignationType;
import nst.common.base.BaseDTO;
import nst.dto.AttachmentDTO;
import nst.kernal.validation.PastDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


@Data
@NoArgsConstructor
@JsonInclude(Include.ALWAYS)
public class FormIEmployerDTO extends BaseDTO {

  private Long id;
  @NotNull
  @Pattern(regexp="[a-zA-Z]")
  @Size(max = 25)
  private String name;

  @NotNull
  @Pattern(regexp="/^[ A-Za-z0-9_/,]*$/")
  @Size(max = 100)
  private String permitNo;


  @NotNull
  @Pattern(regexp="/^[ A-Za-z0-9_/,]*$/")
  @Size(max = 50)
  private DesignationType designation;

  @PastDate
  private String joiningDate;
  @PastDate
  private String leavingDate;

  private String isInactive;

  private String isNew;

  private String otherAttachmentName;

//  public List<AttachmentDTO> attachments;
}