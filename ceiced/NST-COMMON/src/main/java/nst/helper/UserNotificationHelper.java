package nst.helper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import nst.common.Helper;
import nst.dto.EmailDTO;
import nst.dto.UserNotificationDTO;
import nst.kernal.NotificationType;
import nst.kernal.UserNotification;
import org.springframework.stereotype.Component;

@Component
public class UserNotificationHelper implements Helper {

  public UserNotification toUserNotification(EmailDTO dto) {
    UserNotification notification = new UserNotification(NotificationType.EMAIL,
        dto.getUserId());
    notification.setTo(dto.getTo());
    notification.setBody(dto.getBody());
    notification.setSubject(dto.getSubject());
    notification.setCc(dto.getCc());
    notification.setReplyTo(dto.getReplyTo());
    notification.setFrom(dto.getFrom());
    notification.setFromName(dto.getFromName());

    notification.setCode("CODE");
    notification.setService("ABC");
    return notification;
  }

  public List<UserNotificationDTO> toDTO(Iterable<UserNotification> notifications) {
    if (notifications == null) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(notifications.spliterator(), false).map(this::toDTO)
        .collect(Collectors.toList());
  }

  public UserNotificationDTO toDTO(UserNotification notification) {
    UserNotificationDTO dto = new UserNotificationDTO();
    dto.setTo(notification.getTo());
    dto.setBody(notification.getBody());
    dto.setSubject(notification.getSubject());
    dto.setCc(notification.getCc());
    dto.setSendOn(notification.getSendOn());
    dto.setStatus(notification.getStatus().name());
    dto.setRead(notification.isRead());
    dto.setReadOn(notification.getReadOn());
    return dto;
  }
}
