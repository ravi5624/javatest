package nst.app.dto.le;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.app.dto.newgen.NGAttachmentDTO;
import nst.common.base.BaseDTO;
import nst.dto.AttachmentDTO;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class AgencyLegalStatusDetailsDTO extends BaseDTO {

    private Long id;

    private String partnerName;

    private List<AttachmentDTO> attachments;


}
