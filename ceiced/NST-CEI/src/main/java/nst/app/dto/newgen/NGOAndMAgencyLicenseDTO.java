package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.app.dto.newgen.le.NGLESafetyGadgetDTO;
import nst.app.dto.newgen.le.NGLETestingInstrumentDTO;
import nst.app.dto.newgen.le.NGStaffEmployeeDTO;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGOAndMAgencyLicenseDTO extends NGBaseDTO {

    /* Part 1 start*/

  String liftEscalatorType;


  String agencyName;

  String agencyLegalStatus;

  Integer establishmentYear;

  List<NGAgencyLegalStatusDetailsDTO> agencyLegalStatusDetails = new ArrayList<>();

  private NGBusinessAddressDTO businessAddress;


  String businessEmail;

  String businessContactNo;


  String businessWebsite;

  private List<NGBranchAddressDTO> branchAddress;


  String branchEmail;

  String branchContactNo;

  String branchWebsite;

  String contactEmail;

  String contactNo;

  String isAuthorizationCertificateIssued;

  String authorizationNo;

  String authorizationCertificateDate;

  String registrationNumberElectricalContractor;

  String bankName;

  String branchName;

  String issueDate;

  Double amount;

  String bankAddress;

    /* Part 1 end*/


    /* Part 2 start*/


  List<NGLETestingInstrumentDTO> testingInstruments;


  List<NGLESafetyGadgetDTO> safetyGadgets;


  String vehiclePossession;

  String remarks;

    /* Part 2 end*/

  private List<NGAttachmentDTO> attachments;
  private List<NGStaffEmployeeDTO> staffEmployees;


}