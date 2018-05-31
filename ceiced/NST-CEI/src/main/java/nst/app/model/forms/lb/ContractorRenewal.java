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
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;

@Data
@Entity
@Table(name = "contractor_renewal_form")
@BatchSize(size = 50)
@ToString(callSuper = true)
public class ContractorRenewal extends BaseAuditableModel implements Form {

  @JoinColumn(name = "form_id")
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  CommonForm form = new CommonForm(ApplicationType.CONREN);

  @Column(name = "applicant_contractor_name")
  String contractorName;

  @Column(name = "contractor_lic_no")
  String contractorLicNo;

  @Column(name = "issue_date")
  Date issueDate;

  @Column(name = "license_expiry_date")
  Date licenseExpiryDate;


  @Column(name = "old_supervisor_name")
  String oldSupervisorName;

  @Column(name = "old_supervisor_birth_date")
  Date oldSupervisorBirthDate;

  @Column(name = "old_supervisor_joinee_date")
  Date oldSupervisorJoineeDate;


  @Column(name = "old_supervisor_leaving_date")
  Date oldSupervisorLeavingDate;

  @Column(name = "is_working_old_supervisor")
  Boolean isWorkingOldSupervisor;

  @Column(name = "new_supervisor_name")
  String newSupervisorName;

  @Column(name = "new_supervisor_birth_date")
  Date newSupervisorBirthDate;

  @Column(name = "new_supervisor_joinee_date")
  Date newSupervisorJoineeDate;

  @Column(name = "is_address_change")
  Boolean isAddressChange;



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