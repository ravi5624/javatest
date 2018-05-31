package nst.kernal;

import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import nst.common.error.AppException;
import nst.config.MyLogger;
import nst.dto.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class EmailSender {

  @Autowired
  private MailSender mailSender;

  public void send(EmailDTO email) {
    MyLogger.log("%s => %s", "Sending", email.toDetail());
    final MimeMessage message = ((JavaMailSenderImpl) mailSender).createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
      message.setHeader("Content-Transfer-Encoding", "base64");
      helper.setTo(email.getTo());
      helper.setSubject(email.getSubject());
      helper.setText(email.getBody(), true);

      BodyPart htmlPart = new MimeBodyPart();
      htmlPart.setContent(email.getBody(), "text/html");
      Multipart multipart = new MimeMultipart();
      multipart.addBodyPart(htmlPart);

      message.setContent(multipart);

      if (!StringUtils.isEmpty(email.getReplyTo())) {
        helper.setReplyTo(email.getReplyTo());
      }

      if (!StringUtils.isEmpty(email.getFrom()) && !StringUtils.isEmpty(email.getFromName())) {
        helper.setFrom(new InternetAddress(email.getFrom(), email.getFromName()));
      }

      if (!StringUtils.isEmpty(email.getCc())) {
        helper.setCc(email.getCc());
      }

      ((JavaMailSenderImpl) mailSender).send(message);
      MyLogger.log("%s => %s", "Sent", email.toDetail());
    } catch (Throwable throwable) {
      MyLogger.log("EmailSender ERROR ==>", throwable.getMessage());
      MyLogger.logError(throwable);
      throw AppException.createWithMessage(SystemConstants.Email.EMAIL_SENDING_ERROR,
          throwable.getMessage());
    }
  }
}
