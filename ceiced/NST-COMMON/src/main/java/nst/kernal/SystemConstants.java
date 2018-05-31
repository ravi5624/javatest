package nst.kernal;

public class SystemConstants {


  public static final Integer ONE_VALUE = 1;
  public static final Integer ZERO_VALUE = 0;
  public static final Integer HUNDRED_VALUE = 100;
  public static final Integer PASSING_MIN_YEAR = 15;

  public static final String REGEX_FILE_EXT = "^(.*)\\.(.*)$";

  public static final String FORMAT_DATE_FLAT = "yyyyMMdd";
  public static final String FORMAT_DATE_BACKSLASH = "dd/MM/yyyy";
  public static final String FORMAT_DATE_COMMA = "yyyy-MM-dd'T'HH:mm:ss";
  public static final String NEWGEN_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

  public static final String API_SERVICE_ERROR = "BAD_REQUEST";
  public static final String BAD_REQUEST = "BAD_REQUEST";
  public static final String INVALID_DATE = "INVALID_DATE";

  public static final String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";
  public static final String UTF_8 = "UTF-8";
  public static final String SUFFIX_RESET_PASSWORD = "/#authentication/password-expire-reset";

  private SystemConstants() {

  }

  public static final String JSON_CONVERSION_ERROR = "FILE_SAVING_ERROR";
  public static final String FILE_SAVING_ERROR = "FILE_SAVING_ERROR";

  public static class Model {

    public static final String INVALID_REQUEST = "INVALID_REQUEST";
    public static final String TOKEN_EXPIRED= "TOKEN_EXPIRED";
    public static final String INVALID_ACTIVATION_CODE = "INVALID_ACTIVATION_CODE";
    public static final String INVALID_VERSION = "INVALID_VERSION";
    public static final String ALREADY_VALIDATED = "ALREADY_VALIDATED";

    public static final String INVALID_APPLICATION = "INVALID_APPLICATION";
    public static final String INVALID_ORGANIZATION = "INVALID_ORGANIZATION";
    public static final String AGENCY_CANNOT_SUBMIT_TEMPLATE = "AGENCY_CANNOT_SUBMIT_TEMPLATE";
    public static final String INVALID_FILE = "INVALID_FILE";
    public static final String INVALID_AGENCY = "INVALID_AGENCY";
    public static final String INVALID_CERT = "INVALID_CERT";
    public static final String FILE_NOT_FOUND = "FILE_NOT_FOUND";
    public static final String INVALID_CREDENTIALS = "INVALID_CREDENTIALS";
    public static final String INVALID_EMAIL = "INVALID_EMAIL";
    public static final String INVALID_NUMBER = "INVALID_NUMBER";
    public static final String INVALID_TYPE = "INVALID_TYPE";
    public static final String FORM_EXIST = "FORM_EXIST";
    public static final String EMAIL_NOT_VERIFIED = "EMAIL_NOT_VERIFIED";

    private Model() {
    }

  }

  public static class Rest {

    private Rest() {
    }

    public static final String UTF_8 = "UTF-8";

    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_PUT = "PUT";

    public static final String APPLICATION_MULTIPART = "content-type=multipart/form-data";

    public static final String APPLICATION_JSON = "application/json";
    public static final String NO_USER = "NO_USER";
    public static final String UNAUTHORISED_USER = "UNAUTHORISED_USER";
    public static final String UNAUTHORISED_AGENCY = "UNAUTHORISED_AGENCY";

    public static final String SINGLE_APPLICATION_ALLOWED = "SINGLE_APPLICATION_ALLOWED";
    public static final String ERROR_BAD_REQUEST = "BAD_REQUEST";
    public static final String NOT_FOUND = "NOT_FOUND";
    public static final String ERROR_SERVER_SIDE = "SERVER_SIDE_ERROR";
    public static final String NEWGEN_SUBMIT_ERROR = "NEWGEN_SUBMIT_ERROR";
    public static final String PASS_YEAR_ERROR = "Must be grater than DOB.";
    public static final String PASSWORD_NOT_MATCH = "PASSWORD_NOT_MATCH";
    public static final String ADDRESS_NULL = "Address field cannot be null";
    public static final String APPLICATION_NOT_APPROVED = "Previous application is not approved.";
    public static final String EMAIL_ALREADY_EXIST = "EMAIL_ALREADY_EXIST";
    public static final String ALERT = "ALERT";
    public static final String MOBILE_ALREADY_EXIST = "MOBILE_ALREADY_EXIST";
    public static final String FILE_SIZE_MUST_BE_BETWEEN_10_20KB= "File size must between 10-20 KB.";
    public static final String FILE_SIZE_MUST_BE_BETWEEN_20_50KB= "File size must between 20-50 KB.";

  }

  public static class Email {

    private Email() {
    }

    public static final String EMAIL_SENDING_ERROR = "EMAIL_SENDING_ERROR";
  }
}
