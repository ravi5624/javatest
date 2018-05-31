package nst.dto;

import lombok.Data;
import nst.common.DTO;

@Data
public class CellDTO implements DTO {

  private long userId;
  private String to;
  private String body;
  private String server;
  private String userName;
  private String password;
  private String senderId;
}