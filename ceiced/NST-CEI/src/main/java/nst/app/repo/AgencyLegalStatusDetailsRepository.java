package nst.app.repo;

import nst.app.dto.le.LESafetyGadgetDTO;
import nst.app.dto.le.LETestingInstrumentDTO;
import nst.app.model.forms.Experience;
import nst.app.model.forms.le.AgencyLegalStatusDetails;
import nst.common.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.util.List;

@Repository
public interface AgencyLegalStatusDetailsRepository extends BaseRepository<AgencyLegalStatusDetails> {
}