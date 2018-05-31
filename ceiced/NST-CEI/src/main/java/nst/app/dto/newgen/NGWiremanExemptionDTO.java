package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGWiremanExemptionDTO extends NGBaseDTO {

  private String surname;
  private String firstName;
  private String middleName;
  private String birthDate;
  private Integer age;
  private String gender;
  private NGAddressDTO parmanentAddress;
  private NGAddressDTO temporaryAddress;
  private String mobile;
  private String altMobileNumber;
  private String email;
  private String technicalQualification;
  private String passYear;
  private String qualificationState;
  public List<NGAttachmentDTO> attachments;
  public Boolean noPermit;
  private String totalExperience;
}