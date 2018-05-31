package nst.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;
import nst.common.base.BaseDTO;
import nst.dto.AttachmentDTO;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class OrganizationDetailsDTO extends BaseDTO {

  private Long id;
  private String nameOfPartner;
  private String birthDate;
  private String homeAddress;

  public List<AttachmentDTO> attachments;
}
