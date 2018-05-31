package nst.app.repo;

import nst.app.common.CEIBaseRepository;
import nst.app.model.forms.lb.ContractorLicense;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorLicenseRepository extends
    CEIBaseRepository<ContractorLicense> {
}