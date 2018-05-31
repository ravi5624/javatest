package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGSupervisorExaminationDTO extends NGBaseDTO {
    private String surname;
    private String firstname;
    private String middlename;
    private String birthDate;
    private Integer age;
    private String gender;
    private String supExamType;
    NGAddressDTO parmanentAddress;
    NGAddressDTO temporaryAddress;
    private String mobile;
    private String altMobileNumber;
    private String email;
    private String permitNo;
    private String permitIssueDate;
    private String supExperience;
    private List<NGExperienceDTO> experiences;
    private String preferredExamCenter;
    private String preferredLangMode;
    public List<NGAttachmentDTO> attachments;
    private String totalExperience;
    private String whetherWiremanPermit;
}