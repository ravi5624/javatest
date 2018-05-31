package nst.app.repo;

import nst.app.model.forms.le.LESafetyGadget;
import nst.app.model.forms.le.StaffEmployee;
import nst.common.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffEmployeeRepository extends BaseRepository<StaffEmployee> {
}