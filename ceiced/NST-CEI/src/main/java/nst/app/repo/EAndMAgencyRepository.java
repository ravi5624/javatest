package nst.app.repo;

import nst.app.model.EAndMAgency;
import nst.common.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EAndMAgencyRepository extends BaseRepository<EAndMAgency> {

  @Query(value = "SELECT * from agency_e_m WHERE agency_detail_id = (SELECT id FROM agency_detail WHERE user_id = ?1)", nativeQuery = true)
  EAndMAgency getAgency(long id);

  @Query(value = "SELECT * from agency_e_m WHERE agency_detail_id = (SELECT id FROM agency_detail WHERE form_id = ?1)", nativeQuery = true)
  EAndMAgency getByForm(long formId);
}