package nst;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import nst.app.dto.AddressDTO;
import nst.app.dto.AgencyLicenseModificationDTO;
import nst.app.dto.LEAddressDTO;
import nst.app.dto.StaffEmployeeDTO;
import nst.app.dto.le.LESafetyGadgetDTO;
import nst.app.dto.le.LETestingInstrumentDTO;
import nst.app.enums.AddressType;
import nst.dto.AttachmentDTO;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class TestAgencyLicenseModification extends AbstractTest {


  @Test
  public void test() throws Throwable {
    LoginUserUtil.USER = LoginUserUtil.CONTRACTOR;
    AgencyLicenseModificationDTO agencyLicenseModificationDTO = agencyLicenseModificationService
        .create();

    File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
    MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
        "image/png", new FileInputStream(fileItem));

    AttachmentDTO add = attachmentService
        .add(agencyLicenseModificationDTO.getId(), multipartFile, "8.1", "certificate_authorization",
            "Certificate of Authorization in original", "1", null);

    add = attachmentService
        .add(agencyLicenseModificationDTO.getId(), multipartFile, "9.1", "solvent_certificate",
            "solvent Certificate", "1", null);

    add = attachmentService
        .add(agencyLicenseModificationDTO.getId(), multipartFile, "4.9", "electrical_license",
            "Last updated electrical contractor license", "1", null);

    add = attachmentService
        .add(agencyLicenseModificationDTO.getId(), multipartFile, "3.9", "authorization_certificate",
            "Copy of authorization certificate", "1", null);

    add = attachmentService
        .add(agencyLicenseModificationDTO.getId(), multipartFile, "2.9", "renewal_documents",
            "Attach relevant documents for renewal (If more than two attach in zip)", "1", null);

      add = attachmentService
              .add(agencyLicenseModificationDTO.getId(), multipartFile, "1.1.1", "renewal_documents",
                      "Attach relevant documents for renewal (If more than two attach in zip)", "1",
                  null);

    agencyLicenseModificationDTO.setBranchEmail("branch@gmail.com");
    agencyLicenseModificationDTO.setBranchContactNo("7878282806");
    agencyLicenseModificationDTO.setMainOfficeEmail("main@gmail.com");
    agencyLicenseModificationDTO.setMainOfficeContactNo("7878282806");
    agencyLicenseModificationDTO
        .setCommunicationFacilityChangeRequest("This is sample change in communication Facility");
    agencyLicenseModificationDTO.setVehiclePossessionDetail("This is Vehicle Change request");
    agencyLicenseModificationDTO.setAgencyLegalStatus("This is legal status change ");

    AddressDTO address = new AddressDTO();
    address.setAddressType(AddressType.BRANCHOFFICE.getType());
    address.setHouseNo("55");
    address.setBuildingName("Arise");
    address.setTalukaName("vadodara");
    address.setDistrict("Vadodara");
    address.setState("GUJARAT");
    address.setPincode("390019");
    agencyLicenseModificationDTO.setBranchAddress(address);

    address = new AddressDTO();
    address.setAddressType(AddressType.MAINOFFICE.getType());
    address.setHouseNo("56");
    address.setBuildingName("Sapath3");
    address.setTalukaName("Ahmedabad");
    address.setDistrict("Ahmedabad");
    address.setState("GUJARAT");
    address.setPincode("390019");
    agencyLicenseModificationDTO.setOfficeAddress(address);

    address = new AddressDTO();
    address.setAddressType(AddressType.OFFICE.getType());
    address.setHouseNo("56");
    address.setBuildingName("Sapath3");
    address.setTalukaName("Ahmedabad");
    address.setDistrict("Ahmedabad");
    address.setState("GUJARAT");
    address.setPincode("390019");
    address.setAddrLine1("Line1");
    address.setAddrLine2("Line2");
    agencyLicenseModificationDTO.setBranchAddress(address);


     LEAddressDTO address1 = new LEAddressDTO();
      address1.setAddressType(AddressType.WORKSHOP.getType());
      address1.setHouseNo("56");
      address1.setBuildingName("Sapath3");
      address1.setTalukaName("Ahmedabad");
      address1.setDistrict("Ahmedabad");
      address1.setState("GUJARAT");
      address1.setPincode("390019");
      address1.setAddrLine1("Address1");
      address1.setAddrLine2("Address2");
      address1.setCitySurveyNo("123");
      address1.setPremisesName("Sayog Apartment");
      agencyLicenseModificationDTO.setWorkshopAddress(address1);



    List<StaffEmployeeDTO> staffEmployeeList = new ArrayList<>();

    StaffEmployeeDTO staffEmployeeDTO = new StaffEmployeeDTO();
    staffEmployeeDTO.setName("Ravi");
    staffEmployeeDTO.setQualification("MCA");
    staffEmployeeDTO.setDesignation("Software Engineer");
    staffEmployeeDTO.setJoiningDate("2014-12-01T00:00:00");
    staffEmployeeDTO.setExitDate("2017-12-01T00:00:00");
    staffEmployeeList.add(staffEmployeeDTO);

    staffEmployeeDTO = new StaffEmployeeDTO();
    staffEmployeeDTO.setName("Narendra");
    staffEmployeeDTO.setQualification("B.TECH");
    staffEmployeeDTO.setDesignation("JS Developer");
    staffEmployeeDTO.setJoiningDate("2014-12-01T00:00:00");
    staffEmployeeDTO.setExitDate("2017-12-01T00:00:00");
    staffEmployeeList.add(staffEmployeeDTO);

    agencyLicenseModificationDTO.setStaffEmployees(staffEmployeeList);

    List<LETestingInstrumentDTO> testingInstruments = new ArrayList<>();
    LETestingInstrumentDTO leTestingInstrumentDTO = new LETestingInstrumentDTO();
    leTestingInstrumentDTO.setMake("INDIAN STEEL");
    leTestingInstrumentDTO.setNumber("5460");
    leTestingInstrumentDTO.setSerialNumber("54654654ddsda");
    leTestingInstrumentDTO.setMachineName("AUTOMATED Lift");
    leTestingInstrumentDTO.setRemarks("no remarks");
    testingInstruments.add(leTestingInstrumentDTO);

    agencyLicenseModificationDTO.setTestingInstruments(testingInstruments);

    List<LESafetyGadgetDTO> safetyGadgets = new ArrayList<>();

    LESafetyGadgetDTO leSafetyGadgetDTO = new LESafetyGadgetDTO();
    leSafetyGadgetDTO.setMake("INDIAN STEEL");
    leSafetyGadgetDTO.setNumber("5460");
    leSafetyGadgetDTO.setName("MY Safety");
    leSafetyGadgetDTO.setRemarks("no remarks");
    safetyGadgets.add(leSafetyGadgetDTO);
    agencyLicenseModificationDTO.setSafetyGadgets(safetyGadgets);

    System.out.println("================" + agencyLicenseModificationDTO);

    AgencyLicenseModificationDTO save = agencyLicenseModificationService
        .saveForm(agencyLicenseModificationDTO);
    System.out.println("save = " + save.toJSON());

    //System.out.println(agencyLicenseModificationService.submitForm(agencyLicenseModificationDTO.getId()));
    //save.validateAll();

    save = agencyLicenseModificationService.get(agencyLicenseModificationDTO.getId());
    System.out.println("GET =>" + save.toJSON());

    System.out.println(
        "JSON =>" + agencyLicenseModificationService.getJson(agencyLicenseModificationDTO.getId())
            .toJSON());
    //System.out.println("PrintView =>" + agencyLicenseModificationService.getPrintView(agencyLicenseModificationDTO.getId()));
    //HtmlToPDF.toPDF(agencyLicenseModificationService.getPrintView(agencyLicenseModificationDTO.getId()));
  }
}
