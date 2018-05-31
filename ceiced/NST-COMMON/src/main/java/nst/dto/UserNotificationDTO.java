package nst.dto;

import java.util.Date;
import lombok.Data;
import nst.common.base.BaseDTO;

@Data
public class UserNotificationDTO extends BaseDTO {

  String type;
  String status;
  Long userId;
  String notificationId;
  String service;
  String code;
  String to;
  String cc;
  String subject;
  String body;
  Date preferTime;
  Date sendOn;
  boolean read;
  Date readOn;
  String readReceipt;
  String pageLinking;
}