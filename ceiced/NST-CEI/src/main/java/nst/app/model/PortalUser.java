package nst.app.model;

import lombok.Data;
import nst.app.enums.UserType;
import nst.common.base.BaseModel;
import nst.common.security.LoginUser;
import nst.util.AllUtil;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "lae_accounts")
@BatchSize(size = 50)
//@SequenceGenerator(name = "lae_accounts_seq", sequenceName = "lae_accounts_seq", allocationSize = 1)
public class PortalUser extends BaseModel {

  private static final long serialVersionUID = 1L;

  /*
  @Id
  @Column(name = "id", updatable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lae_accounts_seq")
  private Long id;
  */

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "email")
  private String email;

  @Column(name = "contact_number")
  private String contactNo;

  @Column(name = "password")
  private String password;

  @Column(name = "user_type")
  @Enumerated(EnumType.STRING)
  private UserType userType;

  public String getPortalUserName() {
    return String.format("%s %s", nst.app.common.HelperUtil.fromString(firstName), nst.app.common.HelperUtil.fromString(lastName)).trim();
  }

  @Column(name = "unique_id")
  private String uniqueId;

  @Column(name = "cell_otp")
  private String cellOtp;

  @Column(name = "otp_validated")
  private Boolean otpValidated;

  @Column(name = "email_validated")
  private Boolean emailValidated;

  @Column(name = "email_code")
  private String emailCode;

  @Column(name = "reset_password")
  private String resetPassword;

  @Column(name = "expiry_time")
  private Date expiryTime;

  @Column(name = "birth_date")
  private Date birthDate;

  /**
   * Checks if expiryTime date-time(+30 min) is after the current date-time.
   * <p>
   * <pre>
   *   expiryTime = expiryTime.plusSeconds(1800L);
   *   LocalDateTime currentTime = LocalDateTime.now();
   *   expiryTime.isAfter(currentTime) == false
   *   expiryTime.isAfter(expiryTime) == false
   *   currentTime.isAfter(expiryTime) == true
   * </pre>
   * <p>
   *
   * @return false if this currentTime is after the specified expiryTime
   */
  public boolean isOtpExpired() {
    LocalDateTime localDateTime = AllUtil.toLocalDateTime(expiryTime);
    return Objects.isNull(localDateTime) ? Boolean.TRUE : !localDateTime.plusSeconds(1800L).isAfter(LocalDateTime.now());
  }

  public boolean isSameUser(LoginUser loginUser) {
    return getId().equals(loginUser.getUserId());
  }
}