package nst.manager;

import java.util.ArrayList;
import java.util.List;
import nst.dto.UserDTO;
import nst.model.User;
import nst.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserManager {

  @Autowired
  UserRepository userRepository;

  public User save(User user) {
    return userRepository.save(user);
  }

  public User getById(long id) {
    return userRepository.findOne(id);
  }

  public List<UserDTO> getAll() {
    List<UserDTO> list = new ArrayList<>();
    userRepository.findAll().forEach(user -> {
      list.add(new UserDTO(user.getId(), user.getUserId()));
    });
    return list;
  }
}