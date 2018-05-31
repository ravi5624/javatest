package nst.app.dto;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import nst.app.dto.le.LESafetyGadgetDTO;
import nst.app.dto.le.LETestingInstrumentDTO;
import nst.common.base.BaseModelDTO;
import nst.dto.AttachmentDTO;
import org.hibernate.validator.constraints.Email;

/**
 * contains all the fields for Modification of Agency License
 *
 */

@Data
@NoArgsConstructor
public class AgencyLicenseModificationDTO extends BaseModelDTO {

  private List<StaffEmployeeDTO> staffEmployees;

  @Valid
  private AddressDTO branchAddress;

  @Valid
  private AddressDTO officeAddress;

  private LEAddressDTO workshopAddress;


  @Email
  private String branchEmail;

  private String branchContactNo;
  @Email
  private String mainOfficeEmail;

  private String mainOfficeContactNo;

  private List<LETestingInstrumentDTO> testingInstruments;

  private List<LESafetyGadgetDTO> safetyGadgets;

  private String communicationFacilityChangeRequest;

  private String vehiclePossessionDetail;

  private String agencyLegalStatus;

  private List<AttachmentDTO> attachments;

  private Boolean isChangeBranchOffice;
  private Boolean isChangeCommunicationDetails;
  private Boolean isChangeLegalStatus;
  private Boolean isChangeMainOffice;
  private Boolean isChangeSaftyguards;
  private Boolean isChangeVehicleDetails;
  private Boolean isChangeWorkshopOffice;
  private Boolean isChangetestInstrument;
  private Boolean isStaffDetailsChange;

}