package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGWiremanModificationDTO extends NGBaseDTO {
    private String permitNo;
    Boolean isNameCorrection;
    private String surname;
    private String firstName;
    private String middleName;
    public List<NGAttachmentDTO> attachments;
    private String birthDate;
    private Boolean isDOBCorrection;
}