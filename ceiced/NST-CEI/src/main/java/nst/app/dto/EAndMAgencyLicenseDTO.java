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
public class EAndMAgencyLicenseDTO extends BaseModelDTO {

  /* Part 1 start*/

  private List<StaffEmployeeDTO> staffEmployees;
  @NotNull
  String liftEscalatorType;

  @NotNull
  @Size(max = 100)
  String agencyName;

  @NotNull
  String agencyLegalStatus;

  Integer establishmentYear;

  List<AgencyLegalStatusDetailsDTO> agencyLegalStatusDetails = new ArrayList<>();

  @Valid
  private BusinessAddressDTO businessAddress;

   @Valid
   private List<BranchAddressDTO> branchAddress;

  @Email
  @Size(max = 100)
  String contactEmail;

  @Size(min = 6, max = 10)
  String contactNo;

  @NotNull
  String isAuthorizationCertificateIssued;

  @NotNull
  String authorizationNo;

  @NotNull
  String authorizationCertificateDate;

  @Size(max = 100)
  String registrationNumberElectricalContractor;

  @Size(max = 100)
  String bankName;

  @Size(max = 100)
  String branchName;

  @PastDate
  String issueDate;

  Double amount;

  String bankAddress;

  /* Part 1 end*/


  /* Part 2 start*/

  String manufacturingUnitDetails;

   @Valid
   private LEAddressDTO workshopAddress;

  String isRegistrationGranted;

  String occupacyRights;

  @Size(max = 50)
  String electricityBillNo;

  @Size(max = 100)
  String communicationFacilitiesDetails;

  @Valid
  List<LETestingInstrumentDTO> testingInstruments;

  @Valid
  List<LESafetyGadgetDTO> safetyGadgets;

  String vehiclePossession;

  String remarks;

  /* Part 2 end*/

  private List<AttachmentDTO> attachments;

  @Override
  public String getApplicantName() {
    return String.format("%s", nst.app.common.HelperUtil.fromString(agencyName)).trim();
  }
}