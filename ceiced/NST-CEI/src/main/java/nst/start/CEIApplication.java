package nst.start;

import javax.servlet.MultipartConfigElement;
import nst.app.config.CEIConstants;
import nst.config.MyLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"nst"})
@SpringBootApplication
@EnableJpaRepositories({"nst.repo", "nst.app.repo"})
public class CEIApplication extends SpringBootServletInitializer {

  @Value("${temp.path:NOT_FOUND}")
  public String tempPath;

  @Value("${LOG_HOME}")
  public String LOG_HOME;

  @Value("${data.root}")
  public String dataRoot;

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(CEIApplication.class);
  }

  @Bean
  public MultipartConfigElement filterMultipartResolver() {
    if ("NOT_FOUND".equalsIgnoreCase(tempPath)) {
      tempPath = System.getProperty("user.home");
    }
    MyLogger.log("CEIApplication", "TempPath = %s", tempPath);
    MyLogger.log("CEIApplication", "LogPath = %s", LOG_HOME);
    MyLogger.log("CEIApplication", "DataPath = %s", dataRoot);

    MultipartConfigFactory factory = new MultipartConfigFactory();
    factory.setMaxFileSize(CEIConstants.MAX_FILE_SIZE);
    factory.setMaxRequestSize(CEIConstants.MAX_REQUEST_SIZE);
    factory.setLocation(tempPath);
    return factory.createMultipartConfig();
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(new Object[]{CEIApplication.class}, args);
  }
}
