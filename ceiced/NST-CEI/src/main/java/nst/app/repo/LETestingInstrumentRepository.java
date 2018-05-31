package nst.app.repo;

import nst.app.dto.le.LESafetyGadgetDTO;
import nst.app.dto.le.LETestingInstrumentDTO;
import nst.app.model.forms.TestingInstrument;
import nst.app.model.forms.le.AgencyLegalStatusDetails;
import nst.app.model.forms.le.LETestingInstrument;
import nst.common.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.util.List;

@Repository
public interface LETestingInstrumentRepository extends BaseRepository<LETestingInstrument> {
}