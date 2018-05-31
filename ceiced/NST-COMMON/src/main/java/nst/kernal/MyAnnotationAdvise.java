package nst.kernal;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAnnotationAdvise {

  @Around("@annotation(myAnnotation)")
  public Object around(ProceedingJoinPoint pjp, MyAnnotation myAnnotation) throws Throwable {
    try {
      return pjp.proceed();
    } finally {
      System.out.println("MyAnnotationAdvise = " + myAnnotation.value());
    }
  }
}
