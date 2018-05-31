package nst;

import nst.app.dto.*;
import nst.app.enums.AddressType;
import nst.app.enums.DesignationType;
import nst.dto.AttachmentDTO;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class TestContractorLicense extends AbstractTest {

    @Test
    public void test() throws Throwable {
        LoginUserUtil.USER = LoginUserUtil.CONTRACTOR;
        ContractorLicenseDTO contractorLicenseDTO = contractorLicenseService.create();

        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));

        AttachmentDTO add = attachmentService.add(contractorLicenseDTO.getId(), multipartFile, "3.7", "electricty_bill", "Attach electricty bill for address proof or any Govt. valid ID proof", "1",
            null);

        add = attachmentService.add(contractorLicenseDTO.getId(), multipartFile, "4.1", "partnership_deed", " Partnership deed", "1",
            null);

        add = attachmentService.add(contractorLicenseDTO.getId(), multipartFile, "5.1", "partner_signature", " Signature of partner", "1",
            null);

        add = attachmentService.add(contractorLicenseDTO.getId(), multipartFile, "5.2", "birth_date", " Date of birth proof", "1",
            null);

        add = attachmentService.add(contractorLicenseDTO.getId(), multipartFile, "5.3", "address_proof", "Address Proof", "1",
            null);

        add = attachmentService.add(contractorLicenseDTO.getId(), multipartFile, "7.6", "supervisor_address_proof", " Attach address proof of supervisor", "1",
            null);

        add = attachmentService.add(contractorLicenseDTO.getId(), multipartFile, "7.7", "medical_certificiate", " Attach medical certificate of civil surgeon", "1",
            null);

        add = attachmentService.add(contractorLicenseDTO.getId(), multipartFile, "8.1", "form_i", " Form “I” verified by electrical inspector or his representative", "1",
            null);

        add = attachmentService.add(contractorLicenseDTO.getId(), multipartFile, "8.2", "supervisor_signature", "Attach supervisor signature", "1",
            null);

        add = attachmentService.add(contractorLicenseDTO.getId(), multipartFile, "9.1", "bank_solvency_certificate", "Bank solvency certificate", "1",
            null);

        add = attachmentService.add(contractorLicenseDTO.getId(), multipartFile, "9.2", "instruments_details", " Details of instruments (please fill the editable PDF & attach it back)", "1",
            null);

        add = attachmentService.add(contractorLicenseDTO.getId(), multipartFile, "9.3", "instruments_invoice", " Invoice of instruments", "1",
            null);

        add = attachmentService.add(contractorLicenseDTO.getId(), multipartFile, "9.4", "owners_declaration", "Owners declaration", "1",
            null);

        add = attachmentService.add(contractorLicenseDTO.getId(), multipartFile, "9.5", "supervisor_declaration", "Declaration of supervisor", "1",
            null);

        add = attachmentService.add(contractorLicenseDTO.getId(), multipartFile, "9.6", "appointment_letter", "Appointment letter", "1",
            null);

        add = attachmentService.add(contractorLicenseDTO.getId(), multipartFile, "9.7", "declaration_letter", "Letter of declaration", "1",
            null);

        add = attachmentService.add(contractorLicenseDTO.getId(), multipartFile, "9.8", "affidavit", "Affidavit", "1",
            null);

        System.out.println("add = " + add);

        contractorLicenseDTO.setApplicantContractorName("ApplicantContractorName");
        contractorLicenseDTO.setMobile("252599998");
        contractorLicenseDTO.setEmail("252599998");
        contractorLicenseDTO.setOrganizationType("Government");
        contractorLicenseDTO.setContractorFromDate("2010-01-02T02:50:12");
        contractorLicenseDTO.setContractorToDate("2013-02-02T02:50:12");
        contractorLicenseDTO.setIsLicenseGranted("Yes");
        contractorLicenseDTO.setContractorLicNo("545435345");
        contractorLicenseDTO.setIssueDate("2015-08-02T02:50:12");
        contractorLicenseDTO.setSupervisorName("Vishal Patel");
        contractorLicenseDTO.setSupervisorAge(18);
        contractorLicenseDTO.setPermitNoOfSupervisor("1");
        contractorLicenseDTO.setSupervisorPermitIssueDate("2011-09-02T02:50:12");
        contractorLicenseDTO.setSupervisorBirthDate("1995-11-02T02:50:11");
        contractorLicenseDTO.setContractorName("Santosh");
        contractorLicenseDTO.setContractorLicenseNo("010465606");

        AddressDTO address = new AddressDTO();
        address.setAddressType(AddressType.OFFICE.getType());
        address.setBuildingName("Arise");
        address.setAddrLine1("Line1");
        address.setAddrLine2("Line2");
        address.setDistrict("Vadodara");
        address.setHouseNo("25");
        address.setPincode("390019");
        address.setState("Gujarat");
        address.setTalukaName("Sankheda");
        address.setVillage("Rampura");

        contractorLicenseDTO.setOfficeAddress(address);

        OrganizationDetailsDTO organizationDetailsDTO = new OrganizationDetailsDTO();
        organizationDetailsDTO.setNameOfPartner("Partner");
        organizationDetailsDTO.setHomeAddress("This is Home Address");
        organizationDetailsDTO.setBirthDate("1990-08-02T02:50:12");

        FormIDTO formIDTO = new FormIDTO();
        System.out.println("add = " + add);
        add = attachmentService.add(contractorLicenseDTO.getId(), multipartFile, "Other documents", "other_documents"
        );
        System.out.println("add = " + add);

        formIDTO.setTechnicalContractorName("TechnicalContractorName");
        formIDTO.setIssueDate("2011-11-02T02:50:12");

        FormIEmployerDTO emp =new FormIEmployerDTO();

        emp.setIsInactive("No");
        emp.setJoiningDate("2011-11-02T02:50:12");
        emp.setLeavingDate("2015-11-02T02:50:12");
        emp.setPermitNo("5454546");
        emp.setName("Virah");
        emp.setDesignation(DesignationType.SUPERVISOR);

        List<FormIEmployerDTO> employers = new ArrayList<>();
        employers.add(emp);

        emp =new FormIEmployerDTO();

        emp.setIsInactive("No");
        emp.setJoiningDate("2011-11-02T02:50:12");
        emp.setLeavingDate("2015-11-02T02:50:12");
        emp.setPermitNo("5454546s");
        emp.setName("Virah2");
        emp.setDesignation(DesignationType.WIREMAN);
        employers.add(emp);

        formIDTO.setEmployer(employers);

        List<OrganizationDetailsDTO> orgdtoList = new ArrayList<OrganizationDetailsDTO>();
        orgdtoList.add(organizationDetailsDTO);
        contractorLicenseDTO.setOrganizations(orgdtoList);
        contractorLicenseDTO.setFormI(formIDTO);

        System.out.println("contractorLicenseDTO = " + contractorLicenseDTO.toJSON());

        ContractorLicenseDTO save = contractorLicenseService.saveForm(contractorLicenseDTO);
//        System.out.println("save = " + save.toJSON());
        save = contractorLicenseService.get(contractorLicenseDTO.getId());
//        System.out.println("GET" + save.toJSON());
       // contractorLicenseService.delete(contractorLicenseDTO.getId());
        System.out.println("PrintView =>" + contractorLicenseService.getPrintView(contractorLicenseDTO.getId()));
//        HtmlToPDF.toPDF(contractorLicenseService.getPrintView(contractorLicenseDTO.getId()));

       System.out.println("JSON =>" + contractorLicenseService.getJson(save.getId()).toJSON());
    }
}
