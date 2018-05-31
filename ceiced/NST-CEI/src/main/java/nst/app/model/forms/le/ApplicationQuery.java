package nst.app.model.forms.le;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;
import nst.app.enums.QueryStatus;
import nst.common.base.BaseModifiableModel;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

@Data
@Entity
@Table(name = "form_queries")
@BatchSize(size = 50)
@ToString(exclude = "form", callSuper = true)
public class ApplicationQuery extends BaseModifiableModel {

  @JoinColumn(name = "form_id")
  @ManyToOne
  CommonForm form;

  @Column(name = "pack_id")
  String packId;

  @Column(name = "replied_on")
  Date repliedOn;

  @Column(name = "query_status")
  @Enumerated(EnumType.STRING)
  QueryStatus queryStatus;

  @OneToMany(mappedBy = "applicationQuery", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  @BatchSize(size = 50)
  @OrderBy("id")
  List<Query> queryList = new ArrayList<>();

  @JoinColumns({
      @JoinColumn(name = "user_key", referencedColumnName = "id"),
      @JoinColumn(name = "form_id", referencedColumnName = "form_id")
  })
  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  @Where(clause = "attachment_type = 'QUERY'")
  @BatchSize(size = 50)
  List<ApplicationAttachment> attachments;

  @Column(name = "remarks")
  String remarks;

  public Query myQuery(Long expId) {
    if (expId != null) {
      Optional<Query> first = getQueryList().stream().filter(e -> expId.equals(e.getId()))
          .findFirst();
      if (first.isPresent()) {
        return first.get();
      }
    }
    Query query = new Query(this);
    getQueryList().add(query);
    return query;
  }

  public ApplicationQuery() {
  }

  public ApplicationQuery(CommonForm commonForm) {
    this.form = commonForm;
  }

  public void setQueryStatus(QueryStatus queryStatus) {
    if (QueryStatus.REPLIED == queryStatus) {
      this.repliedOn = new Date();
    }
    this.queryStatus = queryStatus;
  }

  public List<ApplicationAttachment> getAttachmentFor(String fieldIdentifier) {
    if (attachments == null || StringUtils.isEmpty(fieldIdentifier)) {
      return null;
    }
    return getAttachments().stream().filter(a -> a.isForField(fieldIdentifier))
        .collect(Collectors.toList());
  }
}