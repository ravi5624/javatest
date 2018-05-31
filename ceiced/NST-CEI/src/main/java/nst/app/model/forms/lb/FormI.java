package nst.app.model.forms.lb;

import lombok.Data;
import lombok.ToString;
import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.model.PortalUser;
import nst.app.model.forms.Form;
import nst.app.model.forms.FormIEmployer;
import nst.app.model.forms.le.CommonForm;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@Entity
@Table(name = "form_i_form")
@BatchSize(size = 50)
@ToString(callSuper = true)
public class FormI extends BaseAuditableModel implements Form {

  @JoinColumn(name = "form_id")
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  CommonForm form = new CommonForm(ApplicationType.FORMI);

  @Column(name = "technical_contractor_name")
  String technicalContractorName;

  @Column(name = "contractor_lic_no")
  String contractorLicNo;

  @Column(name = "issue_date")
  Date issueDate;

  @JoinColumn(name = "form_id", referencedColumnName = "form_id")
  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  @BatchSize(size = 50)
  @OrderBy("id")
  private List<FormIEmployer> employer = new ArrayList<>();

  public FormIEmployer myEmp(Long orgId) {
    if (orgId != null) {
      Optional<FormIEmployer> first = getEmployer().stream().filter(e -> orgId.equals(e.getId()))
          .findFirst();
      if (first.isPresent()) {
        return first.get();
      }
    }
    FormIEmployer employer = new FormIEmployer(form);
    getEmployer().add(employer);
    return employer;
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