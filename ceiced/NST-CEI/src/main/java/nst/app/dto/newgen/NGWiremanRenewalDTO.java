package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGWiremanRenewalDTO extends NGBaseDTO {
    private String surname;
    private String firstName;
    private String middleName;
    private String permitNo;
    private String issueDate;
    private String fromDate;
    private String toDate;
}