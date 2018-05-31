package nst.app.model.forms.lb;

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
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;

import java.util.Date;

@Data
@Entity
@Table(name = "wireman_modification_form")
@BatchSize(size = 50)
@ToString(callSuper = true)
public class WiremanModification extends BaseAuditableModel implements Form {

  @JoinColumn(name = "form_id")
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  CommonForm form = new CommonForm(ApplicationType.WIRMODI);

  @Column(name = "permit_no")
  String permitNo;

  @Column(name = "is_name_correction")
  Boolean isNameCorrection;

  @Column(name = "surname")
  String surname;

  @Column(name = "first_name")
  String firstName;

  @Column(name = "middle_name")
  String middleName;

  @Column(name = "birth_date")
  Date birthDate;

  @Column(name = "is_dob_correction")
  Boolean isDOBCorrection;

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