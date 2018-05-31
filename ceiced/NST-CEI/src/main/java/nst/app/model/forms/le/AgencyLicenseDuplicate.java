package nst.app.model.forms.le;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.model.PortalUser;
import nst.app.model.forms.Form;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;

/**
 * created by Vijay Parmar 16 dec 2017
 *
 */
@Data
@Entity
@Table(name = "agency_license_duplicate")
@BatchSize(size = 50)
public class AgencyLicenseDuplicate extends BaseAuditableModel implements Form {

  @JoinColumn(name = "form_id")
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  CommonForm form = new CommonForm(ApplicationType.ALDUPL);

  @Column(name = "agency_type")
  private String agencyType;


  @Column(name = "agency_auth_no")
  String agencyAuthNo;

  @Column(name = "issue_date")
  Date issueDate;

  @Column(name = "expiry_date")
  Date expiryDate;

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