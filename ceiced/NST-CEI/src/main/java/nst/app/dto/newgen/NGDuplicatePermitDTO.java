package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGDuplicatePermitDTO extends NGBaseDTO {

    private String surname;
    private String firstName;
    private String middleName;
    private String duplicateType;
    private String permitNo;
    private String permitIssueDate;
    private NGAddressDTO parmanentAddress;
    private String mobile;
    private String altMobile;
    private String email;
    public List<NGAttachmentDTO> attachments;
}