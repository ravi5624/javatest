package nst.app.dto.newgen;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.app.dto.AddressDTO;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGAccidentVictimDTO  extends  NGBaseDTO {

    String name;

    String fatherName;

    String gender;

    String age;

    NGAddressDTO victimPostalAddress;

    String email;

    String contactNo;
}