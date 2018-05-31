package nst.common.security;

import java.util.Optional;

public interface LoginUserService {

  Optional<LoginUser> getUserByEmail(String email);

  Object getById(long id);
}
