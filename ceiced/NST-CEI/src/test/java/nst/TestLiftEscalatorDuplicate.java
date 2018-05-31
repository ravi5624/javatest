package nst;

import nst.app.dto.LiftEscalatorDuplicateDTO;
import nst.dto.AttachmentDTO;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

public class TestLiftEscalatorDuplicate  extends AbstractTest {


    @Test
    public void test() throws Throwable {
        
        LoginUserUtil.USER = LoginUserUtil.OM_AGENCY;
        LiftEscalatorDuplicateDTO liftEscalatorDuplicateDTO = liftEscalatorDuplicateService.create();

        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));
        AttachmentDTO add = attachmentService.add(liftEscalatorDuplicateDTO.getId(), multipartFile, "Notarized Affidavit", "notarized_affidavit","Notarized Affidavit", "1",
            null);
        System.out.println("add = " + add);


        liftEscalatorDuplicateDTO.setLiftEscalatorType("Lift");
        liftEscalatorDuplicateDTO.setLicenseNumber("Abd456466");
        liftEscalatorDuplicateDTO.setIssueDate("2010-09-02T02:50:12");
        liftEscalatorDuplicateDTO.setExpiryDate("2011-09-02T02:50:12");
        liftEscalatorDuplicateDTO.setReason("Stolen");
        liftEscalatorDuplicateDTO.setReasonIfOther("nothing");

        System.out.println("liftEscalatorDuplicateDTO = " + liftEscalatorDuplicateDTO.toJSON());

        LiftEscalatorDuplicateDTO save = liftEscalatorDuplicateService.saveForm(liftEscalatorDuplicateDTO);
        System.out.println("save = " + save.toJSON());

        //save.validateAll();

        save = liftEscalatorDuplicateService.get(liftEscalatorDuplicateDTO.getId());
        System.out.println("GET =>" + save.toJSON());

        System.out.println("JSON =>" + liftEscalatorDuplicateService.getJson(liftEscalatorDuplicateDTO.getId()).toJSON());
        //System.out.println("PrintView =>" + liftEscalatorDuplicateService.getPrintView(liftEscalatorDuplicateDTO.getId()));

    }


}
