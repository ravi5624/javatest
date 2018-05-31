package nst.dto;

import lombok.Data;
import nst.common.DTO;

@Data
public class EmailDTO implements DTO {

  private long userId;
  private String to;
  private String replyTo;
  private String cc;
  private String from;
  private String fromName;
  private String subject;
  private String body;

  public String toDetail() {
    return String.format("%s, %s, %s, %s, %s", to, replyTo, from, fromName, subject);
  }
}