package nst;

import nst.app.dto.AddressDTO;
import nst.app.dto.ExperienceDTO;
import nst.app.dto.WiremanExaminationDTO;
import nst.app.enums.AddressType;
import nst.dto.AttachmentDTO;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

public class TestWiremanExamination extends AbstractTest {

  @Test
  public void test() throws Throwable {
    LoginUserUtil.USER = LoginUserUtil.WIREMAN;
    WiremanExaminationDTO wiremanExaminationDTO = wiremanExaminationService.create();

    File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
    MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
        "image/png", new FileInputStream(fileItem));

    AttachmentDTO add = attachmentService
        .add(wiremanExaminationDTO.getId(), multipartFile, "4.1", "school_leaving",
            "School leaving certificate or matriculation certificate", "1", null);

    add = attachmentService
        .add(wiremanExaminationDTO.getId(), multipartFile, "6.7", "electricty_bill",
            "Attach electricty bill for address proof or any Govt. valid ID proof", "1", null);

    add = attachmentService
        .add(wiremanExaminationDTO.getId(), multipartFile, "7.7", "electricty_bill",
            "Attach electricty bill for address proof or any Govt. valid ID proof", "1", null);

    add = attachmentService
        .add(wiremanExaminationDTO.getId(), multipartFile, "12.1", "form_k", "form K", "1", null);

    add = attachmentService.add(wiremanExaminationDTO.getId(), multipartFile, "12.2", "form_i",
        "Copy of Form “I” verified by authority or his representative", "1", null);

    add = attachmentService
        .add(wiremanExaminationDTO.getId(), multipartFile, "15.1", "passport_photograph",
            "Attach a pasport size photograph", "1", null);

    add = attachmentService
        .add(wiremanExaminationDTO.getId(), multipartFile, "16.1", "scan_signature",
            "Attach a scan copy of signature", "1", null);

      wiremanExaminationDTO.setIagree(Boolean.FALSE);
    wiremanExaminationDTO.setFirstName("FN");
    wiremanExaminationDTO.setMiddleName("MN");
    wiremanExaminationDTO.setSurname("SN");
    wiremanExaminationDTO.setBirthDate("2017-12-01T00:00:00");
    wiremanExaminationDTO.setAge(20);
    wiremanExaminationDTO.setGender("Male");
    wiremanExaminationDTO.setMobile("7878282806");
    wiremanExaminationDTO.setAltMobileNumber("7878282806");
    wiremanExaminationDTO.setEmail("vijay.parmar@email.com");
    wiremanExaminationDTO.setWmanEligibility("MCA");
    wiremanExaminationDTO.setPreferredLangMode("English");
    wiremanExaminationDTO.setPreferredExamCentre("Ahmedabad");
    wiremanExaminationDTO.setTotalExperience("22222");

    System.out.println("wiremanExaminationDTO = " + wiremanExaminationDTO.toJSON());

    AddressDTO address = new AddressDTO();
    address.setAddressType(AddressType.PERMANENT.getType());
    address.setBuildingName("Arise");
    address.setVillage("sankheda");
    address.setTalukaName("vadodara");
    address.setState("GUJARAT");
    address.setPincode("390019");
    address.setHouseNo("55");
    address.setDistrict("Vadodara");
    address.setAddrLine1("line1");
    address.setAddrLine2("Line2");

    wiremanExaminationDTO.setParmanentAddress(address);
    wiremanExaminationDTO.setTemporaryAddress(address);

    address = new AddressDTO();
    address.setAddressType(AddressType.TEMPORARY.getType());
    address.setBuildingName("Arise");
    address.setVillage("sankheda");
    address.setTalukaName("vadodara");
    address.setState("GUJARAT");
    address.setPincode("390019");
    address.setHouseNo("55");
    address.setDistrict("Vadodara");
    address.setAddrLine1("line1");
    address.setAddrLine2("Line2");
    wiremanExaminationDTO.setTemporaryAddress(address);

