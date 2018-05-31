package nst.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.common.base.BaseDTO;

import javax.persistence.Column;

/**
 * contains all related to injured victims
 */


@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class AccidentVictimDTO extends BaseDTO {

    private Long id;

    String name;

    String fatherName;

    String gender;

    String age;

    AddressDTO victimPostalAddress;

    String email;

    String contactNo;
}

