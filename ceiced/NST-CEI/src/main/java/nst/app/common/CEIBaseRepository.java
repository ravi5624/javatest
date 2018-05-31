package nst.app.common;

import nst.common.base.BaseModel;
import nst.common.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CEIBaseRepository<M extends BaseModel> extends BaseRepository<M> {

  M findByForm_id(long id);

}