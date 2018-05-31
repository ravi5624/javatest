package nst.app.config;

import javax.persistence.Convert;
import nst.app.enums.UserStatus;
import nst.common.EnumType;
import nst.kernal.EnumNameToColumnConverter;

@Convert
public class UserStatusNameConverter extends EnumNameToColumnConverter<UserStatus> {

  @Override
  public EnumType getEnum(String enumName) {
    return UserStatus.valueOf(enumName.trim().toUpperCase());
  }
}
