package nst;

import java.io.File;
import java.io.FileInputStream;
import nst.app.dto.AddressDTO;
import nst.app.dto.ContractorModificationDTO;
import nst.app.enums.AddressType;
import nst.dto.AttachmentDTO;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class TestContractorModification extends AbstractTest {

    @Test
    public void test() throws Throwable {
        LoginUserUtil.USER = LoginUserUtil.CONTRACTOR;
        ContractorModificationDTO contractorModificationDTO = contractorModificationService.create();

        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));

        AttachmentDTO add = attachmentService.add(contractorModificationDTO.getId(), multipartFile, "6.7", "electricty_bill", "Attach electricty bill for address pfroof or any Govt. valid ID proof", "1",
            null);

        contractorModificationDTO.setApplicantContractorName("ApplicantContractorName");
        contractorModificationDTO.setContractorLicenseNo("252599998");
        contractorModificationDTO.setIsAddressChange("YES");
        contractorModificationDTO.setLicenseNoIssueDate("2015-08-02T02:50:12");
        contractorModificationDTO.setIsNameCorrection("Yes");


        AddressDTO address = new AddressDTO();
        address.setAddressType(AddressType.OFFICE.getType());
        address.setBuildingName("Arise");
        address.setDistrict("Vadodara");
        address.setHouseNo("25");
        address.setPincode("390019");
        address.setState("Gujarat");
        address.setTalukaName("Sankheda");
        address.setVillage("Rampura");

        contractorModificationDTO.setOfficeAddress(address);


        System.out.println("contractorModificationDTO = " + contractorModificationDTO.toJSON());

        ContractorModificationDTO save = contractorModificationService.saveForm(contractorModificationDTO);
        System.out.println("save = " + save.toJSON());
        save = contractorModificationService.get(contractorModificationDTO.getId());
        System.out.println("GET" + save.toJSON());

        System.out.println("JSON =>" + contractorModificationService.getJson(save.getId()).toJSON());
        System.out.println("PrintView =>" + contractorModificationService.getPrintView(contractorModificationDTO.getId()));

        //contractorModificationService.delete(contractorModificationDTO.getId());
    }
   
}
