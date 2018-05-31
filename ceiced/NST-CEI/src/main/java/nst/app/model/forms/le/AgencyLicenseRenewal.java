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
import nst.app.enums.AgencyType;
import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.model.PortalUser;
import nst.app.model.forms.Form;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;


@Data
@Entity
@Table(name = "agency_license_renewal")
@BatchSize(size = 50)
public class AgencyLicenseRenewal extends BaseAuditableModel implements Form {

  @JoinColumn(name = "form_id")
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  CommonForm form = new CommonForm(ApplicationType.ALRENE);

  @Column(name = "agency_type")
  private AgencyType agencyType;

  @Column(name = "agency_auth_no")
  private String agencyAuthNo;

  @Column(name = "issue_date")
  private Date issueDate;

  @Column(name = "expiry_date")
  private Date expiryDate;

  @Column(name = "name_of_agency")
  private String nameOfAgency;

  @Column(name = "email")
  private String email;

  @Column(name = "contact_no")
  private String contactNo;

  @Column(name = "website_url")
  private String websiteUrl;

  @Column(name="is_change_any_details")
  private Boolean isChangeAnyDetails;

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