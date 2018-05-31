package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGRepeaterDTO extends NGBaseDTO {
    private String surname;
    private String firstName;
    private String middleName;
    private String applicantName;
    private String birthDate;
    private Integer age;
    private String examType;
    private String prevRollNo;
    private String prevCentre;
    private String prevExamDateMonthYear;
    private String preferredExamCentre;
    private String preferredLangMode;
    public List<NGAttachmentDTO> attachments;
}