package nst.app.repo;

import nst.app.common.CEIBaseRepository;
import nst.app.model.forms.le.AccidentVictim;
import org.springframework.stereotype.Repository;

@Repository
public interface AccidentVictimRepository extends
    CEIBaseRepository<AccidentVictim> {
}