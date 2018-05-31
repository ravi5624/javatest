package nst;

import nst.app.dto.BranchAddressDTO;
import nst.app.dto.OAndMAgencyLicenseDTO;
import nst.app.dto.StaffEmployeeDTO;
import nst.app.dto.le.AgencyLegalStatusDetailsDTO;
import nst.app.dto.le.LESafetyGadgetDTO;
import nst.app.dto.le.LETestingInstrumentDTO;
import nst.app.enums.AddressType;
import nst.app.enums.LiftEscalatorType;
import nst.dto.AttachmentDTO;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class TestOAndMAgencyLicense extends  AbstractTest{
    @Test
    public void test() throws Throwable {
        LoginUserUtil.USER = LoginUserUtil.OM_AGENCY;
        OAndMAgencyLicenseDTO oAndMagencyLicenseDTO = oAndMAgencyLicenseService.create();

        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));

        AttachmentDTO add = attachmentService.add(oAndMagencyLicenseDTO.getId(), multipartFile, "3.3", "partnership_deed", "Partnership Deed", "1",
            null);

        add = attachmentService.add(oAndMagencyLicenseDTO.getId(), multipartFile, "4.10", "ownership_proof", "Ownership or Occupacy Rights Proof", "1",
            null);

        add = attachmentService.add(oAndMagencyLicenseDTO.getId(), multipartFile, "5.9", "ownership_proof", "Ownership or Occupacy Rights Proof", "1",
            null);

        add = attachmentService.add(oAndMagencyLicenseDTO.getId(), multipartFile, "7.3", "authorization_certificate", "Copy of authorization certificate", "1",
            null);

        add = attachmentService.add(oAndMagencyLicenseDTO.getId(), multipartFile, "7.4", "maintained_detail", "Attach List of lifts/escalator erected/ maintained with details of license no. lift location, address, districtwise", "1",
            null);

        add = attachmentService.add(oAndMagencyLicenseDTO.getId(), multipartFile, "7.5", "company_catalog", "Company catalogue", "1",
            null);

        add = attachmentService.add(oAndMagencyLicenseDTO.getId(), multipartFile, "8.1", "contacactor_license", "Copy of electrical contractor license", "1",
            null);

        add = attachmentService.add(oAndMagencyLicenseDTO.getId(), multipartFile, "8.2", "contacactor_license", "Copy of Form “I” verified by authority (All Pages)", "1",
            null);

        add = attachmentService.add(oAndMagencyLicenseDTO.getId(), multipartFile, "9", "solvency_certificate", "Attach solvency certificate of 1 lakhs", "1",
            null);

        oAndMagencyLicenseDTO.setType("LIFT");
        oAndMagencyLicenseDTO.setAgencyName("Avadhoot Agency");
        oAndMagencyLicenseDTO.setAgencyLegalStatus("Proprietorship");
        oAndMagencyLicenseDTO.setEstablishmentYear(2017);
        oAndMagencyLicenseDTO.setLiftEscalatorType(LiftEscalatorType.LIFT.getType());

        AgencyLegalStatusDetailsDTO agencyLegalStatusDetailsDTO = new AgencyLegalStatusDetailsDTO();
        agencyLegalStatusDetailsDTO.setPartnerName("partner-name");

        List<AgencyLegalStatusDetailsDTO> agencyList= new ArrayList<>();
        agencyList.add(agencyLegalStatusDetailsDTO);
        oAndMagencyLicenseDTO.setAgencyLegalStatusDetails(agencyList);

        /*oAndMagencyLicenseDTO.setBusinessEmail("Business@gmail.com");
        oAndMagencyLicenseDTO.setBusinessContactNo("7878282806");
        oAndMagencyLicenseDTO.setBusinessWebsite("www.gmail.com");

        oAndMagencyLicenseDTO.setBranchEmail("branch@gmail.com");
        oAndMagencyLicenseDTO.setBranchContactNo("7878282806");
        oAndMagencyLicenseDTO.setBranchWebsite("www.gmail.com");*/
        oAndMagencyLicenseDTO.setContactEmail("contact@gmail.com");
        oAndMagencyLicenseDTO.setContactNo("7878282806");
        oAndMagencyLicenseDTO.setIsAuthorizationCertificateIssued("yes");
        oAndMagencyLicenseDTO.setAuthorizationNo("11056");
        oAndMagencyLicenseDTO.setAuthorizationCertificateDate("2017-12-01T00:00:00");
        oAndMagencyLicenseDTO.setRegistrationNumberElectricalContractor("78787979799");
        oAndMagencyLicenseDTO.setBankName("SBI");
        oAndMagencyLicenseDTO.setBranchName("AJWA ROAD");
        oAndMagencyLicenseDTO.setIssueDate("2017-12-01T00:00:00");
        oAndMagencyLicenseDTO.setAmount(25000.0);
        oAndMagencyLicenseDTO.setBankAddress("ajwa road sayaji park");

        /* Part 1 end*/

        /* Part 2 start*/

        List<StaffEmployeeDTO> staffEmployee = new ArrayList<>();
        StaffEmployeeDTO staffEmp=new StaffEmployeeDTO();
        staffEmp.setDesignation("");
        staffEmp.setQualification("");
        staffEmp.setName("Test");
        staffEmp.setExitDate("2017-12-16T00:00:00");
        staffEmp.setJoiningDate("2017-12-01T00:00:00");
        staffEmployee.add(staffEmp);
        oAndMagencyLicenseDTO.setStaffEmployees(staffEmployee);

        List<LETestingInstrumentDTO> testingInstruments = new ArrayList<>();
        LETestingInstrumentDTO leTestingInstrumentDTO = new LETestingInstrumentDTO();
        leTestingInstrumentDTO.setMake("INDIAN STEEL");
        leTestingInstrumentDTO.setNumber("5460");
        leTestingInstrumentDTO.setSerialNumber("54654654");
        leTestingInstrumentDTO.setMachineName("AUTOMATED Lift");
        leTestingInstrumentDTO.setRemarks("no remarks");

        testingInstruments.add(leTestingInstrumentDTO);

        oAndMagencyLicenseDTO.setTestingInstruments(testingInstruments);

        List<LESafetyGadgetDTO> safetyGadgets = new ArrayList<>();

        LESafetyGadgetDTO leSafetyGadgetDTO = new LESafetyGadgetDTO();
        leSafetyGadgetDTO.setMake("INDIAN STEEL");
        leSafetyGadgetDTO.setNumber("5460");
        leSafetyGadgetDTO.setName("MY Safety");
        leSafetyGadgetDTO.setRemarks("no remarks");
        safetyGadgets.add(leSafetyGadgetDTO);
        oAndMagencyLicenseDTO.setSafetyGadgets(safetyGadgets);

        oAndMagencyLicenseDTO.setVehiclePossession("owned");
        oAndMagencyLicenseDTO.setRemarks("no remarks");

        /* Part 2 end*/

        System.out.println("oAndMagencyLicenseDTO = " + oAndMagencyLicenseDTO.toJSON());

        oAndMagencyLicenseDTO.setBusinessAddress(this.getBMAddressDTOByAddressType(AddressType.BUSINESS));
        List<BranchAddressDTO> list = new ArrayList<>();
        list.add(this.getBranchAddressDTOByAddressType(AddressType.BRANCHOFFICE));
        list.add(this.getBranchAddressDTOByAddressType(AddressType.BRANCHOFFICE));
        oAndMagencyLicenseDTO.setBranchAddress(list);

        OAndMAgencyLicenseDTO save = oAndMAgencyLicenseService.saveForm(oAndMagencyLicenseDTO);
        System.out.println("save = " + save.toJSON());

        // System.out.println(iAndTAgencyLicenseService.submitForm(iAndTagencyLicenseDTO.getId()));

        //save.validateAll();

        save = oAndMAgencyLicenseService.get(oAndMagencyLicenseDTO.getId());
        System.out.println("GET =>" + save.toJSON());

        System.out.println("JSON =>" + oAndMAgencyLicenseService.getJson(oAndMagencyLicenseDTO.getId()).toJSON());
        //System.out.println("PrintView =>" + oAndMAgencyLicenseService.getPrintView(oAndMagencyLicenseDTO.getId()));
    }

}
