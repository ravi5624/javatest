package nst.app.repo;

import nst.app.dto.le.LESafetyGadgetDTO;
import nst.app.dto.le.LETestingInstrumentDTO;
import nst.app.model.forms.SafetyGadget;
import nst.app.model.forms.TestingInstrument;
import nst.app.model.forms.le.AgencyLegalStatusDetails;
import nst.app.model.forms.le.LESafetyGadget;
import nst.common.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.util.List;

@Repository
public interface LESafetyGadgetRepository extends BaseRepository<LESafetyGadget> {
}