package nst.app.repo;

import java.util.List;
import nst.app.common.CEIBaseRepository;
import nst.app.model.forms.le.AgencyLicenseDuplicate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyLicenseDuplicateRepository extends
    CEIBaseRepository<AgencyLicenseDuplicate> {
  @Query(value = "SELECT app.* FROM agency_license_duplicate app, form_detail form WHERE form.user_id = ?1 AND form.id = app.form_id order by form.id desc", nativeQuery = true)
  List<AgencyLicenseDuplicate> findMyApps(long userId);
}