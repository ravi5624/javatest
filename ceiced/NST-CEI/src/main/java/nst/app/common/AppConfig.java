package nst.app.common;

import nst.config.MyLogger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class AppConfig implements InitializingBean {

  @Value("${data.root}")
  private String dataRoot;

  @Value("${env.baseUrl:NOT_FOUND}")
  private String baseUrl;

  @Value("${isLocal}")
  private String isLocal;

  @Value("${certURL}")
  private String certURL;

  @Value("${certRoot}")
  private String certRoot;

  @Value("${certUser}")
  private String certUser;

  @Value("${certPassword}")
  private String certPassword;

  @Value("${email.subject.welcome}")
  private String welcomeSubject;

  @Value("${email.subject.forget.password}")
  private String forgetPasswordSubject;

  @Value("${email.fromName}")
  private String emailFromName;

  @Value("${sms.server}")
  private String smsServer;

  @Value("${sms.username}")
  private String smsUserName;

  @Value("${sms.password}")
  private String smsPassword;

  @Value("${sms.senderid}")
  private String smsSender;

  @Value("${sms.body}")
  private String smsBody;

  @Value("${hostUrl}")
  private String hostUrl;

  public String getIsLocal() { return isLocal; }

  public String getHostUrl() { return hostUrl; }

  public String getSmsBody() { return smsBody; }

  public String getSmsServer() { return smsServer; }

  public String getSmsUserName() { return smsUserName; }

  public String getSmsPassword() { return smsPassword; }

  public String getSmsSender() { return smsSender; }

  public String getWelcomeSubject() { return welcomeSubject; }

  public String getForgetPasswordSubject() { return forgetPasswordSubject; }

  public String getEmailFromName() { return emailFromName; }

  public String getCertUser() { return certUser; }

  public String getCertPassword() { return certPassword; }

  public String getCertURL() { return certURL; }

  public String getCertRoot() { return certRoot; }

  public String getBaseUrl() {
    return baseUrl;
  }

  public String getDataRoot() {
    return dataRoot;
  }

  public File getPathRoot(String... path) {
    File pathRoot = new File(getAbsolutePath(path));
    if (!pathRoot.exists()) {
      pathRoot.mkdirs();
    }
    return pathRoot;
  }

  public String getAbsolutePath(String... path) {
    return dataRoot + File.separator + Arrays.stream(path)
        .collect(Collectors.joining(File.separator));
  }

  public String getRelativePath(String... path) {
    return File.separator + Arrays.stream(path)
        .collect(Collectors.joining(File.separator));
  }

  @Override
  public void afterPropertiesSet() {
    try {
      File file = new File(dataRoot);
      if (file.exists() == false) {
        file.mkdirs();
      }
    } catch (Throwable throwable) {
      MyLogger.log("AppConfig:afterPropertiesSet", throwable.getMessage());
      MyLogger.logError(throwable);
      System.exit(0);
    }
  }
}
