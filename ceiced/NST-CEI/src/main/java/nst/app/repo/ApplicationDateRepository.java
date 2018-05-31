package nst.app.repo;

import java.util.Date;
import nst.app.model.ApplicationDate;
import nst.common.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationDateRepository extends BaseRepository<ApplicationDate> {

  @Query(value = "SELECT * from application_date WHERE application_type = ?1 AND start_date <= ?2 AND (end_date >= ?2 OR end_date is NULL)", nativeQuery = true)
  ApplicationDate loadDate(String applicationType, Date start);
}