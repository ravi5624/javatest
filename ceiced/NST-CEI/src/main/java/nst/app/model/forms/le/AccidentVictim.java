package nst.app.model.forms.le;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;


@Data
@Entity
@Table(name = "accident_victim")
@BatchSize(size = 50)
@ToString(exclude = "form", callSuper = true)
public class AccidentVictim extends BaseAuditableModel {



  public AccidentVictim() {
  }

  public AccidentVictim(CommonForm form) {
    this.form = form;
  }

  @JoinColumn(name = "form_id")
  @ManyToOne
  CommonForm form;

  @Column(name = "name")
  String name;

  @Column(name = "father_name")
  String fatherName;

  @Column(name = "gender")
  String gender;


  @Column(name = "age")
  String age;

  @Column(name = "email")
  String email;

  @Column(name = "contact_no")
  String contactNo;


}