package nst.app.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseModelDTO;
import nst.dto.AttachmentDTO;
import nst.kernal.validation.PastDate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class FormIDTO extends BaseModelDTO {

  @NotNull
  @Pattern(regexp = "[a-zA-Z]")
  @Size(max = 100)
  private String technicalContractorName;

  @NotNull
  @Size(max = 100)
  private String contractorLicNo;

  @Valid
  @NotNull
  private AddressDTO officeAddress;

  @PastDate
  private String issueDate;

  private List<FormIEmployerDTO> employer;

  private List<AttachmentDTO> attachments;
  //Contractor License No.
  //Contractor Office Address (6 fields as you know)


  @Override
  public String getApplicantName() {
    return String.format("%s", nst.app.common.HelperUtil.fromString(technicalContractorName)).trim();
  }
}