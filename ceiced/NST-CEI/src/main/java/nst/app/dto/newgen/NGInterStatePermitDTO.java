package nst.app.dto.newgen;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NGInterStatePermitDTO extends NGBaseDTO {


  private String surname;
  private String firstName;
  private  String middleName;
  private String birthDate;
  private Integer age;
  private String gender;
  private String mobile;
  private String altMobile;
  private String email;
  private String permitNo;
  private String nameAndAddressAuth;
  private Integer passYear;
  private String permitIssueDate;
  private String presentOrgName;
  private String presentOrgAddress;
  private NGAddressDTO parmanentAddress;
  private NGAddressDTO temporaryAddress;
  public List<NGAttachmentDTO> attachments;
}