package nst.app.model.forms.lb;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ApplicantBasicDetail {

  @Column(name = "detail")
  String detail;

/*  @Column(name = "first_name")
  String firstName;

  @Column(name = "middle_name")
  String middleName;

  @Column(name = "birth_date")
  Date birthDate;

  @Column(name = "age")
  Integer age;

  @Column(name = "gender")
  String gender;

  @Column(name = "mobile")
  String mobile;

  @Column(name = "alt_mobile_number")
  String altMobileNumber;

  @Column(name = "email")
  String email;*/

}
