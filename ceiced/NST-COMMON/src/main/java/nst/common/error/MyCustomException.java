package nst.common.error;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(of = {"problems"})
public class MyCustomException extends RuntimeException {

  private static final long serialVersionUID = 1l;

  private Map<String, String> problems = new LinkedHashMap<>();

  public static MyCustomException create(String key, String value) {
    MyCustomException myCustomException = new MyCustomException();
    myCustomException.problems.put(key, value);
    return myCustomException;
  }
  public static MyCustomException create(String key1, String value1, String key2, String value2) {
    MyCustomException myCustomException = new MyCustomException();
    myCustomException.problems.put(key1, value1);
    myCustomException.problems.put(key2, value2);
    return myCustomException;
  }
}