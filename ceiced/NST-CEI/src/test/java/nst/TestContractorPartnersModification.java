package nst;

import java.io.File;
import java.io.FileInputStream;
import nst.app.dto.ContractorPartnersModificationDTO;
import nst.app.dto.OrganizationDetailsDTO;
import nst.dto.AttachmentDTO;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class TestContractorPartnersModification extends AbstractTest {

    @Test
    public void test() throws Throwable {
        LoginUserUtil.USER = LoginUserUtil.CONTRACTOR;
        ContractorPartnersModificationDTO contractorPartnersModificationDTO = contractorPartnersModificationService.create();

        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));

        AttachmentDTO add = attachmentService.add(contractorPartnersModificationDTO.getId(), multipartFile, "4.1", "partnership_deed", "Partnership deed (in case of change in partners) ", "1",
            null);

        add = attachmentService.add(contractorPartnersModificationDTO.getId(), multipartFile, "5.1", "birth_date", " Date of birth proof", "1",
            null);

        add = attachmentService.add(contractorPartnersModificationDTO.getId(), multipartFile, "5.2", "address_proof", "Address Proof", "1",
            null);

        add = attachmentService.add(contractorPartnersModificationDTO.getId(), multipartFile, "5.3", "birth_date", " Date of birth proof", "1",
            null);

        add = attachmentService.add(contractorPartnersModificationDTO.getId(), multipartFile, "5.4", "address_proof", "Address Proof", "1",
            null);


        contractorPartnersModificationDTO.setApplicantContractorName("applicantContractorName");
        contractorPartnersModificationDTO.setContractorLicenseNo("252599998");
        contractorPartnersModificationDTO.setLicenseIssueDate("2015-08-02T02:50:12");
        contractorPartnersModificationDTO.setOrganizationType("Government");

        OrganizationDetailsDTO dto = new OrganizationDetailsDTO();
        dto.setId(1L);
        dto.setBirthDate("2015-08-02T02:50:12");
        dto.setHomeAddress("454654646");
        dto.setNameOfPartner("NEXTINNDIA");
        contractorPartnersModificationDTO.getPartners().add(dto);

        System.out.println("contractorPartnersModificationDTO = " + contractorPartnersModificationDTO.toJSON());

        ContractorPartnersModificationDTO save = contractorPartnersModificationService.saveForm(contractorPartnersModificationDTO);
        //TODO: Need to test
        contractorPartnersModificationService.addOrganization(save.getId());

        System.out.println("save = " + save.toJSON());
        save = contractorPartnersModificationService.get(contractorPartnersModificationDTO.getId());
        System.out.println("GET" + save.toJSON());
        System.out.println("JSON =>" + contractorPartnersModificationService.getJson(save.getId()).toJSON());
        System.out.println("PrintView =>" + contractorPartnersModificationService.getPrintView(contractorPartnersModificationDTO.getId()));
        //contractorPartnersModificationService.delete(contractorPartnersModificationDTO.getId());
    }
}
