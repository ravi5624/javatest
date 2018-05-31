package nst.kernal.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CombiDateValidator.class)
@Documented
public @interface CombiDate {

  String message() default "{end} should greater than {start} date.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String endField();

  String startField();

  @Target({ElementType.FIELD})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  @interface List {

    String endField();

    String startField();
  }
}