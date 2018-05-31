package nst.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLogger {

  private static final String SERVICE_FORMAT = "%s : %s : %s";
  private static final String FORMAT = "%s : %s";
  private static final String PERFORMANCE_FORMAT = "%s - %s - %s - %s - %s";
  private static final String API_FORMAT = "%s - %s - %s - %s - %s - %s";
  public static final List<String> AD_MESSAGES = Collections.synchronizedList(new LinkedList());

  private static final Logger LOGGER_CONSOLE = LoggerFactory.getLogger("console");
  private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
  private static final Logger LOGGER_SERVICE = LoggerFactory.getLogger("service");

  public static void performance(String aClass, String method, long time, boolean hasException,
      String exception) {
    LOGGER_CONSOLE
        .info(String.format(PERFORMANCE_FORMAT, aClass, method, time, hasException, exception));
  }

  public static void api(String url, String method, long time, int status, boolean hasException,
      String exception) {
    LOGGER_CONSOLE
        .info(String.format(API_FORMAT, url, method, time, status, hasException, exception));
  }

  public static void log(String sender, String message) {
    logAdMessages(String.format(FORMAT, sender, message));
    LOGGER_CONSOLE.info(String.format(FORMAT, sender, message));
  }

  public static void log(String sender, String format, Object... data) {
    logAdMessages(String.format(FORMAT, sender, String.format(format, data)));
    LOGGER_CONSOLE.info(String.format(FORMAT, sender, String.format(format, data)));
  }

  public static void logError(String sender, String message) {
    logAdMessages(String.format(FORMAT, sender, message));
    LOGGER_ERROR.info(String.format(FORMAT, sender, message));
  }

  public static void logService(String sender, String service, String... data) {
    String messages = Arrays.stream(data).collect(Collectors.joining(", "));
    logAdMessages(messages);
    LOGGER_SERVICE.info(String.format(SERVICE_FORMAT, sender, service, messages));
  }

  public static void logError(String sender, String format, Object... data) {
    logAdMessages(String.format(FORMAT, sender, String.format(format, data)));
    LOGGER_ERROR.info(String.format(FORMAT, sender, String.format(format, data)));
  }

  public static void logError(Throwable throwable) {
    logAdMessages(throwable.getClass().getName() + " => " + throwable.getMessage());
    StringWriter sw = new StringWriter();
    throwable.printStackTrace(new PrintWriter(sw));
    String stackTrace = getStackTrace(throwable);
    LOGGER_ERROR.info(stackTrace);
    logAdMessages(stackTrace);
    try {
      sw.close();
    } catch (IOException e) {
    }
  }

  private static String getStackTrace(Throwable t) {
    t.printStackTrace();
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw, true);
    t.printStackTrace(pw);
    pw.flush();
    sw.flush();
    return sw.toString();
  }

  private static void logAdMessages(String messages) {
    System.out.println(messages);
    if (AD_MESSAGES.size() > 5000) {
      AD_MESSAGES.remove(5000);
    }
    AD_MESSAGES.add(0, new Date() + " => " + messages);
  }
}
