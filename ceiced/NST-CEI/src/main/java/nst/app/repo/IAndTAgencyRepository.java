package nst.app.repo;

import nst.app.model.IAndTAgency;
import nst.common.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAndTAgencyRepository extends BaseRepository<IAndTAgency> {

  @Query(value = "SELECT * from agency_i_t WHERE agency_detail_id = (SELECT id FROM agency_detail WHERE user_id = ?1)", nativeQuery = true)
  IAndTAgency getAgency(long id);

  @Query(value = "SELECT * from agency_i_t WHERE agency_detail_id = (SELECT id FROM agency_detail WHERE form_id = ?1)", nativeQuery = true)
  IAndTAgency getByForm(long formId);
}