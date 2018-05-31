package nst;


import nst.app.dto.AddressDTO;
import nst.app.dto.EscalatorInstallationDTO;
import nst.app.dto.LEAddressDTO;
import nst.app.enums.AddressType;
import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.model.AgencyDetail;
import nst.app.model.forms.le.ApplicationAttachment;
import nst.app.model.forms.le.ApplicationQuery;
import nst.app.model.forms.le.EscalatorInstallation;
import nst.dto.AttachmentDTO;
import nst.start.CEIApplication;
import nst.util.AllUtil;
import nst.util.HtmlToPDF;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CEIApplication.class, loader = SpringApplicationContextLoader.class)

public class TestEscalatorInstallation extends AbstractTest {

    @Test
    public void getAll() {
        Iterable<EscalatorInstallation> all = escalatorInstallationService.getAll();
    }

    @Test
    public void get() {
        escalatorInstallationService.get(20l);
    }

    @Test
    public void addLifInst() {
        EscalatorInstallation form = new EscalatorInstallation();
//    form.setName("Name");
        form.getForm().setApplicationType(ApplicationType.LIL);
        form.getForm().setFileNumber("FFFNO");
        form.getForm().setUniqueId(AllUtil.getUUID());
        form.getForm().setFileStatus(FileStatus.DRAFT);

        form.getForm().addQuery(new ApplicationQuery(form.getForm()));
        form.getForm().addQuery(new ApplicationQuery(form.getForm()));
        form.getForm().addQuery(new ApplicationQuery(form.getForm()));

        form.getForm().addAttachments(new ApplicationAttachment(form.getForm()));
        form.getForm().addAttachments(new ApplicationAttachment(form.getForm()));
        form.getForm().addAttachments(new ApplicationAttachment(form.getForm()));

        escalatorInstallationService.save(form);
    }



