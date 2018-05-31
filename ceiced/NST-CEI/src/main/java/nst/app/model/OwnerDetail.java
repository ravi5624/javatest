package nst.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;

@Data
@Entity
@Table(name = "owner_detail")
@BatchSize(size = 50)
public class OwnerDetail extends BaseAuditableModel {

  @JoinColumn(name = "user_id")
  @OneToOne
  PortalUser user;

  @Column(name = "owner_name")
  String ownerName;
}
