package nst;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import nst.app.dto.DuplicatePermitDTO;
import nst.app.enums.AddressType;
import nst.dto.AttachmentDTO;
import nst.util.HtmlToPDF;
import nst.util.LoginUserUtil;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class TestDuplicatePermit  extends AbstractTest {

    @Test
    public void test() throws Throwable {
        LoginUserUtil.USER = LoginUserUtil.CONTRACTOR;
        DuplicatePermitDTO duplicatePermitDTO = duplicatePermitService.create();

        File fileItem = new File(System.getProperty("user.home") + "/Desktop/ceiced.json");
        MultipartFile multipartFile = new MockMultipartFile("fileItem", fileItem.getName(),
                "image/png", new FileInputStream(fileItem));

        AttachmentDTO add = attachmentService.add(duplicatePermitDTO.getId(), multipartFile, "4.1", "permit_photocopy", "Attach Photocopy of Permit", "1",
            null);

        add = attachmentService.add(duplicatePermitDTO.getId(), multipartFile, "8.7", "electricty_bill", "Attach electricty bill for address pfroof or any Govt. valid ID proof", "1",
            null);

        add = attachmentService.add(duplicatePermitDTO.getId(), multipartFile, "9.1", "passport_photograph", "Attach Passport size Photograph", "1",
            null);

        add = attachmentService.add(duplicatePermitDTO.getId(), multipartFile, "9.2", "signature", "Signature", "1",
            null);

        add = attachmentService.add(duplicatePermitDTO.getId(), multipartFile, "9.3", "attach_affidavit", "Attach Affidavit", "1",
            null);
        duplicatePermitDTO.setFirstName("FN");
        duplicatePermitDTO.setMiddleName("MN");
        duplicatePermitDTO.setSurname("SN");
        duplicatePermitDTO.setPermitNo("4543545345435");
        duplicatePermitDTO.setPermitIssueDate("2017-11-02T02:50:12");
        List<String> strings = Stream.<String>builder().add("Nascent").add("Info").add("Ahmedavad").build().collect(Collectors.toList());
        duplicatePermitDTO.setDuplicateType(strings);
        duplicatePermitDTO.setMobile("1234567890");
        duplicatePermitDTO.setAltMobile("1234567890");
        duplicatePermitDTO.setEmail("wijay10789@gmail.com");
        System.out.println("duplicatePermitDTO = " + duplicatePermitDTO.toJSON());
        duplicatePermitDTO.setParmanentAddress(this.getAddressDTOByAddressType(AddressType.PERMANENT));

        DuplicatePermitDTO save = duplicatePermitService.saveForm(duplicatePermitDTO);
        System.out.println("save = " + save.toJSON());
        save = duplicatePermitService.get(duplicatePermitDTO.getId());
        System.out.println("GET" + save.toJSON());
        System.out.println("PrintView =>" + duplicatePermitService.getPrintView(duplicatePermitDTO.getId()));
        HtmlToPDF.toPDF(duplicatePermitService.getPrintView(duplicatePermitDTO.getId()));
        System.out.println("JSON =>" + duplicatePermitService.getJson(duplicatePermitDTO.getId()).toJSON());
    }
}