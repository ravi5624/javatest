package nst.app.enums;

import nst.common.EnumType;

public enum AgencyType implements EnumType<Integer> {
  E_M_AGENCY(1, "E & M Agency"),
  I_T_AGENCY(1, "I & T Agency"),
  O_M_AGENCY(1, "O & M Agency"),;

  private final String name;
  private final int id;

  AgencyType(int id, String name) {
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
}
