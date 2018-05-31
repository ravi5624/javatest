package nst.app.config;

import javax.persistence.Convert;
import nst.app.enums.UserStatus;
import nst.common.EnumType;
import nst.kernal.EnumIdToColumnConverter;

@Convert
public class UserStatusIdConverter extends EnumIdToColumnConverter<UserStatus> {

  @Override
  public EnumType getEnum(Integer enumId) {
    return UserStatus.getById(enumId);
  }
}
