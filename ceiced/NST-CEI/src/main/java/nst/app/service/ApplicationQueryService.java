package nst.app.service;

import java.util.List;
import nst.app.common.AppConfig;
import nst.app.dto.ApplicationQueryDTO;
import nst.app.enums.ApplicationType;
import nst.app.helper.ApplicationQueryHelper;
import nst.app.manager.ApplicationQueryManager;
import nst.app.manager.FormManager;
import nst.app.model.forms.le.ApplicationQuery;
import nst.common.base.BaseManager;
import nst.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ApplicationQueryService extends BaseService<ApplicationQuery> {

  @Autowired
  ApplicationQueryManager manager;
  @Autowired
  FormManager formManager;
  @Autowired
  ApplicationQueryHelper helper;
  @Autowired
  AppConfig appConfig;

  @Override
  public BaseManager<ApplicationQuery> getManager() {
    return manager;
  }

  public ApplicationQueryDTO getQuery(Long queryId, Long applicationId) {
    return helper.fromModel(manager.getForApplication(queryId, applicationId));
  }

  public List<ApplicationQueryDTO> getAllQuery(Long applicationId) {
    return helper.fromModel(manager.getAllForApplication(applicationId));
  }

  public ApplicationQuery get(Long queryId, Long applicationId) {
    return manager.getForApplication(queryId, applicationId);
  }

  public ApplicationType applicationType() {
    return null;
  }
}