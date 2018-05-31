package nst;

import nst.app.dto.*;
import nst.app.dto.le.AgencyLegalStatusDetailsDTO;
import nst.app.dto.le.LESafetyGadgetDTO;
import nst.app.dto.le.LETestingInstrumentDTO;
import nst.app.enums.AddressType;
import nst.dto.AttachmentDTO;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class TestEAndMAgencyLicense extends AbstractTest {
    @Test
    public void test() throws Throwable {
        LoginUserUtil.USER = LoginUserUtil.EM_AGENCY;
        EAndMAgencyLicenseDTO eAndMagencyLicenseDTO = eAndMAgencyLicenseService.create();

        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));

        AttachmentDTO add = attachmentService.add(eAndMagencyLicenseDTO.getId(), multipartFile, "3.3", "partnership_deed", "Partnership Deed", "1",
            null);

        add = attachmentService.add(eAndMagencyLicenseDTO.getId(), multipartFile, "4.10", "ownership_proof", "Ownership or Occupacy Rights Proof", "1",
            null);

        add = attachmentService.add(eAndMagencyLicenseDTO.getId(), multipartFile, "5.9", "ownership_proof", "Ownership or Occupacy Rights Proof", "1",
            null);

        add = attachmentService.add(eAndMagencyLicenseDTO.getId(), multipartFile, "7.3", "authorization_certificate", "Copy of authorization certificate", "1",
            null);

        add = attachmentService.add(eAndMagencyLicenseDTO.getId(), multipartFile, "7.4", "maintained_detail", "Attach List of lifts/escalator erected/ maintained with details of license no. lift location, address, districtwise", "1",
            null);

        add = attachmentService.add(eAndMagencyLicenseDTO.getId(), multipartFile, "7.5", "company_catalog", "Company catalogue", "1",
            null);

        add = attachmentService.add(eAndMagencyLicenseDTO.getId(), multipartFile, "8.1", "contacactor_license", "Copy of electrical contractor license", "1",
            null);

        add = attachmentService.add(eAndMagencyLicenseDTO.getId(), multipartFile, "8.2", "contacactor_license", "Copy of Form “I” verified by authority (All Pages)", "1",
            null);

        add = attachmentService.add(eAndMagencyLicenseDTO.getId(), multipartFile, "9", "solvency_certificate", "Attach solvency certificate of 5 lakhs", "1",
            null);

        add = attachmentService.add(eAndMagencyLicenseDTO.getId(), multipartFile, "12.1", "registration_factory_act", "Copy of registration factory act", "2",
            null);

        add = attachmentService.add(eAndMagencyLicenseDTO.getId(), multipartFile, "13.1", "ownership_tax_bill", "Proof of ownership tax bill", "2",
            null);

        add = attachmentService.add(eAndMagencyLicenseDTO.getId(), multipartFile, "13.2", "electricity_bill", "Electricity bill", "2",
            null);

        add = attachmentService.add(eAndMagencyLicenseDTO.getId(), multipartFile, "14.2", "recent_copy_bill", "Attach recent copy of bill", "2",
            null);

        add = attachmentService.add(eAndMagencyLicenseDTO.getId(), multipartFile, "14.3", "tax_bill", "Tax bill copy for proof of area", "2",
            null);

        add = attachmentService.add(eAndMagencyLicenseDTO.getId(), multipartFile, "17.1", "rc_book", "RC book proof", "2",
            null);

        eAndMagencyLicenseDTO.setLiftEscalatorType("Lift");
        eAndMagencyLicenseDTO.setAgencyName("Avadhoot Agency");
        eAndMagencyLicenseDTO.setAgencyLegalStatus("Proprietorship");
        eAndMagencyLicenseDTO.setEstablishmentYear(2017);

        AgencyLegalStatusDetailsDTO  agencyLegalStatusDetailsDTO = new AgencyLegalStatusDetailsDTO();
        agencyLegalStatusDetailsDTO.setPartnerName("partner1");

        List<AgencyLegalStatusDetailsDTO> agencyList= new ArrayList<AgencyLegalStatusDetailsDTO>();
        agencyList.add(agencyLegalStatusDetailsDTO);
        eAndMagencyLicenseDTO.setAgencyLegalStatusDetails(agencyList);


        eAndMagencyLicenseDTO.setContactEmail("contact@gmail.com");
        eAndMagencyLicenseDTO.setContactNo("7878282806");
        eAndMagencyLicenseDTO.setIsAuthorizationCertificateIssued("yes");
        eAndMagencyLicenseDTO.setAuthorizationNo("11056");
        eAndMagencyLicenseDTO.setAuthorizationCertificateDate("2017-12-01T00:00:00");
        eAndMagencyLicenseDTO.setRegistrationNumberElectricalContractor("78787979799");
        eAndMagencyLicenseDTO.setBankName("SBI");
        eAndMagencyLicenseDTO.setBranchName("AJWA ROAD");
        eAndMagencyLicenseDTO.setIssueDate("2017-12-01T00:00:00");
        eAndMagencyLicenseDTO.setAmount(25000.0);
        eAndMagencyLicenseDTO.setBankAddress("ajwa road sayaji park");

        /* Part 1 end*/

        /* Part 2 start*/
        eAndMagencyLicenseDTO.setManufacturingUnitDetails("manufacture by appolo ");
        eAndMagencyLicenseDTO.setIsRegistrationGranted("yes");
        eAndMagencyLicenseDTO.setOccupacyRights("owned");
        eAndMagencyLicenseDTO.setElectricityBillNo("432234sdfsdf");
        eAndMagencyLicenseDTO.setCommunicationFacilitiesDetails("yes communication");
        List<LETestingInstrumentDTO> testingInstruments = new ArrayList<>();
        LETestingInstrumentDTO leTestingInstrumentDTO = new LETestingInstrumentDTO();
        leTestingInstrumentDTO.setMake("INDIAN STEEL");
        leTestingInstrumentDTO.setNumber("5460");
        leTestingInstrumentDTO.setSerialNumber("54654654ddsda");
        leTestingInstrumentDTO.setMachineName("AUTOMATED Lift");
        leTestingInstrumentDTO.setRemarks("machine is new");
        testingInstruments.add(leTestingInstrumentDTO);

        eAndMagencyLicenseDTO.setTestingInstruments(testingInstruments);

        List<LESafetyGadgetDTO> safetyGadgets = new ArrayList<>();

        LESafetyGadgetDTO leSafetyGadgetDTO = new LESafetyGadgetDTO();
        leSafetyGadgetDTO.setMake("INDIAN STEEL");
        leSafetyGadgetDTO.setNumber("5460");
        leSafetyGadgetDTO.setName("MY Safety");
        leSafetyGadgetDTO.setRemarks("no remarks");
        safetyGadgets.add(leSafetyGadgetDTO);
        eAndMagencyLicenseDTO.setSafetyGadgets(safetyGadgets);

        List<StaffEmployeeDTO> staffEmployee = new ArrayList<>();
        StaffEmployeeDTO staffEmp=new StaffEmployeeDTO();
        staffEmp.setDesignation("");
        staffEmp.setQualification("");
        staffEmp.setName("Test");
        staffEmp.setExitDate("2017-12-16T00:00:00");
        staffEmp.setJoiningDate("2017-12-01T00:00:00");
        staffEmployee.add(staffEmp);
        eAndMagencyLicenseDTO.setStaffEmployees(staffEmployee);

        eAndMagencyLicenseDTO.setVehiclePossession("owned");
        eAndMagencyLicenseDTO.setRemarks("no remarks");

        /* Part 2 end*/

        System.out.println("eAndMagencyLicenseDTO = " + eAndMagencyLicenseDTO.toJSON());

        List<BranchAddressDTO> list = new ArrayList<>();
        list.add(this.getBranchAddressDTOByAddressType(AddressType.BRANCHOFFICE));
        list.add(this.getBranchAddressDTOByAddressType(AddressType.BRANCHOFFICE));
        eAndMagencyLicenseDTO.setBranchAddress(list);

        eAndMagencyLicenseDTO.setBusinessAddress(this.getBMAddressDTOByAddressType(AddressType.BUSINESS));

        LEAddressDTO addressDTO = new LEAddressDTO();
        addressDTO.setAddressType(AddressType.WORKSHOP.getType());
        addressDTO.setAddrLine1("line1");
        addressDTO.setAddrLine2("Line2");
        addressDTO.setBuildingName("WORKArise");
        addressDTO.setDistrict("Vadodara");
        addressDTO.setState("GUJARAT");
        addressDTO.setPincode("390019");
        addressDTO.setCountry("India");
        eAndMagencyLicenseDTO.setWorkshopAddress(addressDTO);



        EAndMAgencyLicenseDTO save = eAndMAgencyLicenseService.saveForm(eAndMagencyLicenseDTO);
        System.out.println("save = " + save.toJSON());

        //System.out.println(eAndMAgencyLicenseService.submitForm(eAndMagencyLicenseDTO.getId()));

        //save.validateAll();

        save = eAndMAgencyLicenseService.get(eAndMagencyLicenseDTO.getId());
        System.out.println("GET =>" + save.toJSON());

        System.out.println("JSON =>" + eAndMAgencyLicenseService.getJson(eAndMagencyLicenseDTO.getId()).toJSON());
        //System.out.println("PrintView =>" + eAndMAgencyLicenseService.getPrintView(eAndMagencyLicenseDTO.getId()));
        //HtmlToPDF.toPDF(eAndMAgencyLicenseService.getPrintView(eAndMagencyLicenseDTO.getId()));
    }
}
