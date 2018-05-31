package nst.app.dto;

import lombok.Data;
import nst.common.base.BaseDTO;

@Data
public class DashboardDTO extends BaseDTO {

  Integer applied = 0;
  Integer pending = 0;
  Integer approved = 0;

  public void setApplied(Integer applied) {
    this.applied += applied;
  }

  public void setPending(Integer pending) {
    this.pending += pending;
  }

  public void setApproved(Integer approved) {
    this.approved += approved;
  }
}
