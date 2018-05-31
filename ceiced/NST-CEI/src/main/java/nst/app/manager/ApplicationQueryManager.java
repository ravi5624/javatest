package nst.app.manager;

import java.util.List;
import nst.app.enums.QueryStatus;
import nst.app.model.forms.le.ApplicationQuery;
import nst.app.repo.ApplicationQueryRepository;
import nst.common.base.BaseManager;
import nst.common.base.BaseRepository;
import nst.common.error.AppException;
import nst.kernal.SystemConstants.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationQueryManager extends BaseManager<ApplicationQuery> {

  @Autowired
  ApplicationQueryRepository repository;

  @Override
  public BaseRepository<ApplicationQuery> getRepository() {
    return repository;
  }

  public ApplicationQuery getForApplication(Long queryId, Long applicationId) {
    ApplicationQuery query = repository.findByIdAndForm_Id(queryId, applicationId);
    if (query == null) {
      throw AppException.createWithMessageCode(Model.INVALID_REQUEST, Model.INVALID_APPLICATION);
    }
    return query;
  }

  public ApplicationQuery getLatestQuery(Long applicationId) {
    ApplicationQuery query = repository.getLatestQuery(applicationId);
    if (query == null) {
      throw AppException.createWithMessageCode(Model.INVALID_REQUEST, Model.INVALID_APPLICATION);
    }
    return query;
  }

  public List<ApplicationQuery> getAllForApplication(Long applicationId) {
    return repository.findByForm_Id(applicationId);
  }

  public List<ApplicationQuery> getRepliedQueries(Long applicationId) {
    return repository.findByForm_IdAndQueryStatus(applicationId, QueryStatus.REPLIED);
  }
}