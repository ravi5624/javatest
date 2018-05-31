package nst;

import nst.app.dto.OperatingLiftDTO;
import nst.app.enums.AddressType;
import nst.dto.AttachmentDTO;
import nst.util.HtmlToPDF;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

public class TestOperatingLift extends AbstractTest {

    @Test
    public void test() throws Throwable {
        LoginUserUtil.USER = LoginUserUtil.OWNER_USER;
        OperatingLiftDTO operatingLiftDTO = operatingLiftService.create();

        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));

        AttachmentDTO add = attachmentService.add(operatingLiftDTO.getId(), multipartFile, "25.1", "local_proof", "Proof of from Local", "2",
            null);

        add = attachmentService.add(operatingLiftDTO.getId(), multipartFile, "25.2", "ownership_proof", "Proof of ownership", "2",
            null);

        add = attachmentService.add(operatingLiftDTO.getId(), multipartFile, "25.3", "signature_proof", "Proof of Sign", "2",
            null);

        add = attachmentService.add(operatingLiftDTO.getId(), multipartFile, "25.4", "lift_drawing", "Drawing of Lift", "2",
            null);

        operatingLiftDTO.setLicenseNumber("L-1");
        operatingLiftDTO.setIssueDate("2017-12-01T00:00:00");
        operatingLiftDTO.setExpiryDate("2017-12-01T00:00:00");

        operatingLiftDTO.setOlApplicantName("Applicant1");
        operatingLiftDTO.setApplicantEmail("Applicant@gmail.com");
        operatingLiftDTO.setApplicantMobile("99927879274");
        operatingLiftDTO.setLocalAgentAppointed("Yes");
        operatingLiftDTO.setLocalAgentName("Agent");
        operatingLiftDTO.setLocalAgentEmail("agent@gmail.com");
        operatingLiftDTO.setLocalAgentContactNo("9998279274");
        operatingLiftDTO.setLiftSiteName("Naroda side");
   /*     operatingLiftDTO.setLiftTownPlanningNo("7878282806");
        operatingLiftDTO.setLiftFpNo("456469ss");
        operatingLiftDTO.setLiftRsNo("78798799");*/

        // operatingLiftDTO.setLiftSubPlotNo("787");

        //operatingLiftDTO.setLiftBlockTenamentNo("79");
        //operatingLiftDTO.setLiftCitySurveyNo("50");
        operatingLiftDTO.setPersonName("Vijay");
        operatingLiftDTO.setPersonEmail("Vijay@gmail.com");
        operatingLiftDTO.setPersonMobile("7878787878");
        operatingLiftDTO.setLiftType("liftType");
        operatingLiftDTO.setLiftSubCategory("1");
        operatingLiftDTO.setOtherCategoryLift("this is other category lift");
        operatingLiftDTO.setMakeLift("Make Lift");
        operatingLiftDTO.setLiftPassengerCapacity(3);
        operatingLiftDTO.setRatedLoad(122.21);
        operatingLiftDTO.setRatedSpeed(112.12);
        operatingLiftDTO.setTotalLiftWeight("500kg");
        operatingLiftDTO.setTotalCounterWeight("133kg");
        operatingLiftDTO.setSuspensionRopeSize("This is Suspension Rope Size");
        operatingLiftDTO.setPitDepth("121");
        operatingLiftDTO.setTravelMeters(12.2);
        operatingLiftDTO.setTravelTime(1232312.0);
        operatingLiftDTO.setBasementFloors(0);
        operatingLiftDTO.setGroundFloors(2);
        operatingLiftDTO.setMezzaineFloors(3);
        operatingLiftDTO.setServedFloors(2);
        operatingLiftDTO.setEntrances(3);
        operatingLiftDTO.setFloorClosed("asdqwe");
        operatingLiftDTO.setDummyFloors(2);
        operatingLiftDTO.setHeadRoomDetail("head room details");

        operatingLiftDTO.setApplicantAddress(this.getAddressDTOByAddressType(AddressType.APPLICANT));
        operatingLiftDTO.setLocalAgentAddress(this.getAddressDTOByAddressType(AddressType.AGENT));
        operatingLiftDTO.setPersonAddress(this.getAddressDTOByAddressType(AddressType.PERSON));
        operatingLiftDTO.setLiftSiteAddress(this.getLEAddressDTOByAddressType(AddressType.APPLICANT));

        OperatingLiftDTO save = operatingLiftService.saveForm(operatingLiftDTO);
        System.out.println("save = " + save.toJSON());

//        System.out.println(operatingLiftService.submitForm(operatingLiftDTO.getId()));

        //save.validateAll();

        save = operatingLiftService.get(operatingLiftDTO.getId());
        System.out.println("GET =>" + save.toJSON());

        System.out.println("JSON =>" + operatingLiftService.getJson(operatingLiftDTO.getId()).toJSON());
          //System.out.println("hello :dfdfdkjf");
          //System.out.println("hello :");
          //System.out.println("hello :");
        System.out.println("PrintView =>" + operatingLiftService.getPrintView(operatingLiftDTO.getId()));
        HtmlToPDF.toPDF(operatingLiftService.getPrintView(operatingLiftDTO.getId()));
    }
}

