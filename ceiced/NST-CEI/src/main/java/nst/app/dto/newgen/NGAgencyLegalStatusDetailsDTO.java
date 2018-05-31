package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.common.base.BaseDTO;
import nst.dto.AttachmentDTO;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGAgencyLegalStatusDetailsDTO extends BaseDTO {

   Long id;
   String partnerName;
   private List<NGAttachmentDTO> attachments;
}
