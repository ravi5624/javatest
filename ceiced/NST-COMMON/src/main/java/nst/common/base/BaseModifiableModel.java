package nst.common.base;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Data
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseModifiableModel extends BaseModel {

  private static final long serialVersionUID = -6187061292150106087L;

  @CreatedDate
  @Column(name = "created_date")
  private Date createdDate;

  @LastModifiedBy
  @Column(name = "updated_by")
  private String updatedBy;

  @LastModifiedDate
  @Column(name = "updated_date")
  private Date updatedDate;
}
