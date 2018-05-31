package nst;

import nst.app.dto.AddressDTO;
import nst.app.dto.AgencyLicenseRenewalDTO;
import nst.app.enums.AddressType;
import nst.app.enums.AgencyType;
import nst.dto.AttachmentDTO;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

public class TestAgencyLicenseRenewal extends AbstractTest {
    @Test
    public void test() throws Throwable {
        LoginUserUtil.USER = LoginUserUtil.EM_AGENCY;
        AgencyLicenseRenewalDTO agencyLicenseRenewalDTO = agencyLicenseRenewalService.create();

        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));

        AttachmentDTO add = attachmentService.add(agencyLicenseRenewalDTO.getId(), multipartFile, "8", "certificate_authorization", "Certificate of Authorization in original", "1",
            null);

        add = attachmentService.add(agencyLicenseRenewalDTO.getId(), multipartFile, "9", "solvent_certificate", "solvent Certificate", "1",
            null);

        add = attachmentService.add(agencyLicenseRenewalDTO.getId(), multipartFile, "10", "electrical_license", "Last updated electrical contractor license", "1",
            null);

        add = attachmentService.add(agencyLicenseRenewalDTO.getId(), multipartFile, "11", "authorization_certificate", "Copy of authorization certificate", "1",
            null);

        add = attachmentService.add(agencyLicenseRenewalDTO.getId(), multipartFile, "12", "renewal_documents", "Attach relevant documents for renewal (If more than two attach in zip)", "1",
            null);

        agencyLicenseRenewalDTO.setAgencyType(AgencyType.E_M_AGENCY);
        agencyLicenseRenewalDTO.setAgencyAuthNo("EM511212");
        agencyLicenseRenewalDTO.setIssueDate("2017-12-01T00:00:00");
        agencyLicenseRenewalDTO.setExpiryDate("2017-12-01T00:00:00");
        agencyLicenseRenewalDTO.setNameOfAgency("agency-name");
        agencyLicenseRenewalDTO.setEmail("sagar@gmail.com");
        agencyLicenseRenewalDTO.setContactNo("9999999999");
        agencyLicenseRenewalDTO.setWebsiteUrl("www.google.com");
        agencyLicenseRenewalDTO.setIsChangeAnyDetails("false");

        AddressDTO address = new AddressDTO();
        address.setAddressType(AddressType.BRANCHOFFICE.getType());
        address.setHouseNo("55");
        address.setBuildingName("Arise");
        address.setTalukaName("vadodara");
        address.setDistrict("Vadodara");
        address.setState("GUJARAT");
        address.setPincode("390019");
        agencyLicenseRenewalDTO.setOfficeAddress(address);


        AgencyLicenseRenewalDTO save = agencyLicenseRenewalService.saveForm(agencyLicenseRenewalDTO);
        System.out.println("save = " + save.toJSON());

        //System.out.println(agencyLicenseRenewalService.submitForm(agencyLicenseRenewalDTO.getId()));
        //save.validateAll();

        save = agencyLicenseRenewalService.get(agencyLicenseRenewalDTO.getId());
        System.out.println("GET =>" + save.toJSON());

        System.out.println("JSON =>" + agencyLicenseRenewalService.getJson(agencyLicenseRenewalDTO.getId()).toJSON());
        //System.out.println("PrintView =>" + agencyLicenseRenewalService.getPrintView(agencyLicenseRenewalDTO.getId()));
        //HtmlToPDF.toPDF(agencyLicenseRenewalService.getPrintView(agencyLicenseRenewalDTO.getId()));
    }
}
