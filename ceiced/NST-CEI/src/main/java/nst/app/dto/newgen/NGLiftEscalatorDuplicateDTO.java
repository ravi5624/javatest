package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGLiftEscalatorDuplicateDTO extends NGBaseDTO {

    String liftEscalatorType;

    String licenseNumber;

    String issueDate;

    String expiryDate;

    String reason;

    String reasonIfOther;

    public List<NGAttachmentDTO> attachments;


}
