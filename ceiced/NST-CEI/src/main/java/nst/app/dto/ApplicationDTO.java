package nst.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseModelDTO;
import nst.kernal.SystemConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * contains all the fields of Application like payment status  and has query
 *
 */


@Data
@NoArgsConstructor
public class ApplicationDTO extends BaseModelDTO {

  String applicationName;
  String applicationId;
  String applcantName;
  String pid;
  String fileNo;
  String status;
  String paymentStatus;
  boolean editable;
  boolean hasQuery;
  boolean canDelete;
  List<ApplicationTypeDTO.PartDTO> parts;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SystemConstants.FORMAT_DATE_COMMA)
  Date createdOn;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SystemConstants.FORMAT_DATE_COMMA)
  Date submittedOn;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SystemConstants.FORMAT_DATE_COMMA)
  Date approvedOn;

  public void addPart(ApplicationTypeDTO.PartDTO part) {
    if (this.parts == null) {
      this.parts = new ArrayList<>();
    }
    this.parts.add(part);
  }
}