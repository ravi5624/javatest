package nst.app.model.forms.le;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import nst.app.enums.PaymentStatus;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;

@Data
@Entity
@Table(name = "form_transaction")
@ToString(callSuper = true, exclude = "form")
@EqualsAndHashCode(callSuper = true, exclude = "form")
@BatchSize(size = 50)
public class Transaction extends BaseAuditableModel {

  private Transaction() {
  }

  public Transaction(CommonForm form) {
    this.form = form;
  }

  @JoinColumn(name = "form_id")
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  CommonForm form;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  PaymentStatus status = PaymentStatus.PENDING;

  @Column(name = "transaction_id")
  String transactionId;

  @Column(name = "amount")
  double amount;

  @Column(name = "response")
  String response;

  @Column(name = "paid_on")
  private Date paidOn;


  @Column(name = "url")
  String url;
}