package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;
import nst.app.dto.newgen.le.NGLESafetyGadgetDTO;
import nst.app.dto.newgen.le.NGLETestingInstrumentDTO;
import nst.app.dto.newgen.le.NGStaffEmployeeDTO;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGAgencyLicenseModificationDTO extends NGBaseDTO {

  private List<NGStaffEmployeeDTO> staffEmployees;
  private NGAddressDTO branchAddress;
  private NGAddressDTO mainOfficeAddress;
  private NGLIAddressDTO workshopAddress;
  private String branchEmail;
  private String branchContactNo;
  private String mainOfficeEmail;
  private String mainOfficeContactNo;
  private List<NGLETestingInstrumentDTO> testingInstruments;
  private List<NGLESafetyGadgetDTO> safetyGadgets;
  private String communicationFacilityChangeRequestDetails;
  private String vehiclePossessionDetail;
  private String legalStatusChangeRequest;
  private List<NGAttachmentDTO> attachments;

}