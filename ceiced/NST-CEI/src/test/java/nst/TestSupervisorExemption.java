package nst;

import nst.app.dto.AddressDTO;
import nst.app.dto.SupervisorExemptionDTO;
import nst.app.enums.AddressType;
import nst.dto.AttachmentDTO;
import nst.util.HtmlToPDF;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

public class TestSupervisorExemption extends AbstractTest {


    @Test
    public void test() throws Throwable {
        LoginUserUtil.USER = LoginUserUtil.SUPERVISOR;
        SupervisorExemptionDTO supervisorExemptionDto = supervisorExemptionService.create();


        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));
        AttachmentDTO  add = attachmentService.add(supervisorExemptionDto.getId(), multipartFile, "4.1", "school_leaving", "School leaving certificate or matriculation certificate", "1",
            null);

        add = attachmentService.add(supervisorExemptionDto.getId(), multipartFile, "4.1", "school_leaving", "School leaving certificate or matriculation certificate", "1",
            null);

        add = attachmentService.add(supervisorExemptionDto.getId(), multipartFile, "6.7", "electricty_bill", "Attach electricty bill for address proof or any Govt. valid ID proof", "1",
            null);

        add = attachmentService.add(supervisorExemptionDto.getId(), multipartFile, "7.7", "electricty_bill", "Attach electricty bill for address proof or any Govt. valid ID proof", "1",
            null);


        add = attachmentService.add(supervisorExemptionDto.getId(), multipartFile, "11.3", "apprenticeship_certificate", " National apprenticeship certificate (ELE)", "1",
            null);

        add = attachmentService.add(supervisorExemptionDto.getId(), multipartFile, "11.4", "trade_certificate", " National trade certificate (ELE)", "1",
            null);

        add = attachmentService.add(supervisorExemptionDto.getId(), multipartFile, "11.5", "experience_certificate", " Experience certificate for 1 year", "1",
            null);

        add = attachmentService.add(supervisorExemptionDto.getId(), multipartFile, "11.6", "form_k", "form K", "1",
            null);

        add = attachmentService.add(supervisorExemptionDto.getId(), multipartFile, "11.7", "form_i", "Copy of Form “I” verified by authority","1",
            null);

        add = attachmentService.add(supervisorExemptionDto.getId(), multipartFile, "12.3", "affidavit", " Attach affidavit if not from Gujarat ","1",
            null);

        add = attachmentService.add(supervisorExemptionDto.getId(), multipartFile, "12.4", "Permenant_address_proof", "Attach Permenant address proof of the state from which you belong if not from Gujarat","1",
            null);

        add = attachmentService.add(supervisorExemptionDto.getId(), multipartFile, "13.2", "marksheet", "Attach marksheet of all sem if from Other state", "1",
            null);

        add = attachmentService.add(supervisorExemptionDto.getId(), multipartFile, "14.1", "passport_photograph", "Attach a pasport size photograph", "1",
            null);

        add = attachmentService.add(supervisorExemptionDto.getId(), multipartFile, "15.1", "scan_signature", "Attach a scan copy of signature", "1",
            null);

        supervisorExemptionDto.setIagree(Boolean.TRUE);
        supervisorExemptionDto.setFirstName("FN");
        supervisorExemptionDto.setMiddleName("MN");
        supervisorExemptionDto.setSurname("SN");
        supervisorExemptionDto.setBirthDate("1995-07-05T02:50:12");
        supervisorExemptionDto.setAge(22);
        supervisorExemptionDto.setGender("Female");
        supervisorExemptionDto.setMobile("1234567890");
        supervisorExemptionDto.setAltMobileNumber("7778889999");
        supervisorExemptionDto.setEmail("Wijay1111@gmail.com");
        supervisorExemptionDto.setTechnicalQualification("ITI Electrician");
        supervisorExemptionDto.setSupEnrollmentNo("4654646999");
        supervisorExemptionDto.setSupInstituteCollegeName("MS");
        supervisorExemptionDto.setSupCollegeDist("Surat");
        supervisorExemptionDto.setPassYear(2013);
        supervisorExemptionDto.setQualificationState("GUJARAT");
        supervisorExemptionDto.setSupUniversityName("MS UNI");
        System.out.println("supervisorExemptionDto = " + supervisorExemptionDto.toJSON());

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

        supervisorExemptionDto.setParmanentAddress(address);

        AddressDTO address2 = new AddressDTO();
        address2.setAddressType(AddressType.TEMPORARY.getType());
        address2.setBuildingName("shapath4");
        address2.setAddrLine1("Line12");
        address2.setAddrLine2("Line24");
        address2.setDistrict("Vadodara3");
        address2.setHouseNo("26");
        address2.setPincode("390020");
        address2.setState("Gujarat");
        address2.setTalukaName("Sankheda");
        address2.setVillage("Rampura");

        supervisorExemptionDto.setTemporaryAddress(address2);

        System.out.println("wiremanRenewalDTO = " + supervisorExemptionDto.toJSON());

        SupervisorExemptionDTO save = supervisorExemptionService.saveForm(supervisorExemptionDto);
        System.out.println("save = " + save.toJSON());
        save = supervisorExemptionService.get(supervisorExemptionDto.getId());
        System.out.println("GET =>" + save.toJSON());
        
        //supervisorExemptionService.delete(supervisorExemptionDto.getId());
        System.out.println("JSON =>" + supervisorExemptionService.getJson(supervisorExemptionDto.getId()).toJSON());


        System.out.println("PrintView =>" + supervisorExemptionService.getPrintView(supervisorExemptionDto.getId()));
        HtmlToPDF.toPDF(supervisorExemptionService.getPrintView(supervisorExemptionDto.getId()));
    }
}
