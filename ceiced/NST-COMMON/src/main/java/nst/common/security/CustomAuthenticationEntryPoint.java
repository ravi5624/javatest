package nst.common.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nst.config.MyLogger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Returns a 401 error code (Unauthorized) to the client.
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException ae)
      throws IOException, ServletException {

    MyLogger.log("CustomAuthenticationEntryPoint",
        "Pre-authenticated entry point called. Rejecting access");
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied for this resource...");
  }
}
