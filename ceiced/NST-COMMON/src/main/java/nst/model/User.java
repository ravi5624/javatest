package nst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import nst.common.base.BaseModel;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "USERS")
public class User extends BaseModel {

  private static final long serialVersionUID = 1L;

  @Column(name = "USER_ID")
  private String userId;
}