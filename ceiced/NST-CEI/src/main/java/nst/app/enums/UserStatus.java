package nst.app.enums;

import javax.persistence.Convert;
import nst.common.EnumType;
import nst.common.error.AppException;
import org.springframework.util.StringUtils;

@Convert
public enum UserStatus implements EnumType<Integer> {
  ACTIVE(1, "Active"),
  BLOCKED(2, "Blocked");

  private final String name;
  private final int id;

  UserStatus(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getType() {
    return name();
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public Integer getDBId() {
    return id;
  }

  public static UserStatus getById(Integer id) {
    if (StringUtils.isEmpty(id)) {
      return null;
    }
    for (UserStatus userStatus : values()) {
      if (userStatus.id == id) {
        return userStatus;
      }
    }
    throw AppException.create(INVALID_ENUM, ENUM_ID);
  }
}
