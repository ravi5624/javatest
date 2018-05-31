package nst;

import nst.app.dto.AddressDTO;
import nst.app.dto.ExperienceDTO;
import nst.app.dto.SupervisorExaminationDTO;
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

public class TestSupervisorExamination extends AbstractTest {


  @Test
  public void test() throws Throwable {
    LoginUserUtil.USER = LoginUserUtil.SUPERVISOR;
    SupervisorExaminationDTO supervisorExaminationDTO = supervisorExaminationService.create();

    File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
    MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
        "image/png", new FileInputStream(fileItem));
    AttachmentDTO   add = attachmentService.add(supervisorExaminationDTO.getId(), multipartFile, "4.1", "school_leaving", "School leaving certificate or matriculation certificate", "1",
        null);

    add = attachmentService.add(supervisorExaminationDTO.getId(), multipartFile, "6.7", "electricty_bill", "Attach electricty bill for address proof or any Govt. valid ID proof", "1",
        null);

    add = attachmentService.add(supervisorExaminationDTO.getId(), multipartFile, "7.7", "electricty_bill", "Attach electricty bill for address proof or any Govt. valid ID proof", "1",
        null);

    add = attachmentService.add(supervisorExaminationDTO.getId(), multipartFile, "12.1", "wireman_permit", "Attach Wireman permit", "1",
        null);

    add = attachmentService.add(supervisorExaminationDTO.getId(), multipartFile, "15.1", "wireman_certificate_and_permit", "WIREMAN CERTIFICATE AND PERMIT", "1",
        null);

    add = attachmentService.add(supervisorExaminationDTO.getId(), multipartFile, "15.2", "form_k_attachment", "form_k_attachment", "1",
        null);

      add = attachmentService.add(supervisorExaminationDTO.getId(), multipartFile, "15.3", "attach_experience_certificate", "Experience Certificate", "1",
          null);

      add = attachmentService.add(supervisorExaminationDTO.getId(), multipartFile, "15.4", "attach_partnership_proof", "Copy of Form “I” verified by Electrical Inspector or his representative", "1",
          null);


      add = attachmentService.add(supervisorExaminationDTO.getId(), multipartFile, "18.1", "passport_photograph", "Attach a pasport size photograph", "1",
          null);

    add = attachmentService.add(supervisorExaminationDTO.getId(), multipartFile, "19.1", "scan_signature", "Attach a scan copy of signature", "1",
        null);


    System.out.println("add = " + add);
    add = attachmentService
        .add(supervisorExaminationDTO.getId(), multipartFile, "Other documents", "other_documents"
        );
    System.out.println("add = " + add);


      supervisorExaminationDTO.setIagree(Boolean.FALSE);
    supervisorExaminationDTO.setFirstName("VIJAY");
    supervisorExaminationDTO.setMiddlename("Parmar");
    supervisorExaminationDTO.setSurname("SN");
    supervisorExaminationDTO.setBirthDate("1995-11-02T02:50:12");
    supervisorExaminationDTO.setAge(55);
    supervisorExaminationDTO.setGender("Male");
    supervisorExaminationDTO.setExamType("Supervisor Mines / Mines General");
    supervisorExaminationDTO.setMobile("1234567890");
    supervisorExaminationDTO.setAltMobileNumber("1234567890");
    supervisorExaminationDTO.setEmail("Wijay@gmail.com");
    supervisorExaminationDTO.setPermitNo("78ddsds9797979");
    supervisorExaminationDTO.setSupExperience("4 Years Practical Experience Out Of Which Minimum 02 Years Experience After Getting Wireman Permit And Experience In Mines Related Work Order");
    supervisorExaminationDTO.setPermitIssueDate("2012-11-02T02:50:12");
    supervisorExaminationDTO.setPreferredExamCentre("Ahmedabad");
    supervisorExaminationDTO.setPreferredLangMode("English");
    supervisorExaminationDTO.setTotalExperience("222222");

    System.out.println("supervisorExaminationDTO = " + supervisorExaminationDTO.toJSON());

    AddressDTO address = new AddressDTO();
    address.setAddressType(AddressType.PERMANENT.getType());
    address.setBuildingName("Arise");
    address.setAddrLine1("Line1");
    address.setAddrLine2("Line2");
    address.setDistrict("Vadodara");
    address.setHouseNo("25");
    address.setPincode("390019");
    address.setState("Gujarat");
    address.setTalukaName("Sankheda");
    address.setVillage("Rampura");

    supervisorExaminationDTO.setParmanentAddress(address);

    address = new AddressDTO();
    address.setAddressType(AddressType.TEMPORARY.getType());
    address.setBuildingName("Arise");
    address.setAddrLine1("Line1");
    address.setAddrLine2("Line2");
    address.setDistrict("Vadodara");
    address.setHouseNo("25");
    address.setPincode("390019");
    address.setState("Gujarat");
    address.setTalukaName("Sankheda");
    address.setVillage("Rampura");

    supervisorExaminationDTO.setTemporaryAddress(address);

    List<ExperienceDTO> experiences  = new ArrayList<ExperienceDTO>();
    ExperienceDTO dto = new ExperienceDTO();
    dto.setWmanContractorLicenceNo("SDASDAS - 1");
    dto.setFromDate("2013-11-02T02:50:12");
    dto.setToDate("2014-11-02T02:50:12");
    dto.setWmanOrgSupFirmName("LT");
    dto.setWmanSupSupervisorPermitNo("78787940406406");
    dto.setIsCurrent("Yes");
    dto.setExam("wireman");
    experiences.add(dto);
    //supervisorExaminationDTO.getExperiences().add(dto);

    dto = new ExperienceDTO();
    dto.setWmanContractorLicenceNo("SDASDAS - 2");
    dto.setFromDate("2012-11-02T02:50:12");
    dto.setToDate("2013-11-02T02:50:12");
    dto.setWmanOrgSupFirmName("T");
    dto.setWmanSupSupervisorPermitNo("78787940406406");
    dto.setIsCurrent("Yes");
    dto.setExam("wireman");
    experiences.add(dto);


    dto = new ExperienceDTO();
    dto.setWmanContractorLicenceNo("SDASDAS - 3");
    dto.setFromDate("2011-11-02T02:50:12");
    dto.setToDate("2012-11-02T02:50:12");
    dto.setWmanOrgSupFirmName("infoT");
    dto.setWmanSupSupervisorPermitNo("78787940406406");
    dto.setExam("wireman");
    dto.setIsCurrent("Yes");
    experiences.add(dto);

    supervisorExaminationDTO.setExperiences(experiences);

    SupervisorExaminationDTO save = supervisorExaminationService.saveForm(supervisorExaminationDTO);
//    System.out.println("save = " + save.toJSON());

    save = supervisorExaminationService.get(supervisorExaminationDTO.getId());
//    System.out.println("GET =>" + save.toJSON());

    System.out.println("JSON =>" + supervisorExaminationService.getJson(supervisorExaminationDTO.getId()).toJSON());
    System.out.println("PrintView =>" + supervisorExaminationService.getPrintView(supervisorExaminationDTO.getId()));
//    HtmlToPDF.toPDF(supervisorExaminationService.getPrintView(supervisorExaminationDTO.getId()));

    //supervisorExaminationService.delete(supervisorExaminationDTO.getId());

  }

}
