package nst.repo;

import nst.common.base.BaseRepository;
import nst.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User> {

}