package nst.app.dto;

import java.util.List;
import lombok.Data;
import nst.app.enums.QueryStatus;
import nst.common.base.BaseDTO;
import nst.dto.AttachmentDTO;


/**
 * contains all the fields for Application Query 
 *
 */



@Data
public class ApplicationQueryDTO extends BaseDTO {

  Long id;
  Long applicationId;
  String packId;
  QueryStatus queryStatus;
  String queriedOn;
  String repliedOn;
  List<QueryDTO> queryList;

  List<AttachmentDTO> attachments;
}