    @Test
    public void test() throws Throwable {

        LoginUserUtil.USER = LoginUserUtil.OWNER_USER;
        EscalatorInstallationDTO escalatorInstallationDTO = escalatorInstallationService.create();
        System.out.println("==============="+escalatorInstallationDTO);

        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));

        AttachmentDTO add = null;
        add = attachmentService.add(escalatorInstallationDTO.getId(), multipartFile, "21.1", "local_proof", "Rajachitti development", "1",
            null);

        add = attachmentService.add(escalatorInstallationDTO.getId(), multipartFile, "22.1", "ownership_proof", "Approval of civilplan from authority", "1",
            null);

        add = attachmentService.add(escalatorInstallationDTO.getId(), multipartFile, "25.1.1", "signature_proof", "Key plan", "1",
            null);

        add = attachmentService.add(escalatorInstallationDTO.getId(), multipartFile, "25.1.2", "lift_drawing", "elevation", "1",
            null);

        add = attachmentService.add(escalatorInstallationDTO.getId(), multipartFile, "25.1.3", "lift_drawing", "floor plan bottom , top and other (indivual)", "1",
            null);

        add = attachmentService.add(escalatorInstallationDTO.getId(), multipartFile, "25.1.4", "lift_drawing", "Partnership Deed / Memorandum of undertaking / articles of association", "1",
            null);

        add = attachmentService.add(escalatorInstallationDTO.getId(), multipartFile, "25.1.5", "lift_drawing", "Sale Deed (if any)", "1",
            null);

        add = attachmentService.add(escalatorInstallationDTO.getId(), multipartFile, "25.2.1", "lift_drawing", "Development aggreement", "1",
            null);

        add = attachmentService.add(escalatorInstallationDTO.getId(), multipartFile, "25.2.2", "lift_drawing", "Assignment deed (if any)", "1",
            null);

        add = attachmentService.add(escalatorInstallationDTO.getId(), multipartFile, "25.2.3", "lift_drawing", "Society registration certificate (if any)", "1",
            null);

        add = attachmentService.add(escalatorInstallationDTO.getId(), multipartFile, "25.2.4", "lift_drawing", "Transfer deed (if any)", "1",
            null);

        add = attachmentService.add(escalatorInstallationDTO.getId(), multipartFile, "25.2.5", "lift_drawing", "Index-2 , 7/12 Index, Template 8(A) , Template 6 & Other", "1",
            null);

        add = attachmentService.add(escalatorInstallationDTO.getId(), multipartFile, "25.2.6", "lift_drawing", " Proof of Sign", "1",
            null);

        add = attachmentService.add(escalatorInstallationDTO.getId(), multipartFile, "25.2.7", "lift_drawing", "Drawing of Lift", "1",
            null);

        add = attachmentService.add(escalatorInstallationDTO.getId(), multipartFile, "25.3", "lift_drawing", "Drawing of Lift", "1",
            null);

        add = attachmentService.add(escalatorInstallationDTO.getId(), multipartFile, "25.4", "lift_drawing", "Drawing of Lift", "1",
            null);

        AgencyDetail agency = new AgencyDetail();

        escalatorInstallationDTO.setEiApplicantName("Sagar Makvana");

        escalatorInstallationDTO.setAgencyId(30l);

        escalatorInstallationDTO.setApplicantEmail("sagar@gmail.com");

        escalatorInstallationDTO.setApplicantMobile("7878282806");

        escalatorInstallationDTO.setIsLocalAgentAppointed("yes");

        escalatorInstallationDTO.setLocalAgentName("kalpesh");

        escalatorInstallationDTO.setLocalAgentEmail("kalpesh@gmail.com");

        escalatorInstallationDTO.setLocalAgentContactNo("789789799");

        escalatorInstallationDTO.setPersonName("Sagar");

        escalatorInstallationDTO.setPersonEmail("Sagar@gmail.com");

        escalatorInstallationDTO.setPersonMobile("7878979879");

        escalatorInstallationDTO.setMakerName("L & T ");

        escalatorInstallationDTO.setMakerEmail("MAKER@GMAIL.COM");

        escalatorInstallationDTO.setMakerMobile("7878979879");

        escalatorInstallationDTO.setEscalatorIdentification("black and white");

        escalatorInstallationDTO.setFromFloor(1);

        escalatorInstallationDTO.setToFloor(4);

        escalatorInstallationDTO.setRatedSpeed(55.55);

        escalatorInstallationDTO.setBalusmadesWidth(55.55);

        escalatorInstallationDTO.setHorizontalDistance(55.55);

        escalatorInstallationDTO.setRatedLoad(633.00);

        escalatorInstallationDTO.setEscalatorPersonCapacity(53);

        escalatorInstallationDTO.setEscalatorAngle(15.00);

        escalatorInstallationDTO.setEscalatorWidth(15.00);

        escalatorInstallationDTO.setVerticalRise(15.00);

        escalatorInstallationDTO.setDescription("This is Description");

        escalatorInstallationDTO.setConstructionDetails("This is Construction Details");
        escalatorInstallationDTO.setCompletionWork("This is Completion Work");


        escalatorInstallationDTO.setCommencementWork("This is Construction Details");
        escalatorInstallationDTO.setEscalatorSiteName("sdsdkj");

        AddressDTO address = new AddressDTO();
        address.setAddressType(AddressType.APPLICANT.getType());
        address.setBuildingName("two");
        address.setVillage("sankheda");
        address.setTalukaName("vadodara");
        address.setState("GUJARAT");
        address.setPincode("390099");
        address.setHouseNo("55");
        address.setDistrict("Vadodara");
        escalatorInstallationDTO.setApplicantAddress(address);


        AddressDTO applicantAddress = escalatorInstallationDTO.getApplicantAddress();
        System.out.println("applicantAddress: "+ applicantAddress);


        address = new AddressDTO();
        address.setAddressType(AddressType.AGENT.getType());
        address.setBuildingName("Arise");
        address.setVillage("sankheda");
        address.setTalukaName("vadodara");
        address.setState("GUJARAT");
        address.setPincode("390019");
        address.setHouseNo("55");
        address.setDistrict("Vadodara");
        escalatorInstallationDTO.setLocalAgentAddress(address);



        LEAddressDTO address1 = new LEAddressDTO();
        address1.setAddressType(AddressType.LIFTSITE.getType());
        address1.setBuildingName("Arise");
        address1.setVillage("sankheda");
        address1.setTalukaName("vadodara");
        address1.setState("GUJARAT");
        address1.setPincode("390019");
        address1.setHouseNo("55");
        address1.setDistrict("Vadodara");
        address1.setSiteName("SiteName");
        address1.setSchemeNo("schema no");
        address1.setFpNo("fpno");
        address1.setRsNo("rsno");
        address1.setSubPlotNo("setSubPlotNo");
        address1.setTenamentNo("setTenamentNo");
        address1.setCitySurveyNo("setCitySurveyNo");

        escalatorInstallationDTO.setLiftSiteAddress(address1);


        address = new AddressDTO();
        address.setAddressType(AddressType.PERSON.getType());
        address.setBuildingName("Arise");
        address.setVillage("sankheda");
        address.setTalukaName("vadodara");
        address.setState("GUJARAT");
        address.setPincode("390019");
        address.setHouseNo("55");
        address.setDistrict("Vadodara");
        escalatorInstallationDTO.setPersonAddress(address);


        address = new AddressDTO();
        address.setAddressType(AddressType.MAKER.getType());
        address.setAddrLine1("Add-1");
        address.setAddrLine2("Add-2");
        address.setAddressLine3("Add-3");

        escalatorInstallationDTO.setMakerAddress(address);

        System.out.println("liftEscalatorDuplicateDTO = " + escalatorInstallationDTO.toJSON());

        EscalatorInstallationDTO save = escalatorInstallationService.saveForm(escalatorInstallationDTO);
        System.out.println("save = " + save.toJSON());

        //save.validateAll();
//
      save = escalatorInstallationService.get(escalatorInstallationDTO.getId());
        System.out.println("GET =>" + save.toJSON());

        System.out.println("JSON =>" + escalatorInstallationService.getJson(escalatorInstallationDTO.getId()).toJSON());
        System.out.println("PrintView =>" + escalatorInstallationService.getPrintView(escalatorInstallationDTO.getId()));
        HtmlToPDF.toPDF(escalatorInstallationService.getPrintView(escalatorInstallationDTO.getId()));

    }

}
