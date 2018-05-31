package nst.app.model.forms.le;

import lombok.Data;
import lombok.ToString;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "staff_employee")
@BatchSize(size = 50)
@ToString(exclude = "form", callSuper = true)

public class StaffEmployee extends BaseAuditableModel {

  public StaffEmployee() {
  }

  public StaffEmployee(CommonForm form) {
    this.form = form;
  }

  @JoinColumn(name = "form_id")
  @ManyToOne
  CommonForm form;


  @Column(name = "employee")
  private String employee;

  @Column(name = "name")
  private String name;

  @Column(name = "qualification")
  private String qualification;

  @Column(name = "designation")
  private String designation;

  @Column(name = "joining_date", columnDefinition = "DATE")
  private Date joiningDate;

  @Column(name = "exit_date", columnDefinition = "DATE")
  private Date exitDate;

  @Column(name = "serial_number")
  private Integer serialNumber;

  @Column(name = "is_new")
  private Boolean isNew;
}
