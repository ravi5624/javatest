package nst.service;

import java.util.List;
import nst.common.AbstractService;
import nst.dto.UserDTO;
import nst.manager.UserManager;
import nst.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserService extends AbstractService {

  @Autowired
  UserManager userManager;

  public User save(User user) {
    return userManager.save(user);
  }

  public User getById(long id) {
    return userManager.getById(id);
  }

  public List<UserDTO> getAll() {
    return userManager.getAll();
  }
}
