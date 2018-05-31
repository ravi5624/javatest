package nst.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.common.base.BaseDTO;
import nst.dto.AttachmentDTO;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class StaffEmployeeDTO extends BaseDTO {

  Long id;

  private String name;

  private String qualification;

  private String designation;

  private String joiningDate;

  private String exitDate;

  private String employee;

  private Integer serialNumber;

  private List<AttachmentDTO> attachments;

  private Boolean isNew;

}