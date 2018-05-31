package nst.app.model.forms.le;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;
import nst.common.base.BaseAuditableModel;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.annotations.BatchSize;

@Data
@Entity
@Table(name = "form_query_items")
@BatchSize(size = 50)
@ToString(exclude = "applicationQuery", callSuper = true)
public class Query extends BaseAuditableModel {

  @JoinColumn(name = "application_query_id")
  @ManyToOne
  ApplicationQuery applicationQuery;

  @Column(name = "query_id")
  Long queryId;

  @Column(name = "raise_on")
  Date raiseOn;

  @JsonProperty("raise_by")
  String raiseBy;

  @Column(name = "raise")
  String raise;

  @Column(name = "reply")
  String reply;

  public Query() { }

  public Query(ApplicationQuery applicationQuery) {
    this.applicationQuery = applicationQuery;
  }
}