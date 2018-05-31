package nst.app.enums;

import nst.common.EnumType;

public enum AttachmentType implements EnumType<Integer> {
  APPLICATION(1, "APPLICATION"),
  QUERY(2, "QUERY");

  private final String name;
  private final int id;

  AttachmentType(int id, String name) {
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
