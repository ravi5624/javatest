package nst.kernal;

import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseModel;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user_notifications")
@BatchSize(size = 50)
public class UserNotification extends BaseModel {

  public UserNotification(NotificationType type, long userId) {
    this.type = type;
    this.userId = userId;
  }

  @Column(name = "notification_type")
  @Enumerated(EnumType.STRING)
  NotificationType type;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  NotificationStatus status = NotificationStatus.NEW;

  @Column(name = "user_id")
  Long userId;

  @Column(name = "notification_id")
  String notificationId;

  @Column(name = "service")
  String service;

  @Column(name = "code")
  String code;

  @Column(name = "notify_to")
  String to;

  @Column(name = "notify_cc")
  String cc;

  @Column(name = "reply_to")
  String replyTo;

  @Column(name = "notify_from")
  String from;

  @Column(name = "from_name")
  String fromName;

  @Column(name = "subject")
  String subject;

  @Column(name = "body", columnDefinition = "TEXT")
  String body;

  @Column(name = "prefer_time")
  Date preferTime;

  @Column(name = "send_on")
  Date sendOn;

  @Column(name = "is_read")
  boolean read;

  @Column(name = "read_on")
  Date readOn;

  @Column(name = "read_receipt")
  String readReceipt;

  /*To open page the page from Link provided into Email/SMS*/
  @Column(name = "page_linking")
  String pageLinking;

  @CreatedDate
  @Column(name = "created_date")
  private Date createdDate;
}