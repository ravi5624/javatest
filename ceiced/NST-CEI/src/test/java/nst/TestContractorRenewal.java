package nst;

import nst.app.dto.AddressDTO;
import nst.app.dto.ContractorRenewalDTO;
import nst.app.enums.AddressType;
import nst.dto.AttachmentDTO;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

public class TestContractorRenewal extends AbstractTest {

    @Test
    public void test() throws Throwable {
        LoginUserUtil.USER = LoginUserUtil.CONTRACTOR;
        ContractorRenewalDTO contractorRenewalDTO = contractorRenewalService.create();
        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));

        AttachmentDTO add = attachmentService.add(contractorRenewalDTO.getId(), multipartFile, "5", "form_i", "Copy of Form “I” verified by authority or his representative", "1",
            null);
        add = attachmentService.add(contractorRenewalDTO.getId(), multipartFile, "8.7", "electricty_bill", "Attach electricty bill for address pfroof or any Govt. valid ID proof", "1",
            null);
        add = attachmentService.add(contractorRenewalDTO.getId(), multipartFile, "9.1", "owner_signature", "Attach Owners Signature","1",
            null);

        contractorRenewalDTO.setContractorName("contractorName");
        contractorRenewalDTO.setContractorLicNo("546546546");
        contractorRenewalDTO.setIssueDate("2011-09-02T02:50:12");
        contractorRenewalDTO.setLicenseExpiryDate("2012-09-02T02:50:12");
        contractorRenewalDTO.setOldSupervisorName("Name1");
        contractorRenewalDTO.setOldSupervisorBirthDate("1988-09-02T02:50:12");
        contractorRenewalDTO.setOldSupervisorJoineeDate("2011-09-02T02:50:12");
        contractorRenewalDTO.setOldSupervisorLeavingDate("2013-09-02T02:50:12");
        contractorRenewalDTO.setIsWorkingOldSupervisor("True");
        contractorRenewalDTO.setNewSupervisorName("Vijx");
        contractorRenewalDTO.setNewSupervisorBirthDate("1988-12-02T02:50:12");
        contractorRenewalDTO.setNewSupervisorJoineeDate("2013-09-02T02:50:12");
        contractorRenewalDTO.setIsAddressChange("Yes");

        AddressDTO address = new AddressDTO();
        address.setAddressType(AddressType.PERMANENT.getType());
        address.setBuildingName("Arise");
        address.setAddrLine1("Line1");
        address.setAddrLine2("Line2");
        address.setDistrict("Vadodara");
        address.setHouseNo("25");
        address.setId(4l);
        address.setPincode("390019");
        address.setState("Gujarat");
        address.setTalukaName("Sankheda");
        address.setVillage("Rampura");

        contractorRenewalDTO.setParmanentAddress(address);


        System.out.println("contractorRenewalDTO = " + contractorRenewalDTO.toJSON());

        ContractorRenewalDTO save = contractorRenewalService.saveForm(contractorRenewalDTO);
        System.out.println("save = " + save.toJSON());
        save = contractorRenewalService.get(contractorRenewalDTO.getId());
        System.out.println("GET" + save.toJSON());
        //contractorRenewalService.delete(contractorRenewalDTO.getId());
        System.out.println("JSON =>" + contractorRenewalService.getJson(save.getId()).toJSON());
        System.out.println("PrintView =>" + contractorRenewalService.getPrintView(contractorRenewalDTO.getId()));
    }
}
