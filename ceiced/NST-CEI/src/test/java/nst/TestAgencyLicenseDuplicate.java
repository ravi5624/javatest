package nst;

import nst.app.dto.AgencyLicenseDuplicateDTO;
import nst.dto.AttachmentDTO;
import nst.util.HtmlToPDF;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

public class TestAgencyLicenseDuplicate  extends AbstractTest {
    @Test
    public void test() throws Throwable {

        LoginUserUtil.USER = LoginUserUtil.EM_AGENCY;
        AgencyLicenseDuplicateDTO agencyLicenseDuplicateDTO = agencyLicenseDuplicateService.create();

        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));
        AttachmentDTO add = attachmentService.add(agencyLicenseDuplicateDTO.getId(), multipartFile,
                       "6.1", "notarized_affidavit", "Notarized Affidavit", "1", null);
        add = attachmentService.add(agencyLicenseDuplicateDTO.getId(), multipartFile,
                "6.2", "duplicate_doc", "Attach other relevant documents for Duplicate (If more than two attach in zip)", "1",
            null);


        add.setDocType("");


        System.out.println("add = " + add);


        agencyLicenseDuplicateDTO.setAgencyType("E & M Agency");
        agencyLicenseDuplicateDTO.setAgencyAuthNo("Abd456466");
        agencyLicenseDuplicateDTO.setIssueDate("2010-09-02T02:50:12");
        agencyLicenseDuplicateDTO.setExpiryDate("2011-09-02T02:50:12");

        agencyLicenseDuplicateDTO.setReason("Stolen");
        agencyLicenseDuplicateDTO.setReasonIfOther("duplicated");

        System.out.println("agencyLicenseDuplicateDTO = " + agencyLicenseDuplicateDTO.toJSON());

        AgencyLicenseDuplicateDTO save = agencyLicenseDuplicateService.saveForm(agencyLicenseDuplicateDTO);
        System.out.println("save = " + save.toJSON());

        //save.validateAll();

        save = agencyLicenseDuplicateService.get(agencyLicenseDuplicateDTO.getId());
        System.out.println("GET =>" + save.toJSON());

        System.out.println("JSON =>" + agencyLicenseDuplicateService.getJson(agencyLicenseDuplicateDTO.getId()).toJSON());
        System.out.println("PrintView =>" + agencyLicenseDuplicateService.getPrintView(agencyLicenseDuplicateDTO.getId()));
        HtmlToPDF.toPDF(agencyLicenseDuplicateService.getPrintView(agencyLicenseDuplicateDTO.getId()));

    }


}
