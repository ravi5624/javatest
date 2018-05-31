package nst.manager;

import java.io.InputStream;
import java.util.Properties;
import nst.config.MyLogger;
import nst.util.ResourceReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@DependsOn( {"resourceReader"})
public class ErrorMessage implements InitializingBean {

  @Value("${error.property:application.error.properties}")
  private String errorProperty;

  private static Properties PROPERTIES = new Properties();

  @Override
  public void afterPropertiesSet() {
    InputStream inputStream = null;
    try {
      inputStream = ResourceReader.readAsStream(errorProperty);
      MyLogger.log("ErrorMessage", "Load from %s, Found %s", errorProperty, (inputStream != null));

      if (inputStream != null) {
        PROPERTIES.load(inputStream);
      }
    } catch (Exception fnfE) {
      MyLogger.logError(fnfE);
    } finally {
      try {
        if (inputStream != null) {
          inputStream.close();
        }
      } catch (Throwable e) {
        MyLogger.logError(e);
      }
    }
  }

  public static String getMessage(String key) {
    return PROPERTIES.getProperty(key);
  }

  public static String formatMessage(String key, String[] placeHolders) {
    String propertyValue = getMessage(key);
    return StringUtils.isEmpty(propertyValue) ? ""
        : placeHolders != null && placeHolders.length > 0 ? String
            .format(propertyValue, placeHolders) : propertyValue;
  }

  public static String getMessage(String lang, String key) {
    return getMessage(String.format("%s.%s", lang, key));
  }

  public static String formatMessage(String lang, String key, String[] placeHolders) {
    return formatMessage(String.format("%s.%s", lang, key), placeHolders);
  }
}