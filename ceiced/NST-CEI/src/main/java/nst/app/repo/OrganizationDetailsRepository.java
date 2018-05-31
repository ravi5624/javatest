package nst.app.repo;

import nst.app.common.CEIBaseRepository;
import nst.app.model.forms.OrganizationDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationDetailsRepository extends
    CEIBaseRepository<OrganizationDetails>{

  OrganizationDetails findByIdAndForm_id(Long id, Long formId);

}