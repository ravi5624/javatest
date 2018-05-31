package nst.common;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import nst.common.base.ResponseError;
import nst.common.error.DataValidationException;
import org.springframework.util.CollectionUtils;

public class ValidationUtil {

  public static <T> void validate(T t, Class[] classes) {
    validate(t, classes, new ArrayList<>());
  }

  public static <T> void validate(T t, Class[] classes, List<ResponseError> allErrors) {
    Arrays.stream(classes).forEach(aClass -> {
      allErrors.addAll(validateFor(t, aClass));
    });

    if (CollectionUtils.isEmpty(allErrors)) {
      return;
    }
    throw DataValidationException.create(allErrors);
  }

  public static <T> List<ResponseError> validateFor(T t, Class[] aClass) {
    List<ResponseError> allErrors = new ArrayList<>();
    Arrays.stream(aClass).forEach(ac -> {
      allErrors.addAll(validateFor(t, ac));
    });
    return allErrors;
  }

  public static <T> List<ResponseError> validateFor(T t, Class aClass) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
    if (CollectionUtils.isEmpty(constraintViolations)) {
      return Collections.emptyList();
    }

    List<ResponseError> errors = new ArrayList<>();
    constraintViolations.forEach(error -> {
      if (error.getConstraintDescriptor().getAnnotation().annotationType() == aClass) {
        StringBuilder path = new StringBuilder();
        error.getPropertyPath().forEach(node -> {
          path.append(node.getName()).append(".");
        });
        errors
            .add(ResponseError.create(aClass.getSimpleName(), path.toString(), error.getMessage()));
      }
    });
    return errors;
  }

  public static <T> List<ResponseError> validate(T t) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);

    if (CollectionUtils.isEmpty(constraintViolations)) {
      return Collections.emptyList();
    }

    List<ResponseError> errors = new ArrayList<>();
    constraintViolations.forEach(error -> {
      StringBuilder path = new StringBuilder();
      error.getPropertyPath().forEach(node -> {
        path.append(node.getName()).append(".");
      });
      errors.add(ResponseError.create("FIELD_ERROR", path.toString(), error.getMessage()));
    });
    return errors;
  }

  public static <T> List<ResponseError> validateGroup(T t, Class group, Class[] classes) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<T>> constraintViolations = validator.validate(t, group);

    if (CollectionUtils.isEmpty(constraintViolations)) {
      return Collections.emptyList();
    }

      List<ResponseError> errors = new ArrayList<>();
      constraintViolations.stream().filter(constraintViolation -> {
      Optional<Class> first = Arrays.stream(classes).filter(
              constraintClass -> constraintViolation.getConstraintDescriptor().getAnnotation().annotationType() == constraintClass).findFirst();
      return first.isPresent();
    }).forEach(error -> {
      StringBuilder path = new StringBuilder();
      error.getPropertyPath().forEach(node -> {
        path.append(node.getName()).append(".");
      });
      errors.add(ResponseError.create("FIELD_ERROR", path.toString(), error.getMessage()));
    });
    return errors;
  }
}