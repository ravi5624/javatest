package nst.app.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Data;
import nst.app.enums.ApplicationType;
import nst.common.base.BaseAuditableModel;
import nst.util.AllUtil;
import org.hibernate.annotations.BatchSize;

@Data
@Entity
@Table(name = "application_fees")
@BatchSize(size = 50)
public class ApplicationFees extends BaseAuditableModel {

  @Column(name = "application_type")
  @Enumerated(value = EnumType.STRING)
  ApplicationType applicationType;

  @Column(name = "fees")
  double fees;

  @Column(name = "start_date")
  Date startDate;

  @Column(name = "end_date")
  Date endDate;

  public ApplicationFees() {
  }

  public ApplicationFees(ApplicationType applicationType, double fees) {
    this.applicationType = applicationType;
    this.fees = fees;
    this.startDate = AllUtil.getDayStart();
    this.endDate = endDate;
  }
}