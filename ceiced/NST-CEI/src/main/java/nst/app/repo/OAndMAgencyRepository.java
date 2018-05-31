package nst.app.repo;

import nst.app.model.OAndMAgency;
import nst.common.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OAndMAgencyRepository extends BaseRepository<OAndMAgency> {

  @Query(value = "SELECT * from agency_o_m WHERE agency_detail_id = (SELECT id FROM agency_detail WHERE user_id = ?1)", nativeQuery = true)
  OAndMAgency getAgency(long id);

  @Query(value = "SELECT * from agency_o_m WHERE agency_detail_id = (SELECT id FROM agency_detail WHERE form_id = ?1)", nativeQuery = true)
  OAndMAgency getByForm(long formId);
}