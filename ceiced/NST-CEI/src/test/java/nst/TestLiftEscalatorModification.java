package nst;

import nst.app.dto.LiftEscalatorModificationDTO;
import nst.app.enums.AddressType;
import nst.dto.AttachmentDTO;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

public class TestLiftEscalatorModification extends AbstractTest {


    @Test
    public void test() throws Throwable {

        LoginUserUtil.USER = LoginUserUtil.OWNER_USER;
        LiftEscalatorModificationDTO liftEscalatorModificationDTO = liftEscalatorModificationService.create();

        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));
        AttachmentDTO add = attachmentService.add(liftEscalatorModificationDTO.getId(), multipartFile, "1.10", "ownership_proof","Proof of Ownership", "1",
            null);

        add = attachmentService.add(liftEscalatorModificationDTO.getId(), multipartFile, "1.11", "relivan_document","Other relivant document", "1",
            null);

        add = attachmentService.add(liftEscalatorModificationDTO.getId(), multipartFile, "3.10", "relivan_document","Other relivant document", "1",
            null);

        liftEscalatorModificationDTO.setOwnerName("Vijay");
        liftEscalatorModificationDTO.setOwnerEmail("wijay@gmail.com");
        liftEscalatorModificationDTO.setOwnerContactNo("7878575719");
        liftEscalatorModificationDTO.setChangeBuildingName("New Building");
        liftEscalatorModificationDTO.setIsChangeAgency("Yes");
        liftEscalatorModificationDTO.setInstallerPersonName("Vijay");
        liftEscalatorModificationDTO.setInstallerPersonContactNo("02656656");
        liftEscalatorModificationDTO.setInstallerPersonName("Bhargav");
        liftEscalatorModificationDTO.setInstallerPersonEmail("Bhargav@gmail.com");

        liftEscalatorModificationDTO.setOwnerAddress(this.getAddressDTOByAddressType(AddressType.OWNER));
        liftEscalatorModificationDTO.setInstallerPesonAddress(this.getAddressDTOByAddressType(AddressType.LIFTINSTALLER));

        System.out.println("liftEscalatorModificationDTO = " + liftEscalatorModificationDTO.toJSON());

        LiftEscalatorModificationDTO save = liftEscalatorModificationService.saveForm(liftEscalatorModificationDTO);
        System.out.println("save = " + save.toJSON());

        //save.validateAll();

        save = liftEscalatorModificationService.get(liftEscalatorModificationDTO.getId());
        System.out.println("GET =>" + save.toJSON());

        System.out.println("JSON =>" + liftEscalatorModificationService.getJson(liftEscalatorModificationDTO.getId()).toJSON());
        System.out.println("PrintView =>" + liftEscalatorModificationService.getPrintView(liftEscalatorModificationDTO.getId()));

    }


}
