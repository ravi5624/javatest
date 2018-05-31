package nst.app.enums;

import nst.common.EnumType;
import org.springframework.util.StringUtils;

public enum LiftEscalatorType implements EnumType<Integer> {
  LIFT(1, "Lift"),
  ESCALATOR(1, "Escalator"),;

  private final String name;
  private final int id;

  LiftEscalatorType(int id, String name) {
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

  @Override
  public Integer getDBId() {
    return id;
  }

  public static LiftEscalatorType fromType(String type) {
    if (!StringUtils.isEmpty(type)) {
      return LiftEscalatorType.valueOf(type.toUpperCase());
    }
    //throw AppException.create(INVALID_ENUM, ENUM_TYPE);
    return null;
  }
}
