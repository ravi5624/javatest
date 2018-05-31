package nst.kernal;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nst.config.MyLogger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class APILoggingFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    long startTime = System.currentTimeMillis();
    boolean hasException = false;
    String exception = null;
    try {
      filterChain.doFilter(servletRequest, servletResponse);
    } catch (Throwable throwable) {
      MyLogger.logError(throwable);

      hasException = true;
      exception = throwable.getMessage();

      throw throwable;
    } finally {
      HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
      HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

      MyLogger.api(httpServletRequest.getRequestURI(),
          httpServletRequest.getMethod(),
          (System.currentTimeMillis() - startTime),
          httpServletResponse.getStatus(),
          hasException, exception);
    }
  }

  @Override
  public void destroy() {
  }
}
