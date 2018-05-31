package nst.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseModelDTO;
import nst.dto.AttachmentDTO;
import nst.kernal.validation.CombiDate;

import javax.validation.constraints.Size;
import java.util.List;


/**
 * contains fields to get Duplicate License of Agency
 */


@Data
@CombiDate(
        startField = "IssueDate",
        endField = "ExpiryDate",
        message = "Licence dates are invalid.")
@NoArgsConstructor
public class AgencyLicenseDuplicateDTO extends BaseModelDTO {

  private String agencyType;

  @Size(max = 50)
  private String agencyAuthNo;

  private String issueDate;

  private String expiryDate;

  private String reason;

  private String reasonIfOther;

  public List<AttachmentDTO> attachments;


}