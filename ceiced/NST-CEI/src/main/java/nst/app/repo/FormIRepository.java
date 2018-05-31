package nst.app.repo;

import nst.app.common.CEIBaseRepository;
import nst.app.model.forms.lb.FormI;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FormIRepository extends
    CEIBaseRepository<FormI> {

    @Query(value = "SELECT app.* FROM form_i_form app, form_detail form WHERE form.user_id = ?1 AND form.application_type='FORMI' AND form.id = app.form_id order by form.id desc limit 1", nativeQuery = true)
    FormI getLatest(Long userId);
}