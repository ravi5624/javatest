package nst;

import java.io.File;
import java.io.FileInputStream;
import nst.app.dto.SupervisorRenewalDTO;
import nst.dto.AttachmentDTO;
import nst.util.HtmlToPDF;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class TestSupervisorRenewal extends AbstractTest {

    @Test
    public void test() throws Throwable {
        LoginUserUtil.USER = LoginUserUtil.SUPERVISOR;
        SupervisorRenewalDTO supervisorRenewalDTO = supervisorRenewalService.create();

        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));
        AttachmentDTO add = attachmentService
                .add(supervisorRenewalDTO.getId(), multipartFile, "Original License", "original_License"
                );
        System.out.println("add = " + add);
        add = attachmentService.add(supervisorRenewalDTO.getId(), multipartFile, "Other documents", "other_documents"
        );
        System.out.println("add = " + add);

        supervisorRenewalDTO.setFirstName("Vijay");
        supervisorRenewalDTO.setMiddleName("Kantilal");
        supervisorRenewalDTO.setSurname("Parmar");
        supervisorRenewalDTO.setPermitNo("4543545345435");
        supervisorRenewalDTO.setIssueDate("2011-09-02T02:50:12");
        supervisorRenewalDTO.setFromDate("2011-11-02T02:50:12");
        supervisorRenewalDTO.setToDate("2011-11-02T02:50:12");

        SupervisorRenewalDTO save = supervisorRenewalService.saveForm(supervisorRenewalDTO);
        System.out.println("save = " + save.toJSON());
        save = supervisorRenewalService.get(supervisorRenewalDTO.getId());
        System.out.println("GET" + save.toJSON());

        //supervisorRenewalService.delete(supervisorRenewalDTO.getId());

        System.out.println("JSON =>"+supervisorRenewalService.getJson(supervisorRenewalDTO.getId()).toJSON());

        System.out.println("PrintView =>" + supervisorRenewalService.getPrintView(supervisorRenewalDTO.getId()));
        HtmlToPDF.toPDF(supervisorRenewalService.getPrintView(supervisorRenewalDTO.getId()));

    }
}