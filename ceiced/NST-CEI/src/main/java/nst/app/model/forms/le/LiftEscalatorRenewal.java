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

/*
* Attach Original License of Lift/Escalator
* Attach relevant documents for renewal (If more than two attach in zip)
* Renewal_License_Lift_Escalator.xlsx
*/
@Data
@Entity
@Table(name = "lift_esclalator_renewal")
@BatchSize(size = 50)
public class LiftEscalatorRenewal extends BaseAuditableModel implements Form {

  @JoinColumn(name = "form_id")
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  CommonForm form = new CommonForm(ApplicationType.LERL);

  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  LiftEscalatorType liftEscalatorType;

  @Column(name = "license_number")
  String licenseNumber;

  @Column(name = "license_issue_date")
  Date licenseIssueDate;

  @Column(name = "license_expiry_date")
  Date licenseExpiryDate;

  @Column(name = "licensee_full_name")
  String licenseeFullName;

  @Column(name = "is_address_or_owner_change")
  Boolean isAddressOrOwnerChange;

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