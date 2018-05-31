package nst;

import nst.app.dto.AddressDTO;
import nst.app.dto.FormIDTO;
import nst.app.dto.FormIEmployerDTO;
import nst.app.enums.AddressType;
import nst.app.enums.DesignationType;
import nst.dto.AttachmentDTO;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class TestFormI extends AbstractTest {


    @Test
    public void test() throws Throwable {
      LoginUserUtil.USER = LoginUserUtil.CONTRACTOR;

        FormIDTO formIDTO = formIService.create();

        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));
        AttachmentDTO add = attachmentService.add(formIDTO.getId(), multipartFile, "3.1", "supervisor_permit_letter");
        add = attachmentService.add(formIDTO.getId(), multipartFile, "3.2", "declaration");
        add = attachmentService.add(formIDTO.getId(), multipartFile, "3.3", "supervisor_appointment_letter");
        add = attachmentService.add(formIDTO.getId(), multipartFile, "3.4", "school_leaving_ertificate");
        add = attachmentService.add(formIDTO.getId(), multipartFile, "3.5", "supervisor_permit");
        add = attachmentService.add(formIDTO.getId(), multipartFile, "3.6", "supervisor_certificate");
        add = attachmentService.add(formIDTO.getId(), multipartFile, "3.7", "supervisor_experience_letter");
        add = attachmentService.add(formIDTO.getId(), multipartFile, "3.8", "Supervisor_previous_contractor_FormI");
        add = attachmentService.add(formIDTO.getId(), multipartFile, "3.9", "Other");

        formIDTO.setTechnicalContractorName("TechnicalContractorName");
        formIDTO.setIssueDate("2011-11-02T02:50:12");
        formIDTO.setContractorLicNo("ContractorLicense");

        FormIEmployerDTO emp =new FormIEmployerDTO();

        emp.setIsInactive("No");
        emp.setJoiningDate("2011-11-02T02:50:12");
        emp.setLeavingDate("2015-11-02T02:50:12");
        emp.setPermitNo("5454546");
        emp.setName("Virah");
        emp.setDesignation(DesignationType.SUPERVISOR);
        emp.setOtherAttachmentName("Other attachment name 1");


      List<FormIEmployerDTO> employers = new ArrayList<>();
        employers.add(emp);

        emp =new FormIEmployerDTO();

        emp.setIsInactive("No");
        emp.setJoiningDate("2011-11-02T02:50:12");
        emp.setLeavingDate("2015-11-02T02:50:12");
        emp.setPermitNo("5454546s");
        emp.setName("Virah2");
        emp.setDesignation(DesignationType.WIREMAN);
        emp.setOtherAttachmentName("Other attachment name");

        employers.add(emp);

        formIDTO.setEmployer(employers);

        AddressDTO address = new AddressDTO();
        address.setAddressType(AddressType.OFFICE.getType());
        address.setHouseNo("25");
        address.setBuildingName("Arise");
        address.setState("Gujarat");
        address.setDistrict("Vadodara");
        address.setTalukaName("Sankheda");
        address.setVillage("Sankheda");
        address.setPincode("390019");

        formIDTO.setOfficeAddress(address);
        System.out.println("formIDTO = " + formIDTO.toJSON());

        FormIDTO save = formIService.saveForm(formIDTO);
        System.out.println("save = " + save.toJSON());
        save = formIService.get(formIDTO.getId());
        System.out.println("GET =>" + save.toJSON());
        //formIService.delete(formIDTO.getId());
        System.out.println("PrintView =>" + formIService.getPrintView(formIDTO.getId()));
       // HtmlToPDF.toPDF(formIService.getPrintView(formIDTO.getId()));
        System.out.println("JSON =>" + formIService.getJson(formIDTO.getId()).toJSON());
    }

}
