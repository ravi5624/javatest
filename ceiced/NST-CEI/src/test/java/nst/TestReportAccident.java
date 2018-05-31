package nst;

import nst.app.dto.AccidentVictimDTO;
import nst.app.dto.ReportAccidentDTO;
import nst.app.enums.AddressType;
import nst.dto.AttachmentDTO;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class TestReportAccident extends AbstractTest {



    @Test
    public void test() throws Throwable {
        LoginUserUtil.USER = LoginUserUtil.OWNER_USER;
        ReportAccidentDTO reportAccidentDTO = reportAccidentService.create();

        System.out.println("=============="+reportAccidentDTO);

        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));

        AttachmentDTO add = attachmentService.add(reportAccidentDTO.getId(), multipartFile, "21", "certificate_authorization", "Certificate of Authorization in original", "1",
            null);

        add = attachmentService.add(reportAccidentDTO.getId(), multipartFile, "22", "solvent_certificate", "solvent Certificate", "1",
            null);

        reportAccidentDTO.setAccidentDate(("2017-12-01T00:00:00"));
        reportAccidentDTO.setAccidentTime("10:30 AM");
        reportAccidentDTO.setOwnerName("Vishal");
        reportAccidentDTO.setAccidentType("Fatal");
        reportAccidentDTO.setIsPostmortemperformed("Yes");
        reportAccidentDTO.setIsPersonAuthorized("Yes");
        reportAccidentDTO.setPersonDesgination("Officer");
        reportAccidentDTO.setJobDescription("Gov. Employee");
        reportAccidentDTO.setIsPersonAllowed("Yes");
        reportAccidentDTO.setLicenceNo("7878999asd");
        reportAccidentDTO.setMaintainerContactNo("7878797979");
        reportAccidentDTO.setMaintainerWebsite("www.gmail.com");
        reportAccidentDTO.setInjuriesDescription("hand injuries");
        reportAccidentDTO.setDetailedAccidentCauses("Break Fail");
        reportAccidentDTO.setTakenAction("taken action to save injured person");
        reportAccidentDTO.setIsPoliceConcerned("Yes");
        reportAccidentDTO.setPoliceConcernedDetails("yes police is concerned");
        reportAccidentDTO.setAccidentEvidene("injured to the hand ");
        reportAccidentDTO.setAssistingDescription("This is Assisting Address");
        reportAccidentDTO.setWitnessDescription("This is withness Description");
        reportAccidentDTO.setRemarks("This is Remarks");
        reportAccidentDTO.setMaintainerEmail("maintainer@gmail.com");
        reportAccidentDTO.setAgencyName("my Agency");

//        reportAccidentDTO.setAgencyAddress(this.getAddressDTOByAddressType(AddressType.AGENCY));
        reportAccidentDTO.setBusinessAddress(this.getAddressDTOByAddressType(AddressType.BUSINESS));
        reportAccidentDTO.setAccidentPlace(this.getAddressDTOByAddressType(AddressType.ACCIDENT));

        List<AccidentVictimDTO> victimsList = new ArrayList<>();

        AccidentVictimDTO victim = new AccidentVictimDTO();
        victim.setAge("25");
        victim.setFatherName("Kantilal");
        victim.setName("vijayparmar@gmail.com");
        victim.setGender("Male");
        victim.setEmail("vijayparmar@gmail.com");
        victim.setContactNo("78787282890");

        victim.setVictimPostalAddress(this.getAddressDTOByAddressType(AddressType.VICTIM));
        victimsList.add(victim);

        victim.setVictimPostalAddress(this.getAddressDTOByAddressType(AddressType.VICTIM));
        victimsList.add(victim);

        reportAccidentDTO.setVictims(victimsList);

        ReportAccidentDTO save = reportAccidentService.saveForm(reportAccidentDTO);
        System.out.println("save = " + save.toJSON());

        //System.out.println(reportAccidentService.submitForm(reportAccidentDTO.getId()));
        //save.validateAll();

        save = reportAccidentService.get(reportAccidentDTO.getId());
        System.out.println("GET =>" + save.toJSON());

        System.out.println("JSON =>" + reportAccidentService.getJson(reportAccidentDTO.getId()).toJSON());
        System.out.println("PrintView =>" + reportAccidentService.getPrintView(reportAccidentDTO.getId()));
        //HtmlToPDF.toPDF(reportAccidentService.getPrintView(reportAccidentDTO.getId()));
    }
}

