package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.app.enums.DesignationType;
import nst.common.base.BaseDTO;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGFormIEmployerDTO extends BaseDTO {

  private Long id;
  private String name;
  private String permitNo;
  private DesignationType designation;
  private String joiningDate;
  private String leavingDate;
  private String otherAttachmentName;
  private Boolean isInactive;
//  private List<NGAttachmentDTO> attachments;
}