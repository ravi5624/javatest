package nst;

import nst.app.dto.LiftEscalatorRenewalDTO;
import nst.app.enums.AddressType;
import nst.dto.AttachmentDTO;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

public class TestLiftEscalatorRenewal extends AbstractTest {


    @Test
    public void test() throws Throwable {

        LoginUserUtil.USER = LoginUserUtil.OWNER_USER;
        LiftEscalatorRenewalDTO liftEscalatorRenewalDTO = liftEscalatorRenewalService.create();

        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));
        AttachmentDTO add = attachmentService.add(liftEscalatorRenewalDTO.getId(), multipartFile, "8", "org_lic", "Original License of Lift/Escalator", "1",
            null);

        add = attachmentService.add(liftEscalatorRenewalDTO.getId(), multipartFile, "9", "relevant_doc_for_renewal", "Attach relevant documents for renewal ", "1",
            null);
        System.out.println("add = " + add);


        liftEscalatorRenewalDTO.setLiftEscalatorType("Escalator");
        liftEscalatorRenewalDTO.setLicenseNumber("4546666646dfdfs");
        liftEscalatorRenewalDTO.setLicenseIssueDate("2008-09-02T02:50:12");
        liftEscalatorRenewalDTO.setLicenseExpiryDate("2010-09-02T02:50:12");
        liftEscalatorRenewalDTO.setLicenseeFullName("Vijay k parmar");
        liftEscalatorRenewalDTO.setIsAddressOrOwnerChange("Yes");
        liftEscalatorRenewalDTO.setLiftSiteAddress(this.getLEAddressDTOByAddressType(AddressType.PREMISES));

        System.out.println("liftEscalatorRenewalDTO = " + liftEscalatorRenewalDTO.toJSON());

        LiftEscalatorRenewalDTO save = liftEscalatorRenewalService.saveForm(liftEscalatorRenewalDTO);
        System.out.println("save = " + save.toJSON());

        //save.validateAll();

        save = liftEscalatorRenewalService.get(liftEscalatorRenewalDTO.getId());
        System.out.println("GET =>" + save.toJSON());

        System.out.println("JSON =>" + liftEscalatorRenewalService.getJson(liftEscalatorRenewalDTO.getId()).toJSON());
        System.out.println("PrintView =>" + liftEscalatorRenewalService.getPrintView(liftEscalatorRenewalDTO.getId()));

    }
}
