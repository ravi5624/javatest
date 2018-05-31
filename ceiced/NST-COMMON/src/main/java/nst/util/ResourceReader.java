package nst.util;

import java.io.InputStream;
import nst.common.error.AppException;
import nst.config.MyLogger;
import nst.config.SystemConfiguration;
import nst.kernal.SystemConstants;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@DependsOn({"systemConfiguration"})
public class ResourceReader implements InitializingBean {

  @Override
  public void afterPropertiesSet() throws Exception {
    MyLogger.log("ResourceReader", SystemConfiguration.ENV);
  }

  public static String readAsString(String resourcePath) {
    return IOUtil.readAsString(readAsStream(resourcePath), SystemConstants.UTF_8);
  }

  public static InputStream readAsStream(String resourcePath) {
    try {
      return new ClassPathResource(resourcePath).getInputStream();
    } catch (Exception e) {
      MyLogger.logError(e);
      throw AppException.createWithMessageCode(SystemConstants.Rest.ERROR_BAD_REQUEST,
          SystemConstants.RESOURCE_NOT_FOUND);
    }
  }
}