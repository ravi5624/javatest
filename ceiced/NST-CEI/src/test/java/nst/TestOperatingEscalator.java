package nst;

import nst.app.dto.OperatingEscalatorDTO;
import nst.app.enums.AddressType;
import nst.dto.AttachmentDTO;
import nst.util.HtmlToPDF;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

public class TestOperatingEscalator extends AbstractTest {

    @Test
    public void test() throws Throwable {
        LoginUserUtil.USER = LoginUserUtil.OWNER_USER;
        OperatingEscalatorDTO operatingEscalatorDTO = operatingEscalatorService.create();

        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));

        AttachmentDTO add = attachmentService.add(operatingEscalatorDTO.getId(), multipartFile, "24.1", "local_proof", "Proof of from Local", "1",
            null);

        add = attachmentService.add(operatingEscalatorDTO.getId(), multipartFile, "24.2", "ownership_proof", "Proof of ownership", "1",
            null);

        add = attachmentService.add(operatingEscalatorDTO.getId(), multipartFile, "24.3", "signature_proof", "Proof of Sign", "1",
            null);

        add = attachmentService.add(operatingEscalatorDTO.getId(), multipartFile, "24.4", "lift_drawing", "Drawing of Lift", "1",
            null);

        operatingEscalatorDTO.setOeApplicantName("TestUser");
        operatingEscalatorDTO.setApplicantEmail("test@gmail.com");
        operatingEscalatorDTO.setApplicantMobile("9992378927");
        operatingEscalatorDTO.setIsLocalAgentAppointed("true");
        operatingEscalatorDTO.setLocalAgentName("Agent1");
        operatingEscalatorDTO.setLocalAgentEmail("agent@gmail.com");
        operatingEscalatorDTO.setLocalAgentContactNo("9992389274");
        operatingEscalatorDTO.setEscalatorSiteName("SiteName1");
        operatingEscalatorDTO.setPersonName("Vimla Patel");
        operatingEscalatorDTO.setPersonEmail("VimlaPatel@gmail.com");
        operatingEscalatorDTO.setPersonMobile("7777777777");
        operatingEscalatorDTO.setPersonMobile("7777777777");
        operatingEscalatorDTO.setMakerName("Marker1");
        operatingEscalatorDTO.setMakerMobile("9992389274");
        operatingEscalatorDTO.setMakerEmail("marker@gmail.com");
        operatingEscalatorDTO.setEscalatorIdentification("100 step escalator");
        operatingEscalatorDTO.setFromFloor(1);
        operatingEscalatorDTO.setToFloor(4);
        operatingEscalatorDTO.setEscalatorType("Automated");
        operatingEscalatorDTO.setRatedLoad(233.12);
        operatingEscalatorDTO.setRatedSpeed(12.12);
        operatingEscalatorDTO.setEscalatorPassengerCapacity(2);
        operatingEscalatorDTO.setEscalatorAngle(1.11);
        operatingEscalatorDTO.setEscalatorWidth(50.12);
        operatingEscalatorDTO.setVerticalRise(122.2);
        operatingEscalatorDTO.setDescription("Description");
        operatingEscalatorDTO.setTotalHeadRoom("4");
        operatingEscalatorDTO.setConstructionDetails("Construction Details");
        operatingEscalatorDTO.setApproxReaction("reaction");

        operatingEscalatorDTO.setApplicantAddress(this.getAddressDTOByAddressType(AddressType.APPLICANT));
        operatingEscalatorDTO.setLocalAgentAddress(this.getAddressDTOByAddressType(AddressType.AGENT));
        operatingEscalatorDTO.setPersonAddress(this.getAddressDTOByAddressType(AddressType.PERSON));
        operatingEscalatorDTO.setMakerAddress(this.getAddressDTOByAddressType(AddressType.MAKER));
        operatingEscalatorDTO.setLiftSiteAddress(this.getLEAddressDTOByAddressType(AddressType.LIFTSITE));

        OperatingEscalatorDTO save = operatingEscalatorService.saveForm(operatingEscalatorDTO);
        System.out.println("save = " + save.toJSON());

        System.out.println(operatingEscalatorService.submitForm(operatingEscalatorDTO.getId()));

        //save.validateAll();

        save = operatingEscalatorService.get(operatingEscalatorDTO.getId());
        System.out.println("GET =>" + save.toJSON());

        System.out.println("JSON =>" + operatingEscalatorService.getJson(operatingEscalatorDTO.getId()).toJSON());
        System.out.println("PrintView =>" + operatingEscalatorService.getPrintView(operatingEscalatorDTO.getId()));
        HtmlToPDF.toPDF(operatingEscalatorService.getPrintView(operatingEscalatorDTO.getId()));
    }
}
