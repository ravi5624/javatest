package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import lombok.Data;
import nst.app.dto.newgen.le.NGLESafetyGadgetDTO;
import nst.app.dto.newgen.le.NGLETestingInstrumentDTO;
import nst.app.dto.newgen.le.NGStaffEmployeeDTO;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGIAndTAgencyLicenseDTO extends NGBaseDTO {

    /* Part 1 start*/

    String liftEscalatorType;

    String agencyName;

    String agencyLegalStatus;

    Integer establishmentYear;

    List<NGAgencyLegalStatusDetailsDTO> agencyLegalStatusDetails = new ArrayList<>();

    @Valid
    private NGBusinessAddressDTO businessAddress;


    /*String businessEmail;

    String businessContactNo;

    String businessWebsite;*/

    @Valid
    private List<NGBranchAddressDTO> branchAddress;

    /*String branchEmail;

    String branchContactNo;

    String branchWebsite;*/

    String contactEmail;

    String contactNo;


    String isAuthorizationCertificateIssued;

    String authorizationNo;

    String authorizationCertificateDate;

    String registrationNumberElectricalContractor;

    String bankName;

    String branchName;

    Date issueDate;

    Double amount;

    String bankAddress;

    /* Part 1 end*/

    /* Part 2 start*/


    String communicationFacilitiesDetails;

    List<NGLETestingInstrumentDTO> testingInstruments;

    List<NGLESafetyGadgetDTO> safetyGadgets;

    List<NGStaffEmployeeDTO> staffEmployees;

    String remarks;

    /* Part 2 end*/

    private List<NGAttachmentDTO> attachments;


}