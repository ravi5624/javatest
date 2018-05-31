package nst.app.model.forms.lb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import lombok.ToString;
import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.model.PortalUser;
import nst.app.model.forms.Experience;
import nst.app.model.forms.Form;
import nst.app.model.forms.le.CommonForm;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;

@Data
@Entity
@Table(name = "wireman_examination_form")
@BatchSize(size = 50)
@ToString(callSuper = true, exclude = "experiences")
public class WiremanExamination extends BaseAuditableModel implements Form {

  @JoinColumn(name = "form_id")
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  CommonForm form = new CommonForm(ApplicationType.WIREXM);

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

  @Column(name = "eligibility")
  private String eligibility;

  @Column(name = "total_experience")
  String totalExperience;

  @JoinColumn(name = "form_id", referencedColumnName = "form_id")
  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  @BatchSize(size = 50)
  @OrderBy("id")
  private List<Experience> experiences = new ArrayList<>();

  @Column(name = "preferred_exam_centre")
  String preferredExamCentre;

  @Column(name = "preferredLangMode")
  String preferredLangMode;


  public Experience myExp(Long expId) {
    if (expId != null) {
      Optional<Experience> first = getExperiences().stream().filter(e -> expId.equals(e.getId()))
          .findFirst();
      if (first.isPresent()) {
        return first.get();
      }
    }
    Experience experience = new Experience(form);
    getExperiences().add(experience);
    return experience;
  }


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