package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGContractorModificationDTO extends NGBaseDTO {

  private Boolean isNameCorrection;
  private String contractorName;
  private Boolean isAddressChange;
  private String contractorLicenseNo;
  private String licenseNoIssueDate;
  public List<NGAttachmentDTO> attachments;
  private NGAddressDTO officeAddress;
}