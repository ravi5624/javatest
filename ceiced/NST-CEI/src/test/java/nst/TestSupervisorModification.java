package nst;

import java.io.File;
import java.io.FileInputStream;
import nst.app.dto.SupervisorModificationDTO;
import nst.dto.AttachmentDTO;
import nst.util.HtmlToPDF;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class TestSupervisorModification  extends AbstractTest {


    @Test
    public void test() throws Throwable {
        LoginUserUtil.USER = LoginUserUtil.SUPERVISOR;
            SupervisorModificationDTO supervisorModificationDTO = supervisorModificationService.create();
        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));
        AttachmentDTO add = attachmentService.add(supervisorModificationDTO.getId(), multipartFile, "6.7", "electricty_bill", "Attach electricty bill for address proof or any Govt. valid ID proof", "1",
            null);

        supervisorModificationDTO.setPermitNo("asd789797979");
        supervisorModificationDTO.setIsNameCorrection("No");
        supervisorModificationDTO.setFirstName("Vijay");
        supervisorModificationDTO.setMiddleName("Parmar");
        supervisorModificationDTO.setSurname("SN");

        System.out.println("supervisorModificationDTO = " + supervisorModificationDTO.toJSON());

        SupervisorModificationDTO save = supervisorModificationService.saveForm(supervisorModificationDTO);
        System.out.println("save = " + save.toJSON());
        save = supervisorModificationService.get(supervisorModificationDTO.getId());
        System.out.println("GET =>" + save.toJSON());

       // supervisorModificationService.delete(supervisorModificationDTO.getId());

        System.out.println("JSON =>"+supervisorModificationService.getJson(supervisorModificationDTO.getId()).toJSON());

        System.out.println("PrintView =>" + supervisorModificationService.getPrintView(supervisorModificationDTO.getId()));
        HtmlToPDF.toPDF(supervisorModificationService.getPrintView(supervisorModificationDTO.getId()));

    }

}