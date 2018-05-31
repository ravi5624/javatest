package nst;

import java.io.File;
import java.io.FileInputStream;
import nst.app.dto.ExperienceDTO;
import nst.app.dto.WiremanModificationDTO;
import nst.dto.AttachmentDTO;
import nst.util.HtmlToPDF;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class TestWiremanModification extends AbstractTest {

    @Test
    public void test() throws Throwable {
        LoginUserUtil.USER = LoginUserUtil.WIREMAN;
        WiremanModificationDTO wiremanModificationDTO = wiremanModificationService.create();

        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));
        AttachmentDTO add = attachmentService.add(wiremanModificationDTO.getId(), multipartFile, "6.7", "electricity_bill", "Attach electricity bill for address proof or any Govt. valid ID proof", "1",
            null);


        System.out.println("add = " + add);
        add = attachmentService.add(wiremanModificationDTO.getId(), multipartFile, "Other documents", "other_documents");
        System.out.println("add = " + add);

        wiremanModificationDTO.setFirstName("FN");
        wiremanModificationDTO.setMiddleName("MN");
        wiremanModificationDTO.setSurname("SN");
        wiremanModificationDTO.setPermitNo("4543545345435");
        wiremanModificationDTO.setIsNameCorrection("Yes");

        ExperienceDTO dto = new ExperienceDTO();
        dto.setWmanContractorLicenceNo("SDASDAS - 1");
        dto.setFromDate("12-11-2017");
        dto.setToDate("12-11-2017");
        dto.setId(7l);
        dto.setWmanOrgSupFirmName("L&T");
        dto.setWmanSupSupervisorPermitNo("78787940406406");
        dto.setExam("wireman");
        dto.setIsCurrent("Yes");

        System.out.println("wiremanModificationDTO = " + wiremanModificationDTO.toJSON());

        WiremanModificationDTO save = wiremanModificationService.saveForm(wiremanModificationDTO);

        System.out.println("save = " + save.toJSON());

        save = wiremanModificationService.get(wiremanModificationDTO.getId());

        System.out.println("GET" + save.toJSON());

        // wiremanModificationService.delete(wiremanModificationDTO.getId());

        System.out.println("PrintPreview: " + wiremanModificationService.getPrintView(wiremanModificationDTO.getId()));
        System.out.println("JSON =>" + wiremanModificationService.getJson(wiremanModificationDTO.getId()).toJSON());
        HtmlToPDF.toPDF(wiremanModificationService.getPrintView(wiremanModificationDTO.getId()));

    }
}
