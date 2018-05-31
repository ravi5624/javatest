package nst.common.base;

import nst.common.Repository;
import org.springframework.data.repository.CrudRepository;

public interface BaseRepository<T> extends CrudRepository<T, Long>, Repository {



}
