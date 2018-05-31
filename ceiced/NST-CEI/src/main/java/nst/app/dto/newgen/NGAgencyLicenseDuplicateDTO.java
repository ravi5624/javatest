package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;
import nst.dto.AttachmentDTO;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGAgencyLicenseDuplicateDTO extends NGBaseDTO {

  private String agencyType;
  private String agencyAuthNo;
  private String issueDate;
  private String expiryDate;
  private String reason;
  private String reasonIfOther;
  public List<NGAttachmentDTO> attachments;

}