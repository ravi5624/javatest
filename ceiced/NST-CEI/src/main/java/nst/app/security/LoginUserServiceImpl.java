package nst.app.security;

import nst.app.enums.UserType;
import nst.app.manager.PortalUserManager;
import nst.app.model.PortalUser;
import nst.common.security.LoginUser;
import nst.common.security.LoginUserService;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUserServiceImpl implements LoginUserService {

  @Autowired
  PortalUserManager userManager;

  @Override
  public Optional<LoginUser> getUserByEmail(String userName) {
    PortalUser portalUser = null;
    String[] split = userName.split(":");
    if (split.length == 2) {
      portalUser = userManager.getByUserTypeAndUserNameAndEmailValidate(UserType.valueOf(split[1].toUpperCase()), split[0]);
      return Optional.ofNullable(portalUser == null ? null
          : new LoginUser(portalUser.getId(), portalUser.getUserName(), portalUser.getPassword(),
          LoginUserUtil.getGrantedAuthorities(portalUser.getUserType().getType())));
    }
    return Optional.empty();
  }

  @Override
  public PortalUser getById(long id) {
    return userManager.getById(id);
  }
}