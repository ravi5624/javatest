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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Data
@Entity
@Table(name = "contractor_license_form")
@BatchSize(size = 50)
@ToString(callSuper = true)
public class ContractorLicense extends BaseAuditableModel implements Form {

  @JoinColumn(name = "form_id")
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  CommonForm form = new CommonForm(ApplicationType.CONLIC);

  @Column(name = "applicant_contractor_name")
  String applicantContractorName;

  @Column(name = "mobile")
  String mobile;

  @Column(name = "email")
  String email;

  @Column(name = "org_type")
  String organizationType;

  @JoinColumn(name = "form_id", referencedColumnName = "form_id")
  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  @BatchSize(size = 50)
  @OrderBy("id")
  private List<OrganizationDetails> organizations = new ArrayList<>();

//  @JoinColumn(name = "form_i_id", referencedColumnName = "form_id")
//  @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
//  @Fetch(FetchMode.JOIN)
  @Transient
  private FormI formI;

  @Column(name = "is_license_granted")
  Boolean isLicenseGranted;

  @Column(name = "contractor_lic_no")
  String contractorLicNo;

  @Column(name = "issue_date")
  Date issueDate;

  @Column(name = "supervisor_name")
  String supervisorName;

  @Column(name = "supervisor_age")
  Integer supervisorAge;

  @Column(name = "permit_no_of_supervisor")
  String permitNoOfSupervisor;

  @Column(name = "supervisor_permit_issue_date")
  Date supervisorPermitIssueDate;

  @Column(name = "supervisor_birth_date")
  Date supervisorBirthDate;

  @Column(name = "contractor_name")
  String contractorName;

  @Column(name = "contractor_license_no")
  String contractorLicenseNo;

  @Column(name = "contractor_from_date")
  Date contractorFromDate;

  @Column(name = "contractor_to_date")
  Date contractorToDate;

  public OrganizationDetails myOrg(Long expId) {
    if (expId != null) {
      Optional<OrganizationDetails> first = getOrganizations().stream()
          .filter(e -> expId.equals(e.getId()))
          .findFirst();
      if (first.isPresent()) {
        return first.get();
      }
    }
    OrganizationDetails organizationDetails = new OrganizationDetails(form);
    getOrganizations().add(organizationDetails);
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