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
import nst.app.model.forms.Form;
import nst.app.model.forms.OrganizationDetails;
import nst.app.model.forms.le.CommonForm;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;

@Data
@Entity
@Table(name = "contractor_partners_modification_form")
@BatchSize(size = 50)
@ToString(callSuper = true)
public class ContractorPartnersModification extends BaseAuditableModel implements Form {

  @JoinColumn(name = "form_id")
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  CommonForm form = new CommonForm(ApplicationType.CONPARTMODI);

  @Column(name = "applicant_contractor_name")
  String applicantContractorName;

  @Column(name = "contractor_license_no")
  String contractorLicenseNo;

  @Column(name = "license_issue_date")
  Date licenseIssueDate;

  @Column(name = "org_type")
  String organizationType;


  @JoinColumn(name = "form_id", referencedColumnName = "form_id")
  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  @BatchSize(size = 50)
  @OrderBy("id")
  private List<OrganizationDetails> partners = new ArrayList<>();

  public OrganizationDetails myOrg(Long orgId) {
    if (orgId != null) {
      Optional<OrganizationDetails> first = getPartners().stream().filter(e -> orgId.equals(e.getId()))
              .findFirst();
      if (first.isPresent()) {
        return first.get();
      }
    }
    OrganizationDetails organizationDetails = new OrganizationDetails(form);
    getPartners().add(organizationDetails);
    return organizationDetails;
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