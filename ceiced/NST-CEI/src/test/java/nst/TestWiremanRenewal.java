package nst;

import java.io.File;
import java.io.FileInputStream;
import nst.app.dto.WiremanRenewalDTO;
import nst.dto.AttachmentDTO;
import nst.util.HtmlToPDF;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class TestWiremanRenewal extends AbstractTest {

    @Test
    public void test() throws Throwable {
        LoginUserUtil.USER = LoginUserUtil.WIREMAN;
        WiremanRenewalDTO wiremanRenewalDTO = wiremanRenewalService.create();

        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));
        AttachmentDTO add = attachmentService
                .add(wiremanRenewalDTO.getId(), multipartFile, "Original License", "original_License"
                );
        System.out.println("add = " + add);
        add = attachmentService.add(wiremanRenewalDTO.getId(), multipartFile, "Other documents", "other_documents"
        );
        System.out.println("add = " + add);

        wiremanRenewalDTO.setFirstName("FN");
        wiremanRenewalDTO.setMiddleName("MN");
        wiremanRenewalDTO.setSurname("SN");
        wiremanRenewalDTO.setPermitNo("4543545345435");
        wiremanRenewalDTO.setIssueDate("2011-11-02T02:50:12");
        wiremanRenewalDTO.setFromDate("2011-11-02T02:50:12");
        wiremanRenewalDTO.setToDate("2011-11-02T02:50:12");

        System.out.println("wiremanRenewalDTO = " + wiremanRenewalDTO.toJSON());

        WiremanRenewalDTO save = wiremanRenewalService.saveForm(wiremanRenewalDTO);
        System.out.println("save = " + save.toJSON());
        save = wiremanRenewalService.get(wiremanRenewalDTO.getId());
        System.out.println("GET" + save.toJSON());
       // wiremanRenewalService.delete(wiremanRenewalDTO.getId());
        System.out.println("PrintPreview: " + wiremanRenewalService.getPrintView(wiremanRenewalDTO.getId()));
        HtmlToPDF.toPDF(wiremanRenewalService.getPrintView(wiremanRenewalDTO.getId()));
        System.out.println("JSON =>" + wiremanRenewalService.getJson(wiremanRenewalDTO.getId()).toJSON());
    }
}
