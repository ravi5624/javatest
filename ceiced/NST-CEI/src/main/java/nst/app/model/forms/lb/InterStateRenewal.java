package nst.app.model.forms.lb;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import lombok.ToString;
import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.model.PortalUser;
import nst.app.model.forms.Form;
import nst.app.model.forms.le.CommonForm;
import nst.common.base.BaseModel;
import org.hibernate.annotations.BatchSize;

@Data
@Entity
@Table(name = "inter_state_renewal_form")
@BatchSize(size = 50)
@ToString(callSuper = true)
public class InterStateRenewal extends BaseModel implements Form {

  @JoinColumn(name = "form_id")
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  CommonForm form = new CommonForm();

  @Column(name = "surname")
  String surname;

  @Column(name = "first_name")
  String firstName;

  @Column(name = "middle_name")
  String middleName;

  @Column(name = "birth_Date")
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

  @Column(name = "candidate_type")
  String candidateType;

  @Column(name = "permit_no")
  String permitNo;

  @Column(name = "permit_issue_date")
  Date permitIssueDate;

  @Column(name = "technical_qualification")
  String technicalQualification;

  @Column(name = "sup_enrollment_no")
  String supEnrollmentNo;

  @Column(name = "pass_year")
  Integer passYear;

  @Column(name = "qualification_state")
  String qualificationState;

  @Column(name = "sup_institute_college_name")
  String supInstituteCollegeName;

  @Column(name = "file_no")
  String fileNo;


  @Column(name = "sup_college_dist")
  String supCollegeDist;

  @Column(name = "sup_university_name")
  String supUniversityName;

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