package nst.common.error;

import nst.common.base.BaseResponse;
import nst.common.base.ResponseError;
import nst.config.MyLogger;
import nst.kernal.SystemConstants.Rest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {

  @ExceptionHandler(AppException.class)
  @ResponseBody
  public ResponseEntity appException(AppException information) {
    MyLogger.log("ExceptionController:AppException => ", information.toString());
    BaseResponse response = BaseResponse
        .createFail(Rest.ERROR_BAD_REQUEST, information.getResponseError());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler({RuntimeException.class, Exception.class, TechnicalException.class})
  @ResponseBody
  public ResponseEntity technicalError(Throwable exception) {
    MyLogger.log("ExceptionController:TechnicalException => ", exception.getMessage());
    MyLogger.logError("ExceptionController:TechnicalException", exception.getMessage());
    MyLogger.logError(exception);
    BaseResponse fail = BaseResponse.createFail(Rest.ERROR_SERVER_SIDE,
        ResponseError
            .create(Rest.ERROR_SERVER_SIDE, Rest.ERROR_SERVER_SIDE, exception.getMessage()));

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(fail);
  }

  @ExceptionHandler(MyCustomException.class)
  @ResponseBody
  public ResponseEntity appException(MyCustomException customException) {
    MyLogger.log("ExceptionController:MyCustomException => ", customException.toString());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customException.getProblems());
  }

  @ExceptionHandler(AuthorizationException.class)
  @ResponseBody
  public ResponseEntity authorizationException(AuthorizationException authorizationException) {
    MyLogger
        .log("ExceptionController:AuthorizationException => ", authorizationException.toString());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }

  @ExceptionHandler(DataValidationException.class)
  @ResponseBody
  public ResponseEntity dataValidationException(DataValidationException exception) {
    MyLogger.log("ExceptionController:DataValidationException => ", exception.toString());
    BaseResponse response = BaseResponse.createForErrors(Rest.ERROR_BAD_REQUEST);
    response.setErrors(exception.getErrors());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException ex) {
    MyLogger.log("ExceptionController:MethodArgumentNotValidException => ", ex.toString());
    BindingResult result = ex.getBindingResult();
    BaseResponse response = BaseResponse.createForErrors(Rest.ERROR_BAD_REQUEST);

    result.getFieldErrors().stream().forEach(error -> {
      response.addError(
          ResponseError
              .create(error.getDefaultMessage(), error.getField(), error.getDefaultMessage(),
                  error.getDefaultMessage()));
    });

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }
}