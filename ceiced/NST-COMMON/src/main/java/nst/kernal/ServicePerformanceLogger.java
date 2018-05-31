package nst.kernal;

import nst.config.MyLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ServicePerformanceLogger {

  @Around("target(nst.common.Service) && execution(* *(..))")
  public Object around(ProceedingJoinPoint pjp) throws Throwable {
    long startTime = System.currentTimeMillis();
    Object result = null;
    boolean hasException = false;
    String exception = "";
    Throwable throwable = null;
    try {
      result = pjp.proceed();
    } catch (Throwable error) {
      hasException = true;
      exception = error.getMessage();
      throwable = error;
    }

    long execTime = System.currentTimeMillis() - startTime;
    MyLogger
        .performance(pjp.getTarget().getClass().getName(), pjp.getSignature().getName(), execTime,
            hasException, exception);
    if (hasException) {
      throw throwable;
    }
    return result;
  }
}
