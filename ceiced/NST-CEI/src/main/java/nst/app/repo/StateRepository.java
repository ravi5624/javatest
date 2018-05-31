package nst.app.repo;

import java.util.List;
import nst.app.model.State;
import nst.common.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;

public interface StateRepository extends BaseRepository<State> {

    @Query(value = "SELECT * FROM states order by state_name", nativeQuery = true)
    List<State> findAllByOrderByName();
}
