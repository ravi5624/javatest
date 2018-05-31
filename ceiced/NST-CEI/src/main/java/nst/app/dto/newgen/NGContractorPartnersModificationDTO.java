package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGContractorPartnersModificationDTO extends NGBaseDTO {
    private String surname;
    private String firstName;
    private String middleName;
    private String contractorName;
    private String contractorLicenseNo;
    private String licenseIssueDate;
    private String organizationType;
    private List<NGOrganizationDetailsDTO> partners;
    @Valid
    @NotNull
    public List<NGAttachmentDTO> attachments;
}