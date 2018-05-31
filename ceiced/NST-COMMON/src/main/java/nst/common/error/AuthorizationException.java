package nst.common.error;

import nst.common.security.LoginUser;
import nst.kernal.SystemConstants.Rest;

public class AuthorizationException extends RuntimeException {

  String object;
  String user;

  public AuthorizationException(String object, String user) {
    super();
    this.object = object;
    this.user = user;
  }

  public static AuthorizationException create(String object, String user) {
    return new AuthorizationException(object, user);
  }

  public static AuthorizationException create(String object, LoginUser loginUser) {
    return new AuthorizationException(object, loginUser != null ? loginUser.getUsername() : Rest.NO_USER);
  }

  public static AuthorizationException create(String user) {
    return new AuthorizationException(null, user);
  }

  @Override
  public String toString() {
    return "AuthorizationException{" +
        "object='" + object + '\'' +
        ", user='" + user + '\'' +
        '}';
  }
}
