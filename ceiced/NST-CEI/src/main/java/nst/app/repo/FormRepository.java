package nst.app.repo;

import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.CommonForm;
import nst.common.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends BaseRepository<CommonForm> {

  public CommonForm findByUniqueId(String applicationId);

  /**
   * Returns all instances of the type with the given applicationType and userId.
   *
   * @param applicationType, userId must not be {@literal null}.
   * @return list
   */
  long countByUserAndApplicationType(PortalUser user, ApplicationType applicationType);

  CommonForm findByUserIdAndApplicationType(Long userId, ApplicationType applicationType);

  long countByUserAndApplicationTypeAndFileStatusIn(PortalUser user,
      ApplicationType applicationType, FileStatus[] fileStatuses);
}