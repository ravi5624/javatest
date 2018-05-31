package nst;

import nst.app.dto.BranchAddressDTO;
import nst.app.dto.IAndTAgencyLicenseDTO;
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

public class TestIAndTAgencyLicense extends  AbstractTest {

    @Test
    public void test() throws Throwable {
        LoginUserUtil.USER = LoginUserUtil.IT_AGENCY;
        IAndTAgencyLicenseDTO iAndTagencyLicenseDTO = iAndTAgencyLicenseService.create();

        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));

        AttachmentDTO add = attachmentService.add(iAndTagencyLicenseDTO.getId(), multipartFile, "3.3", "partnership_deed", "Partnership Deed", "1",
            null);

        add = attachmentService.add(iAndTagencyLicenseDTO.getId(), multipartFile, "4.10", "ownership_proof", "Ownership or Occupacy Rights Proof", "1",
            null);

        add = attachmentService.add(iAndTagencyLicenseDTO.getId(), multipartFile, "5.9", "ownership_proof", "Ownership or Occupacy Rights Proof", "1",
            null);

        add = attachmentService.add(iAndTagencyLicenseDTO.getId(), multipartFile, "7.3", "authorization_certificate", "Copy of authorization certificate", "1",
            null);

        add = attachmentService.add(iAndTagencyLicenseDTO.getId(), multipartFile, "7.4", "maintained_detail", "Attach List of lifts/escalator erected/ maintained with details of license no. lift location, address, districtwise", "1",
            null);

        add = attachmentService.add(iAndTagencyLicenseDTO.getId(), multipartFile, "7.5", "company_catalog", "Company catalogue", "1",
            null);

        add = attachmentService.add(iAndTagencyLicenseDTO.getId(), multipartFile, "8.1", "contacactor_license", "Copy of electrical contractor license", "1",
            null);

        add = attachmentService.add(iAndTagencyLicenseDTO.getId(), multipartFile, "8.2", "contacactor_license", "Copy of Form “I” verified by authority (All Pages)", "1",
            null);

        add = attachmentService.add(iAndTagencyLicenseDTO.getId(), multipartFile, "9", "solvency_certificate", "Attach solvency certificate of 1 lakhs", "1",
            null);

        iAndTagencyLicenseDTO.setType("LIFT");
        iAndTagencyLicenseDTO.setAgencyName("Avadhoot Agency");
        iAndTagencyLicenseDTO.setAgencyLegalStatus("Proprietorship");
        iAndTagencyLicenseDTO.setEstablishmentYear(2017);
        iAndTagencyLicenseDTO.setLiftEscalatorType(LiftEscalatorType.LIFT.getType());

        AgencyLegalStatusDetailsDTO agencyLegalStatusDetailsDTO = new AgencyLegalStatusDetailsDTO();
        agencyLegalStatusDetailsDTO.setPartnerName("partner-name");

        List<AgencyLegalStatusDetailsDTO> agencyList= new ArrayList<>();
        agencyList.add(agencyLegalStatusDetailsDTO);
        iAndTagencyLicenseDTO.setAgencyLegalStatusDetails(agencyList);


        iAndTagencyLicenseDTO.setBusinessEmail("Business@gmail.com");
        iAndTagencyLicenseDTO.setBusinessContactNo("7878282806");
        iAndTagencyLicenseDTO.setBusinessWebsite("www.gmail.com");

        iAndTagencyLicenseDTO.setBranchEmail("branch@gmail.com");
        iAndTagencyLicenseDTO.setBranchContactNo("7878282806");
        iAndTagencyLicenseDTO.setBranchWebsite("www.gmail.com");
        iAndTagencyLicenseDTO.setContactEmail("contact@gmail.com");
        iAndTagencyLicenseDTO.setContactNo("7878282806");
        iAndTagencyLicenseDTO.setIsAuthorizationCertificateIssued("yes");
        iAndTagencyLicenseDTO.setAuthorizationNo("11056");
        iAndTagencyLicenseDTO.setAuthorizationCertificateDate("2017-12-01T00:00:00");
        iAndTagencyLicenseDTO.setRegistrationNumberElectricalContractor("78787979799");
        iAndTagencyLicenseDTO.setBankName("SBI");
        iAndTagencyLicenseDTO.setBranchName("AJWA ROAD");
        iAndTagencyLicenseDTO.setIssueDate("2017-12-01T00:00:00");
        iAndTagencyLicenseDTO.setAmount(25000.0);
        iAndTagencyLicenseDTO.setBankAddress("ajwa road sayaji park");

        /* Part 1 end*/


        /* Part 2 start*/
        iAndTagencyLicenseDTO.setCommunicationFacilitiesDetails("yes communication");


        List<LETestingInstrumentDTO> testingInstruments = new ArrayList<>();
        LETestingInstrumentDTO leTestingInstrumentDTO = new LETestingInstrumentDTO();
        leTestingInstrumentDTO.setMake("INDIAN STEEL");
        leTestingInstrumentDTO.setNumber("5460");
        leTestingInstrumentDTO.setSerialNumber("54654654ddsda");
        leTestingInstrumentDTO.setMachineName("AUTOMATED Lift");
        leTestingInstrumentDTO.setRemarks("no remarks");
        testingInstruments.add(leTestingInstrumentDTO);

        iAndTagencyLicenseDTO.setTestingInstruments(testingInstruments);

        List<LESafetyGadgetDTO> safetyGadgets = new ArrayList<>();

        LESafetyGadgetDTO leSafetyGadgetDTO = new LESafetyGadgetDTO();
        leSafetyGadgetDTO.setMake("INDIAN STEEL");
        leSafetyGadgetDTO.setNumber("5460");
        leSafetyGadgetDTO.setName("MY Safety");
        leSafetyGadgetDTO.setRemarks("no remarks");
        safetyGadgets.add(leSafetyGadgetDTO);
        iAndTagencyLicenseDTO.setSafetyGadgets(safetyGadgets);



        iAndTagencyLicenseDTO.setRemarks("no remarks");

        /* Part 2 end*/
        List<BranchAddressDTO> list = new ArrayList<>();
        list.add(this.getBranchAddressDTOByAddressType(AddressType.BRANCHOFFICE));
        list.add(this.getBranchAddressDTOByAddressType(AddressType.BRANCHOFFICE));
        iAndTagencyLicenseDTO.setBranchAddress(list);

        iAndTagencyLicenseDTO.setBusinessAddress(this.getBMAddressDTOByAddressType(AddressType.BUSINESS));


        IAndTAgencyLicenseDTO save = iAndTAgencyLicenseService.saveForm(iAndTagencyLicenseDTO);
        System.out.println("save = " + save.toJSON());

        //save.validateAll();

        save = iAndTAgencyLicenseService.get(iAndTagencyLicenseDTO.getId());
        System.out.println("GET =>" + save.toJSON());

        System.out.println("JSON =>" + iAndTAgencyLicenseService.getJson(iAndTagencyLicenseDTO.getId()).toJSON());
        System.out.println("PrintView =>" + iAndTAgencyLicenseService.getPrintView(iAndTagencyLicenseDTO.getId()));
       // HtmlToPDF.toPDF(iAndTAgencyLicenseService.getPrintView(iAndTagencyLicenseDTO.getId()));
    }


}
