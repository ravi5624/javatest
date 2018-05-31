package nst.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SystemConfiguration implements InitializingBean {

  @Value("${deployment.env:DEV}")
  private String deploymentEnv;

  @Autowired
  Environment env;

  public static String ENV;
  public static boolean IS_DEV_ENV;
  public static boolean IS_QA_ENV;
  public static boolean IS_UAT_ENV;
  public static boolean IS_PROD_ENV;

  @Override
  public void afterPropertiesSet() throws Exception {
    IS_DEV_ENV = "DEV".equalsIgnoreCase(deploymentEnv);
    IS_QA_ENV = "QA".equalsIgnoreCase(deploymentEnv);
    IS_UAT_ENV = "BU".equalsIgnoreCase(deploymentEnv);
    IS_PROD_ENV = "PROD".equalsIgnoreCase(deploymentEnv);
    ENV = deploymentEnv;
  }
}