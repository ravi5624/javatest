package nst.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseModelDTO;
import nst.dto.AttachmentDTO;
import nst.kernal.validation.CombiDate;
import nst.kernal.validation.PastDate;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@CombiDate(
        startField = "IssueDate",
        endField = "ExpiryDate",
        message = "Licence dates are invalid.")
@NoArgsConstructor
public class LiftEscalatorDuplicateDTO extends BaseModelDTO {


  @NotNull
  String liftEscalatorType;

  @NotNull
  String licenseNumber;

  @NotNull
  @PastDate
  String issueDate;

  @NotNull
  String expiryDate;

  String reason;

  String reasonIfOther;

  public List<AttachmentDTO> attachments;

}