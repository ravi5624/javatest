package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGFormIDTO extends NGBaseDTO {
    private String technicalContractorName;
    private String issueDate;
    private String contractorLicNo;
    private NGAddressDTO officeAddress;
    private List<NGFormIEmployerDTO> employer;
    private List<NGAttachmentDTO> attachments;
}