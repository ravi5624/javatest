package nst.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserDetailsService implements UserDetailsService {

  private final LoginUserService userService;

  @Autowired
  public LoginUserDetailsService(LoginUserService userService) {
    this.userService = userService;
  }

  @Override
  public LoginUser loadUserByUsername(String email) throws UsernameNotFoundException {
    LoginUser user = userService.getUserByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException(
            String.format("User with email=%s was not found", email)));
    return user;
  }
}