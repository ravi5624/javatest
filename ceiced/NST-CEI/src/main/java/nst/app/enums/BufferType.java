package nst.app.enums;

import nst.common.EnumType;

public enum BufferType implements EnumType<Integer> {
  CAR_BUFFER(1, "Car Buffer"),
  COUNTER_WEIGHT_BUFFER (2, "Counter Weight Buffer");

  private final String name;
  private final int id;

  BufferType(int id, String name) {
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
