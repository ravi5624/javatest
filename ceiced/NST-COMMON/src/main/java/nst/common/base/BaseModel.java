package nst.common.base;

import lombok.Data;
import nst.common.Model;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@MappedSuperclass
@Data
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseModel implements Model {

  @Id
  @Column(name = "id", updatable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "common_seq")
  private Long id;

  //  @Version
//  @Column(name = "VERSION")
  @Transient
  private Integer version;

  public boolean isCreated() {
    return getId() != null;
  }

  public boolean isNew() {
    return getId() == null;
  }
}
