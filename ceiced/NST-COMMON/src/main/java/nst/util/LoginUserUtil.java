package nst.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import nst.common.error.AuthorizationException;
import nst.common.security.LoginUser;
import nst.kernal.SystemConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoginUserUtil {

  public static boolean IS_TEST;

  public static LoginUser USER;
  public static LoginUser OWNER_USER;
  public static LoginUser EM_AGENCY;
  public static LoginUser IT_AGENCY;
  public static LoginUser OM_AGENCY;
  public static LoginUser GUEST;
  public static LoginUser WIREMAN;
  public static LoginUser SUPERVISOR;
  public static LoginUser CONTRACTOR;

  public static LoginUser getLoginUser() {
    return getLoginUser(Boolean.FALSE);
  }

  public static LoginUser loadLoginUser() {
    return getLoginUser(Boolean.TRUE);
  }

  public static Collection<GrantedAuthority> getGrantedAuthorities(String... authorities) {
    return Arrays.stream(authorities).map(authoritie -> {
      return new SimpleGrantedAuthority(authoritie);
    }).collect(Collectors.toList());
  }

  private static LoginUser getLoginUser(boolean required) {
    if (IS_TEST) {
      return USER;
    }

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      if (required) {
        throw AuthorizationException.create(SystemConstants.Rest.NO_USER);
      }
      return null;
    }
    if (authentication.getPrincipal() instanceof LoginUser) {
      return ((LoginUser) authentication.getPrincipal());
    }
    if (required) {
      throw AuthorizationException.create(SystemConstants.Rest.NO_USER);
    }
    return null;
  }
}
