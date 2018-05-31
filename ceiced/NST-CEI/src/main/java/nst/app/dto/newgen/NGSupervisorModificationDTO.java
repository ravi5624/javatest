package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;
import nst.kernal.validation.PastDate;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGSupervisorModificationDTO extends NGBaseDTO {
    private String permitNo;
    private Boolean isNameCorrection;
    private String surname;
    private String firstName;
    private String middleName;
    private Boolean isAddressChange;
    private Boolean isDOBCorrection;
    public List<NGAttachmentDTO> attachments;
    private String birthDate;
}