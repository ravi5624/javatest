package nst.app.dto.newgen;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NGContractorRenewalDTO extends NGBaseDTO {

  private String surname;
  private String firstName;
  private String middleName;
  private String contractorName;
  private String contractorLicNo;
  private String issueDate;
  private String licenseExpiryDate;
  private String oldSupervisorName;
  private String oldSupervisorBirthDate;
  private String oldSupervisorJoineeDate;
  private String oldSupervisorLeavingDate;
  private Boolean isWorkingOldSupervisor;
  private String newSupervisorName;
  private String newSupervisorBirthDate;
  private String newSupervisorJoineeDate;
  private Boolean isAddressChange;
  private NGAddressDTO parmanentAddress;
  @Valid
  @NotNull
  public List<NGAttachmentDTO> attachments;



}