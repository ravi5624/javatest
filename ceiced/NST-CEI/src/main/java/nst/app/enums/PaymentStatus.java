package nst.app.enums;

import nst.common.EnumType;

public enum PaymentStatus implements EnumType<Integer> {
  PENDING(1, "Pending"),
  PAID(1, "Paid"),
  FAILED(1, "Failed");

  private final String name;
  private final int id;

  PaymentStatus(int id, String name) {
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
