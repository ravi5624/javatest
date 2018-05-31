package nst.app.model.forms;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;
import nst.app.model.forms.le.CommonForm;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;

@Data
@Entity
@Table(name = "experience")
@BatchSize(size = 50)
@ToString(exclude = "form", callSuper = true)
public class Experience extends BaseAuditableModel {

  public Experience() {
  }

  public Experience(CommonForm form) {
    this.form = form;
  }

  @JoinColumn(name = "form_id")
  @ManyToOne
  CommonForm form;

  @Column(name = "sr_no")
  private String srNo;

  @Column(name = "firm_name")
  private String firmName;

  @Column(name = "from_date", columnDefinition = "DATE")
  private Date fromDate;

  @Column(name = "to_date", columnDefinition = "DATE")
  private Date toDate;

  @Column(name = "supervisor_name")
  private String supervisorName;

  @Column(name = "license_no")
  private String licenseNo;

  @Column(name = "supervisor_permit_no")
  private String supervisorPermitNo;

  @Column(name = "is_current")
  private Boolean isCurrent;

}