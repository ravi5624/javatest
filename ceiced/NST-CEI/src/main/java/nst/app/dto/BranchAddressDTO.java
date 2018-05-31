package nst.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;

@Data
@JsonInclude(Include.ALWAYS)
public class BranchAddressDTO extends AddressDTO {

  @Email
  @Size(max = 100)
  String branchEmail;

  @Size(min = 6, max = 10)
  String branchContactNo;

  @Size(max = 100)
  String branchWebsite;

}