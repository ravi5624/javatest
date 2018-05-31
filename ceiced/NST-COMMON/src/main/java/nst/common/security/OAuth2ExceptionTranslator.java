package nst.common.security;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;


public class OAuth2ExceptionTranslator implements WebResponseExceptionTranslator {
  private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

  @Override
  public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
    // Try to extract a SpringSecurityException from the stacktrace
    Throwable[] causeChain = throwableAnalyzer.determineCauseChain(e);
    RuntimeException ase =
        (OAuth2Exception) throwableAnalyzer.getFirstThrowableOfType(OAuth2Exception.class, causeChain);

    if (e instanceof InvalidGrantException) {
      return handleOAuth2Exception(new ForbiddenException(ase.getMessage(), ase));
    }

    if (ase != null) {
      return handleOAuth2Exception((OAuth2Exception) ase);
    }

    ase =
        (AuthenticationException) throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class, causeChain);
    if (ase != null) {
      return handleOAuth2Exception(new UnauthorizedException(e.getMessage(), e));
    }

    ase = (AccessDeniedException) throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class, causeChain);
    if (ase instanceof AccessDeniedException) {
      return handleOAuth2Exception(new ForbiddenException(ase.getMessage(), ase));
    }

    return handleOAuth2Exception(new ServerErrorException(e.getMessage(), e));

  }

  private ResponseEntity<OAuth2Exception> handleOAuth2Exception(OAuth2Exception e) throws IOException {
    int status = e.getHttpErrorCode();
    HttpHeaders headers = new HttpHeaders();
    headers.set("Cache-Control", "no-store");
    headers.set("Pragma", "no-cache");
    Map<String, String> errorParams = new HashMap<String, String>();
    errorParams.put("message", e.getMessage());
    errorParams.put("status", String.valueOf(e.getHttpErrorCode()));
    errorParams.put("timestamp", new Date().toString());

    ResponseEntity<OAuth2Exception> response =
        new ResponseEntity<OAuth2Exception>(OAuth2Exception.valueOf(errorParams), headers, HttpStatus
            .valueOf(status));
    return response;

  }

  public void setThrowableAnalyzer(ThrowableAnalyzer throwableAnalyzer) {
    this.throwableAnalyzer = throwableAnalyzer;
  }

  @SuppressWarnings("serial")
  private static class ForbiddenException extends OAuth2Exception {

    public ForbiddenException(String msg, Throwable t) {
      super(msg, t);
    }

    @Override
    public String getOAuth2ErrorCode() {
      return "access_denied";
    }

    @Override
    public int getHttpErrorCode() {
      return 403;
    }
  }

  @SuppressWarnings("serial")
  private static class ServerErrorException extends OAuth2Exception {

    public ServerErrorException(String msg, Throwable t) {
      super(msg, t);
    }

    @Override
    public String getOAuth2ErrorCode() {
      return "server_error";
    }

    @Override
    public int getHttpErrorCode() {
      return 500;
    }

  }

  @SuppressWarnings("serial")
  private static class UnauthorizedException extends OAuth2Exception {

    public UnauthorizedException(String msg, Throwable t) {
      super(msg, t);
    }

    @Override
    public String getOAuth2ErrorCode() {
      return "unauthorized";
    }

    @Override
    public int getHttpErrorCode() {
      return 401;
    }

  }
}
