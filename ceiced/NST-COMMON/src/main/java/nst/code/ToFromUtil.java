package nst.code;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import org.springframework.util.ReflectionUtils;

public class ToFromUtil {

  public static void getFieldValue(Class aClass) {
    Method[] methods = ReflectionUtils.getAllDeclaredMethods(aClass);
    Arrays.stream(methods)
        .filter(method -> method.getName().startsWith("get"))
        .filter(method -> (method.getReturnType() == String.class ||
            method.getReturnType() == Boolean.class ||
            method.getReturnType() == Integer.class) ||
            method.getReturnType() == Float.class ||
            method.getReturnType() == Double.class
        )
        .forEach(method -> {
          System.out.println(String
              .format("dto.%s(model.%s());", method.getName().replace("get", "set"),
                  method.getName()));
        });

    System.out.println("===========================");

    Arrays.stream(methods)
        .filter(method -> method.getName().startsWith("get"))
        .filter(method -> method.getReturnType() == Date.class)
        .forEach(method -> {
          System.out.println(String
              .format("dto.%s(AllUtil.manageUIDate(model.%s()));",
                  method.getName().replace("get", "set"),
                  method.getName()));
        });
  }

  public static void getFieldValue(Object from, Object to) {
    Method[] methods = ReflectionUtils.getAllDeclaredMethods(from.getClass());
    Arrays.stream(methods)
        .filter(method -> method.getName().startsWith("get"))
        .filter(method -> method.getReturnType() == String.class)
        .forEach(System.out::println);
  }
}