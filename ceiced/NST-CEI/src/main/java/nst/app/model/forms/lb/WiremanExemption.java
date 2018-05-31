package nst.app.model.forms.lb;

import lombok.Data;
import lombok.ToString;
import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.model.PortalUser;
import nst.app.model.forms.Form;
import nst.app.model.forms.le.CommonForm;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "wireman_exemption_form")
@BatchSize(size = 50)
@ToString(callSuper = true)
public class WiremanExemption extends BaseAuditableModel implements Form {

  @JoinColumn(name = "form_id")
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  CommonForm form = new CommonForm(ApplicationType.WIREXMP);

  @Column(name = "surname")
  String surname;

  @Column(name = "first_name")
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

  @Column(name = "alt_mobile")
  String altMobile;

  @Column(name = "email")
  String email;

  @Column(name = "technical_qualification")
  String technicalQualification;

  @Column(name = "pass_year")
  Integer passYear;

  @Column(name = "qualification_state")
  String qualificationState;

  @Column(name = "no_permit")
  Boolean noPermit = Boolean.FALSE;


  @Override
  public ApplicationType getApplicationType() {
    return getForm().getApplicationType();
  }

  @Override
  public PortalUser getOwner() {
    return getForm().getUser();
  }

  @Transient
  public Long getApplicationId() {
    return getForm().getId();
  }

  @Override
  public FileStatus getFileStatus() {
    return getForm().getFileStatus();
  }

  @Override
  public String getUniqueId() {
    return getForm().getUniqueId();
  }

}