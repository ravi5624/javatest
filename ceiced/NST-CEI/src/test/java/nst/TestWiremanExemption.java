package nst;

import nst.app.dto.AddressDTO;
import nst.app.dto.WiremanExemptionDTO;
import nst.app.enums.AddressType;
import nst.common.base.BaseModelDTO;
import nst.common.error.AppException;
import nst.dto.AttachmentDTO;
import nst.dto.TransactionDTO;
import nst.util.HtmlToPDF;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class TestWiremanExemption extends AbstractTest {

    @Test
    public void test() throws Throwable {
        LoginUserUtil.USER = LoginUserUtil.WIREMAN;
        WiremanExemptionDTO exemptionDTO = wiremanExemptionService.create();

        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));
        AttachmentDTO add = attachmentService
                .add(exemptionDTO.getId(), multipartFile, "4.1", "school_leaving", "School leaving certificate or matriculation certificate", "1",
                    null);
        System.out.println("add = " + add);

        add = attachmentService.add(exemptionDTO.getId(), multipartFile, "6.7", "electricity_bill", "Attach electricity bill for address proof or any Govt. valid ID proof", "1",
            null);

        add = attachmentService.add(exemptionDTO.getId(), multipartFile, "7.7", "electricity_bill", "Attach electricity bill for address proof or any Govt. valid ID proof", "1",
            null);

        add = attachmentService.add(exemptionDTO.getId(), multipartFile, "11.2", "EST_certificate", "Attach certificate of EST (Electrical Service Technician)", "1",
            null);

        add = attachmentService.add(exemptionDTO.getId(), multipartFile, "12.2", "affidavit", "Attach affidavit if not from Gujarat(Stamp Paper Of 20 rs.)", "1",
            null);

        add = attachmentService.add(exemptionDTO.getId(), multipartFile, "13.1", "passport_photograph", "Attach a passport size photograph", "1",
            null);

        add = attachmentService.add(exemptionDTO.getId(), multipartFile, "14.1", "scan_signature", "Attach a scan copy of signature", "1",
            null);

        System.out.println("add = " + add);

        exemptionDTO.setIagree(Boolean.FALSE);
        exemptionDTO.setFirstName("FN");
        exemptionDTO.setMiddleName("MN");
        exemptionDTO.setSurname("SN");
        exemptionDTO.setBirthDate("2017-12-01T00:00:00");
        exemptionDTO.setAge(44);
        exemptionDTO.setGender("Male");
        exemptionDTO.setMobile("7878787878");
        exemptionDTO.setAltMobileNumber("1234567890");
        exemptionDTO.setEmail("Wijay@gmail.com");
        exemptionDTO.setTechnicalQualification("MCA");
        exemptionDTO.setPassYear(2013);
        exemptionDTO.setQualificationState("GUJARAT");
        exemptionDTO.setTotalExperience("1");


        System.out.println("exemptionDTO = " + exemptionDTO.toJSON());
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

        exemptionDTO.setParmanentAddress(address);

        address = new AddressDTO();
        address.setBuildingName("Arise");
        address.setAddrLine1("Line1");
        address.setAddrLine2("Line2");
        address.setDistrict("Vadodara");
        address.setHouseNo("25");
        address.setPincode("390019");
        address.setState("Gujarat");
        address.setTalukaName("Sankheda");
        address.setVillage("Rampura");
        address.setAddressType(AddressType.TEMPORARY.getType());

        exemptionDTO.setTemporaryAddress(address);

        WiremanExemptionDTO save = wiremanExemptionService.saveForm(exemptionDTO);
        System.out.println("save = " + save.toJSON());
        System.out.println("PrintView =>" + wiremanExemptionService.getPrintView(exemptionDTO.getId()));
        HtmlToPDF.toPDF(wiremanExemptionService.getPrintView(exemptionDTO.getId()));
        save = wiremanExemptionService.get(exemptionDTO.getId());
        System.out.println("GET =>" + save.toJSON());
        wiremanExemptionService.delete(exemptionDTO.getId());


    }
    @Test
    public void testFlow() {
        try {
            LoginUserUtil.USER = LoginUserUtil.WIREMAN;

            System.out.println("======================================================");
            WiremanExemptionDTO dto = wiremanExemptionService.create();
            wiremanExemptionService.saveForm(dto);
            System.out.println("dto.getId() = " + dto.getId());

            File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
            MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                    "image/png", new FileInputStream(fileItem));
            AttachmentDTO add = attachmentService
                    .add(dto.getId(), multipartFile, "Original License", "original_License");
            System.out.println("add = " + add);
            add = attachmentService.add(dto.getId(), multipartFile, "Other documents", "other_documents");
            System.out.println("add = " + add);

            attachmentService.delete(dto.getId(), add.getId());

            BaseModelDTO baseModelDTO = wiremanExemptionService.get(dto.getId());
            System.out.println("GET = " + baseModelDTO.toJSON());

            List myForms = wiremanExemptionService.getMyForms();
            System.out.println("myForms = " + myForms.size());

            TransactionDTO processPayment = wiremanExemptionService.processPayment(dto.getId());
            try {
                wiremanExemptionService.submitForm(dto.getId());
            } catch (AppException throwable) {
                System.out.println("throwable = " + throwable);
            }

            formService.postPayment(processPayment.getTransactionId(), "SUCCESS");

            wiremanExemptionService.submitForm(dto.getId());

            //      System.out.println("baseModelDTO = " + wiremanExaminationService.get(dto.getId()));

            //wiremanExaminationService.delete(dto.getId());

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
