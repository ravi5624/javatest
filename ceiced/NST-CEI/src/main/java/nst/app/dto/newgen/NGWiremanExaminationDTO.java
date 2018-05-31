package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGWiremanExaminationDTO extends NGBaseDTO {
    private String surname;
    private String firstName;
    private String middleName;
    private String birthDate;
    private Integer age;
    private String gender;
    private NGAddressDTO parmanentAddress;
    private NGAddressDTO temporaryAddress;
    private String mobile;
    private String altMobileNumber;
    private String email;
    private String wmanEligibility;
    private List<NGExperienceDTO> experiences;
    private String preferredExamCenter;
    private String preferredLangMode;
    public List<NGAttachmentDTO> attachments;
    private String totalExperience;
}