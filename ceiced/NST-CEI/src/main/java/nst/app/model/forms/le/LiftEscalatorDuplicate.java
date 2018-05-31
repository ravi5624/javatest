package nst.app.model.forms.le;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.enums.LiftEscalatorType;
import nst.app.model.PortalUser;
import nst.app.model.forms.Form;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;


/**
 * Ant user can apply for List License Duplicate. Owner can apply from his own earlier List from
 * Lift Applied.
 *
 * Attachments: Notarized Affidavit Attachments: Attach other relevant documents for Duplicate (If
 * more than two attach in zip)
 *
 * 1 - Duplicate_License_Lift_Escalator.xlsx
 */
@Data
@Entity
@Table(name = "lift_esclalator_duplicate")
@BatchSize(size = 50)
public class LiftEscalatorDuplicate extends BaseAuditableModel implements Form {

  @JoinColumn(name = "form_id")
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  CommonForm form = new CommonForm(ApplicationType.LEDL);

  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  LiftEscalatorType liftEscalatorType;

  @Column(name = "license_number")
  String licenseNumber;

  @Column(name = "expiry_date")
  Date expiryDate;

  @Column(name = "issue_date")
  Date issueDate;

  @Column(name = "reason")
  String reason;

  @Column(name = "reason_if_other")
  String reasonIfOther;

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