package nst.app.enums;

import nst.common.EnumType;

public enum QueryStatus implements EnumType<Integer> {
  QUERIED(1, "Queried"),
  REPLIED(2, "Replied");

  private final String name;
  private final int id;

  QueryStatus(int id, String name) {
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