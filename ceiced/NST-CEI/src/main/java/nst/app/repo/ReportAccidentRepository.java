package nst.app.repo;

import java.util.List;
import nst.app.common.CEIBaseRepository;
import nst.app.model.forms.le.ReportAccident;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportAccidentRepository extends
    CEIBaseRepository<ReportAccident> {

  @Query(value = "SELECT app.* FROM supervisor_exemption_form app, form_detail form WHERE form.user_id = ?1 AND form.id = app.form_id order by form.id desc", nativeQuery = true)
  List<ReportAccident> findMyApps(long userId);
}