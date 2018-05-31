package nst.app.dto.newgen.le;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.app.dto.newgen.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGEAndMAgencyLicenseDTO extends NGBaseDTO { /* Part 1 start*/

    String liftEscalatorType;

    String agencyName;

    String agencyLegalStatus;

    Integer establishmentYear;

    List<NGAgencyLegalStatusDetailsDTO> agencyLegalStatusDetails = new ArrayList<>();

    @Valid
    private NGBusinessAddressDTO businessAddress;


    /*String businessEmail;

    String businessContactNo;

    String businessWebsite;
*/
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

    String manufacturingUnitDetails;

    String premiseName;

    String surveyNo;

    private NGAddressDTO workshopAddress;

    Boolean isRegistrationGranted;

    String occupacyRights;

    String electricityBillNo;

    String communicationFacilitiesDetails;

    List<NGLETestingInstrumentDTO> testingInstruments;

    List<NGLESafetyGadgetDTO> safetyGadgets;

    String vehiclePossession;

    String remarks;

    /* Part 2 end*/

    public List<NGAttachmentDTO> attachments;
    private List<NGStaffEmployeeDTO> staffEmployees;


}