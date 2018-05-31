package nst.kernal;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseModel;
import nst.common.security.LoginUser;
import nst.dto.APIRequestDTO;
import nst.dto.APIResponseDTO;
import nst.util.LoginUserUtil;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "external_communication")
@BatchSize(size = 50)
public class ExternalCommunication extends BaseModel {

  public ExternalCommunication(CommunicationType type, String service, String code) {
    LoginUser loginUser = LoginUserUtil.getLoginUser();
    this.type = type;
    this.userName = loginUser == null ? "System" : loginUser.getUsername();
    this.service = service;
    this.code = code;
  }

  @Column(name = "communication_type")
  @Enumerated(EnumType.STRING)
  CommunicationType type;

  @Column(name = "user_name")
  String userName;

  @Column(name = "service")
  String service;

  @Column(name = "code")
  String code;

  @Column(name = "end_point")
  String endPoint;

  @Column(name = "request", columnDefinition = "TEXT")
  String request;

  @Column(name = "status")
  Integer status;

  @Column(name = "response", columnDefinition = "TEXT")
  String response;

  @Column(name = "time_taken")
  Long timeTaken;

  @CreatedDate
  @Column(name = "created_date")
  private Date createdDate;

  public void setDetails(APIRequestDTO requestDTO, APIResponseDTO responseDTO) {
    setRequest(requestDTO.getBody());
    setEndPoint(requestDTO.getEndPoint());
    if (responseDTO != null) {
      setResponse(responseDTO.getResponse());
      setTimeTaken(responseDTO.getTime());
      setStatus(responseDTO.getCode());
    } else {
      setStatus(500);
    }
  }
}