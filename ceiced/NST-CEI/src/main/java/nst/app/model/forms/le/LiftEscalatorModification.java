package nst.app.model.forms.le;

import lombok.Data;
import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.model.PortalUser;
import nst.app.model.forms.Form;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

/**
 * Modification_Request_Form.xlsx
 */
@Data
@Entity
@Table(name = "lift_escalator_modification")
@BatchSize(size = 50)
public class LiftEscalatorModification extends BaseAuditableModel implements Form {

  @JoinColumn(name = "form_id")
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  CommonForm form = new CommonForm(ApplicationType.LEML);

  @Column(name = "owner_name")
  private String ownerName;

  @Column(name = "owner_email")
  String ownerEmail;

  @Column(name = "owner_contact_no")
  String ownerContactNo;

  //TODO: TYPO mistake in columnName
  @Column(name = "change_buiding_name")
  String changeBuildingName;

  @Column(name = "change_agency")
  Boolean isChangeAgency;

  @Column(name = "installer_person_name")
  String installerPersonName;

  @Column(name = "installer_person_email")
  String installerPersonEmail;

  @Column(name = "installer_person_contact_no")
  String installerPersonContactNo;


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