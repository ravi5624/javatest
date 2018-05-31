package nst.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nst.app.dto.le.AgencyLegalStatusDetailsDTO;
import nst.app.dto.le.LESafetyGadgetDTO;
import nst.app.dto.le.LETestingInstrumentDTO;
import nst.common.base.BaseModelDTO;
import nst.dto.AttachmentDTO;
import nst.kernal.validation.PastDate;
import org.hibernate.validator.constraints.Email;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OAndMAgencyLicenseDTO extends BaseModelDTO {

  /* Part 1 start*/

  @NotNull
  String liftEscalatorType;

  @NotNull
  @Size(max =100)
  String agencyName;

  @NotNull
  String agencyLegalStatus;

  Integer establishmentYear;

  List<AgencyLegalStatusDetailsDTO> agencyLegalStatusDetails = new ArrayList<>();

  @Valid
  private BusinessAddressDTO businessAddress;

  /*@NotNull
  @Email
  @Size(max =100)
  String businessEmail;

  @Size(min=6,max =10)
  String businessContactNo;

  @NotNull
  @Size(max = 100)
  String businessWebsite;*/

  @Valid
  private List<BranchAddressDTO> branchAddress;

  /*@Email
  @Size(max =100)
  String branchEmail;

  @Size(min=6,max =10)
  String branchContactNo;

  @Size(max =100)
  String branchWebsite;*/

  @Email
  @Size(max =100)
  String contactEmail;

  @Size(min=6,max =10)
  String contactNo;

  @NotNull
  String isAuthorizationCertificateIssued;

  @NotNull
  String authorizationNo;

  @NotNull
  String authorizationCertificateDate;

  @Size(max =100)
  String registrationNumberElectricalContractor;

  @Size(max =100)
  String bankName;

  @Size(max =100)
  String branchName;

    @PastDate
    String issueDate;

  Double amount;

  String bankAddress;

  private List<StaffEmployeeDTO> staffEmployees;

    /* Part 1 end*/


  /* Part 2 start*/

  @Valid
  List<LETestingInstrumentDTO> testingInstruments;

  @Valid
  List<LESafetyGadgetDTO> safetyGadgets;

  @Size(max = 25)
  String vehiclePossession;

  String remarks;

  /* Part 2 end*/

  private List<AttachmentDTO> attachments;

  @Override
  public String getApplicantName() {
    return String.format("%s", nst.app.common.HelperUtil.fromString(agencyName)).trim();
  }
}