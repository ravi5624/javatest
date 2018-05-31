package nst.app.dto.newgen;

import java.util.List;
import lombok.Data;
import nst.app.dto.FormIDTO;


@Data
public class NGContractorLicenseDTO extends NGBaseDTO {
    private String surname;
    private String firstName;
    private String middleName;
    private String applicantContractorName;
    private String mobile;
    private String email;
    private NGAddressDTO officeAddress;
    private String organizationType;
    private List<NGOrganizationDetailsDTO> organizations;
    private Boolean isLicenseGranted;
    private String contractorLicNo;
    private String issueDate;
    private String supervisorName;
    private Integer supervisorAge;
    private String permitNoOfSupervisor;
    private String supervisorPermitIssueDate;
    private String supervisorBirthDate;
    private String contractorName;
    private String contractorLicenseNo;
    private String contractorFromDate;
    private String contractorToDate;
    public List<NGAttachmentDTO> attachments;
    private NGFormIDTO formI;
}