package nst.common.security;

import nst.common.error.AppException;
import nst.kernal.SystemConstants;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class LoginUser extends org.springframework.security.core.userdetails.User {

  private Long userId;

  public LoginUser(Long userId, String username, String password,
      Collection<GrantedAuthority> authorities) {
    super(username, password, authorities);
    this.userId = userId;
  }

  public Long getUserId() {
    return userId;
  }

  @Override
  public String toString() {
    return "LoginUser{" +
        "userId=" + userId +
        ", " +
        super.toString() +
        '}';
  }

  public boolean hasAuthority(String authority) {
    return getAuthorities().stream().anyMatch(g -> g.getAuthority().equalsIgnoreCase(authority));
  }

  public void validateAuthority(String authority) {
    if (!hasAuthority(authority)) {
      throw AppException.createWithMessageCode(SystemConstants.BAD_REQUEST, SystemConstants.Rest.UNAUTHORISED_USER);
    }
  }
}
