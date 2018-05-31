package nst.app.repo;

import java.util.List;
import nst.app.model.District;
import nst.common.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;

public interface DistrictRepository extends BaseRepository<District> {

    @Query(value = "SELECT * FROM districts order by district_name", nativeQuery = true)
    List<District> findAllByOrderByName();

    List<District> findAllByStateIdOrderByNameAsc(Long stateId);

    @Query(value = "select  * from districts,states where states.id=districts.state_id and state_name=?1 order by district_name", nativeQuery = true)
    List<District> findAllByStateName(String stateName);
}
