package nst.app.repo;

import nst.app.enums.AgencyType;
import nst.app.model.AgencyDetail;
import nst.app.model.PortalUser;
import nst.common.base.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AgencyDetailRepository extends BaseRepository<AgencyDetail> {


  List<AgencyDetail> findByAgencyTypeAndExpiryDateGreaterThanAndNameIgnoreCaseLike(AgencyType agencyType, Date expiryDate, String name, Pageable pagination);

  List<AgencyDetail> findByExpiryDateGreaterThanAndNameIgnoreCaseLike(Date date, String name, Pageable pagination);

  AgencyDetail getByUser(PortalUser user);

  List<AgencyDetail> findByAgencyTypeAndAgencyAuthNoIgnoreCaseLike(AgencyType agencyType, String agencyAuthNo);
}