package nst.app.repo;

import java.util.List;
import nst.app.model.District;
import nst.app.model.Taluka;
import nst.common.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;

public interface TalukaRepository extends BaseRepository<Taluka> {

    //List<Taluka> findAllByStateIdOrderByNameAsc(Long stateId);

    List<Taluka> findAllByDistrictIdOrderByNameAsc(Long districtId);

    @Query(value = "select  * from talukas,districts where districts.id=talukas.district_id and district_name=?1 order by taluka_name", nativeQuery = true)
    List<Taluka> findAllByDistrictName(String districtName);

}
