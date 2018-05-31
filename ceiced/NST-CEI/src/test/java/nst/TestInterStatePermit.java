package nst;

import nst.app.dto.AddressDTO;
import nst.app.dto.InterStatePermitDTO;
import nst.app.enums.AddressType;
import nst.common.base.BaseModelDTO;
import nst.common.error.AppException;
import nst.dto.AttachmentDTO;
import nst.dto.TransactionDTO;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class TestInterStatePermit extends AbstractTest {

  @Test
  public void test() throws Throwable {
    LoginUserUtil.USER = LoginUserUtil.WIREMAN;
    InterStatePermitDTO interStatePermitDTO = interStatePermitService.create();
    
    File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
    MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
        "image/png", new FileInputStream(fileItem));
    AttachmentDTO add = attachmentService.add(interStatePermitDTO.getId(), multipartFile, "Original License", "original_License",            "Label Name", "1",
        null);

    add = attachmentService.add(interStatePermitDTO.getId(), multipartFile, "4.1", "school_leaving", " School leaving certificate or matriculation certificate", "1",
        null);

    add = attachmentService.add(interStatePermitDTO.getId(), multipartFile, "6.7", "electricty_bill", "Attach electricty bill for address proof or any Govt. valid ID proof", "1",
        null);


    add = attachmentService.add(interStatePermitDTO.getId(), multipartFile, "7.7", "electricty_bill", "Attach electricty bill for address proof or any Govt. valid ID proof", "1",
        null);

    add = attachmentService.add(interStatePermitDTO.getId(), multipartFile, "11.4", "permit_copy", "Attach Original Permit Copy", "1",
        null);

    add = attachmentService.add(interStatePermitDTO.getId(), multipartFile, "11.5", "exemption_certificate", "Attach Exemption Certificate Copy If taken Exemption", "1",
        null);

    add = attachmentService.add(interStatePermitDTO.getId(), multipartFile, "11.6", "affidavit", "Attach affidavit if Interstate", "1",
        null);

    add = attachmentService.add(interStatePermitDTO.getId(), multipartFile, "12.2", "appointment_letter", "Attach Appointment Letter", "1",
        null);

    add = attachmentService.add(interStatePermitDTO.getId(), multipartFile, "13.1", "passport_photograph", "Attach Passport size Photograph", "1",
        null);

    add = attachmentService.add(interStatePermitDTO.getId(), multipartFile, "14.1", "scan_signature", "Attach a scan copy of signature", "1",
        null);


    interStatePermitDTO.setIagree(Boolean.FALSE);
    interStatePermitDTO.setFirstName("FN");
    interStatePermitDTO.setMiddleName("MN");
    interStatePermitDTO.setSurname("SN");
    interStatePermitDTO.setBirthDate("2011-09-02T02:50:12");
    interStatePermitDTO.setAge(20);
    interStatePermitDTO.setGender("Male");
    interStatePermitDTO.setMobile("7878282806");
    interStatePermitDTO.setAltMobile("7878282806");
    interStatePermitDTO.setEmail("vijay.parmar@email.com");
    interStatePermitDTO.setPermitNo("65645656400ccc");
    interStatePermitDTO.setPassYear(2012);
    interStatePermitDTO.setPermitIssueDate("2011-09-02T02:50:12");
    interStatePermitDTO.setPresentOrgName("Nascent");
    interStatePermitDTO.setPresentOrgAddress("Near Karnavati club,Ahmedbaad");
    interStatePermitDTO.setNameAndAddressAuth("setNameAndAddressAuth");

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

    interStatePermitDTO.setParmanentAddress(address);

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
    interStatePermitDTO.setTemporaryAddress(address);

    System.out.println("interStatePermitDTO = " + interStatePermitDTO.toJSON());


    InterStatePermitDTO save = interStatePermitService.saveForm(interStatePermitDTO);
    System.out.println("save = " + save.toJSON());

    //save.validateAll();

    save = interStatePermitService.get(interStatePermitDTO.getId());
    System.out.println("GET =>" + save.toJSON());

    System.out.println("JSON =>" + interStatePermitService.getJson(interStatePermitDTO.getId()).toJSON());
    System.out.println("PrintView =>" + interStatePermitService.getPrintView(interStatePermitDTO.getId()));
  }

  @Test
  public void testFlow() {
    try {
      LoginUserUtil.USER = LoginUserUtil.CONTRACTOR;

      InterStatePermitDTO dto = interStatePermitService.create();
      interStatePermitService.saveForm(dto);
      System.out.println("dto.getId() = " + dto.getId());

      File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
      MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
          "image/png", new FileInputStream(fileItem));
      AttachmentDTO add = attachmentService
          .add(dto.getId(), multipartFile, "Original License", "original_License");
      add = attachmentService.add(dto.getId(), multipartFile, "Other documents", "other_documents");

      attachmentService.delete(dto.getId(), add.getId());

      BaseModelDTO baseModelDTO = interStatePermitService.get(dto.getId());
      System.out.println("GET = " + baseModelDTO.toJSON());

      List myForms = interStatePermitService.getMyForms();

      TransactionDTO processPayment = interStatePermitService.processPayment(dto.getId());
      try {
        interStatePermitService.submitForm(dto.getId());
      } catch (AppException throwable) {
        System.out.println("throwable = " + throwable);
      }

      formService.postPayment(processPayment.getTransactionId(), "SUCCESS");

      interStatePermitService.submitForm(dto.getId());

      //      System.out.println("baseModelDTO = " + interStatePermitService.get(dto.getId()));

      //interStatePermitService.delete(dto.getId());

    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }
}
