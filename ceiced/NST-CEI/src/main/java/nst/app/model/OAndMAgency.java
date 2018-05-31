package nst.app.model;

import javax.persistence.CascadeType;
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
@Table(name = "agency_o_m")
@BatchSize(size = 50)
public class OAndMAgency extends BaseAuditableModel {

  @JoinColumn(name = "agency_detail_id")
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  AgencyDetail agencyDetail = new AgencyDetail();

  @Column(name = "om_name")
  String name;
}