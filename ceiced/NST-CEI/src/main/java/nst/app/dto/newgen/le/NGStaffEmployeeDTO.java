package nst.app.dto.newgen.le;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.app.dto.newgen.NGAttachmentDTO;
import nst.app.dto.newgen.NGBaseDTO;

import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGStaffEmployeeDTO extends NGBaseDTO {


    private String name;

    private String qualification;

    private String designation;

    private String joiningDate;

    private String exitDate;

    private String employee;

    private Integer serialNumber;

    public List<NGAttachmentDTO> attachments;

}