package nst.app.repo;

import java.util.Date;
import nst.app.enums.ApplicationType;
import nst.app.model.ApplicationFees;
import nst.common.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationFeesRepository extends BaseRepository<ApplicationFees> {

  ApplicationFees findByApplicationType(ApplicationType applicationType);

  @Query(value = "SELECT * from application_fees WHERE application_type = ?1 AND start_date <= ?2 AND (end_date >= ?2 OR end_date is NULL)", nativeQuery = true)
  ApplicationFees loadFees(String applicationType, Date start);
}