    ExperienceDTO dto = new ExperienceDTO();
    dto.setFromDate("2011-09-02T02:50:12");
    dto.setToDate("2012-09-02T02:50:12");
    dto.setWmanContractorLicenceNo("WmanContractorLicenceNo");
    dto.setWmanOrgSupFirmName("WmanOrgSupFirmName");
    dto.setWmanSupSupervisorPermitNo("setWmanSupSupervisorPermitNo");
    dto.setIsCurrent("Yes");
    dto.setWmanOrgSupFirmName("setWmanOrgSupFirmName");
    wiremanExaminationDTO.getExperiences().add(dto);

    dto = new ExperienceDTO();
    dto.setIsCurrent("Yes");
    dto.setFromDate("2011-09-02T02:50:12");
    dto.setToDate("2012-09-02T02:50:12");
    dto.setWmanContractorLicenceNo("WmanContractorLicenceNo");
    dto.setWmanOrgSupFirmName("WmanOrgSupFirmName");
    dto.setWmanSupSupervisorPermitNo("setWmanSupSupervisorPermitNo");
    dto.setWmanOrgSupFirmName("setWmanOrgSupFirmName");
    wiremanExaminationDTO.getExperiences().add(dto);

    dto = new ExperienceDTO();
    dto.setFromDate("2013-09-02T02:50:12");
    dto.setIsCurrent("Yes");
    dto.setToDate("2014-09-02T02:50:12");
    dto.setWmanContractorLicenceNo("WmanContractorLicenceNo");
    dto.setWmanOrgSupFirmName("WmanOrgSupFirmName");
    dto.setWmanSupSupervisorPermitNo("setWmanSupSupervisorPermitNo");
    dto.setWmanOrgSupFirmName("setWmanOrgSupFirmName");
    wiremanExaminationDTO.getExperiences().add(dto);

    WiremanExaminationDTO save = wiremanExaminationService.saveForm(wiremanExaminationDTO);
    System.out.println("save = " + save.toJSON());

    //save.validateAll();

    save = wiremanExaminationService.get(wiremanExaminationDTO.getId());
    System.out.println("GET =>" + save.toJSON());

    System.out.println(
        "JSON =>" + wiremanExaminationService.getJson(wiremanExaminationDTO.getId()).toJSON());

  /*  TransactionDTO processPayment = wiremanExaminationService
        .processPayment(wiremanExaminationDTO.getId());
    formService.postPayment(processPayment.getTransactionId(), "SUCCESS");
    wiremanExaminationService.submitForm(wiremanExaminationDTO.getId());*/
/*
    NGQueryRequestDTO queryDTO = new NGQueryRequestDTO();
    queryDTO.getData().setApplicationId(wiremanExaminationDTO.getUniqueId());
    queryDTO.getData().setPackId("fdsfsd");

    List<NGQueryDetailDTO> qrydtoList = new ArrayList<NGQueryDetailDTO>();
    NGQueryDetailDTO q = new NGQueryDetailDTO();
    q.setRaiseOn("2018-01-01 12:12:22");
    q.setQId("1");
    q.setRaiseBy("Vishal");
    q.setQRaise("Query - 1");
    qrydtoList.add(q);

    q = new NGQueryDetailDTO();
    q.setRaiseOn("2018-01-01 12:12:22");
    q.setQId("2");
    q.setRaiseBy("Vishal - 2");
    q.setQRaise("Query - 2");
    qrydtoList.add(q);
    queryDTO.getData().setQueries(qrydtoList);

    newgenService.queryRequested(queryDTO);

    printJSON(portalUserService.getMyApps(null,"",RepoUtil.pagination(1, 50)));

    List<ApplicationQueryDTO> allQuery = queryService.getAllQuery(wiremanExaminationDTO.getId());
    printJSON(allQuery);

    System.out.println("PrintView =>" + wiremanExaminationService.getPrintView(wiremanExaminationDTO.getId()));
      System.out.println("JSON =>" + wiremanExaminationService.getJson(wiremanExaminationDTO.getId()).toJSON());
*/

//    HtmlToPDF.toPDF(wiremanExaminationService.getPrintView(wiremanExaminationDTO.getId()));
  }
}
