package nst.app.enums;

import nst.common.EnumType;

public enum DesignationType implements EnumType<Integer> {
  SUPERVISOR(1, "Supervisor"),
  WIREMAN(2, "Wireman"),
  APPRENTICE(3, "Apprentice");

  private final String name;
  private final int id;

  DesignationType(int id, String name) {
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
