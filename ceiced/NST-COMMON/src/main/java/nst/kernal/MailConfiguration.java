package nst.kernal;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Data
@Configuration
public class MailConfiguration {

  @Value("${email.host}")
  private String host;

  @Value("${email.port}")
  private int port;

  @Value("${email.username}")
  private String username;

  @Value("${email.password}")
  private String password;

  @Value("${email.from}")
  private String from;

  @Value("${email.fromName}")
  private String fromName;

  @Bean
  public MailSender javaMailService() {
    JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
    javaMailSender.setHost(host);
    javaMailSender.setPort(port);
    javaMailSender.setUsername(username);
    javaMailSender.setPassword(password);
    javaMailSender.getJavaMailProperties().setProperty("mail.smtp.ssl.trust", host);
    javaMailSender.getJavaMailProperties().setProperty("mail.smtp.starttls.enable", "true");
    javaMailSender.getJavaMailProperties().setProperty("mail.smtp.auth", "true");
    javaMailSender.getJavaMailProperties().setProperty("mail.mime.charset", "UTF-8");
    javaMailSender.getJavaMailProperties().setProperty("mail.smtp.allow8bitmime", "true");
    javaMailSender.getJavaMailProperties().setProperty("mail.smtps.allow8bitmime", "true");
    return javaMailSender;
  }
}
