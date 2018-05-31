package nst.common.error;

/**
 * This Exception is thrown if any unknown error happens into system.
 */
public class TechnicalException extends RuntimeException {

  public TechnicalException() {
    super();
  }

  public TechnicalException(String message) {
    super(message);
  }

  public TechnicalException(String message, Throwable cause) {
    super(message, cause);
  }

  public TechnicalException(Throwable cause) {
    super(cause);
  }
}
