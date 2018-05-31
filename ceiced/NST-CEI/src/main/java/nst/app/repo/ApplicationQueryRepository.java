package nst.app.repo;

import java.util.List;
import nst.app.enums.QueryStatus;
import nst.app.model.forms.le.ApplicationQuery;
import nst.common.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationQueryRepository extends BaseRepository<ApplicationQuery> {

  ApplicationQuery findByIdAndForm_Id(Long fileId, Long form_id);

  @Query(value = "SELECT qu.* FROM form_queries qu WHERE form_id = ?1 order by id desc limit 1", nativeQuery = true)
  ApplicationQuery getLatestQuery(Long formId);

  List<ApplicationQuery> findByForm_Id(Long form_id);

  List<ApplicationQuery> findByForm_IdAndQueryStatus(Long form_id, QueryStatus queryStatus);
}