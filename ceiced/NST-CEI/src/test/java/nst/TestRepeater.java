package nst;

import nst.app.dto.RepeaterDTO;
import nst.dto.AttachmentDTO;
import nst.util.HtmlToPDF;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

public class TestRepeater extends AbstractTest {


    @Test
    public void test() throws Throwable {
        LoginUserUtil.USER = LoginUserUtil.CONTRACTOR;
        RepeaterDTO testRepeater = repeaterService.create();
        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));
        AttachmentDTO add = attachmentService.add(testRepeater.getId(), multipartFile, "11.1", "passport_photograph", "Attach Passport size Photograph", "1",
            null);

        add = attachmentService.add(testRepeater.getId(), multipartFile, "2.1", "school_leaving", " School leaving certificate or matriculation certificate", "1",
            null);

        add = attachmentService.add(testRepeater.getId(), multipartFile, "12.1", "scan_signature", "Attach a scan copy of signature", "1",
            null);

        testRepeater.setIagree(Boolean.FALSE);
        testRepeater.setSurname("SN");
        testRepeater.setFirstName("Vijay");
        testRepeater.setMiddleName("Parmar");
        testRepeater.setAge(18);
        testRepeater.setBirthDate("1990-08-02T02:50:12");
        testRepeater.setExamType("Wireman");
        testRepeater.setPrevRollNo("4545646666");
        testRepeater.setPrevCentre("Ahmedabad");
        testRepeater.setPrevExamDateMonthYear("1990-08-02T02:50:12");
        testRepeater.setPreferredLangMode("English");
        testRepeater.setPreferredExamCentre("Surat");
        System.out.println("testRepeater = " + testRepeater.toJSON());
        RepeaterDTO save = repeaterService.saveForm(testRepeater);
        System.out.println("save = " + save.toJSON());
        save = repeaterService.get(testRepeater.getId());
        System.out.println("GET =>" + save.toJSON());
        //repeaterService.delete(testRepeater.getId());
        System.out.println("JSON =>" + repeaterService.getJson(save.getId()).toJSON());

        System.out.println("PrintView =>" + repeaterService.getPrintView(testRepeater.getId()));
        HtmlToPDF.toPDF(repeaterService.getPrintView(testRepeater.getId()));
    }

}