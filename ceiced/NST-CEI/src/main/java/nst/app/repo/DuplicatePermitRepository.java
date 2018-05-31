package nst.app.repo;

import nst.app.common.CEIBaseRepository;
import nst.app.model.forms.lb.DuplicatePermit;
import org.springframework.stereotype.Repository;

@Repository
public interface DuplicatePermitRepository extends
    CEIBaseRepository<DuplicatePermit> {
}