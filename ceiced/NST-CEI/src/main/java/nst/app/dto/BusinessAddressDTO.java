package nst.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;

@Data
@JsonInclude(Include.ALWAYS)
public class BusinessAddressDTO extends AddressDTO {

  @Email
  @Size(max = 100)
  String businessEmail;

  @Size(min = 6, max = 10)
  String businessContactNo;

  @Size(max = 100)
  String businessWebsite;

}