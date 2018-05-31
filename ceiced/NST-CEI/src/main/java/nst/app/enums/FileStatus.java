package nst.app.enums;

import nst.common.EnumType;
import nst.common.error.AppException;
import org.springframework.util.StringUtils;

public enum FileStatus implements EnumType<Integer> {
  DRAFT(1, "Draft"),
  OWNER_SUBMITTED(1, "Owner Submitted"),
  AGENCY_SUBMITTED(1, "Agency Submitted"),
  SUBMITTED(1, "Submitted"),
  IN_PROCESS(1, "In Process"),
  QUERIED(1, "Queried"),
  QUERY_REPLIED(1, "Query Replied"),
  APPROVED(1, "Approved"),
  REJECTED(1, "Rejected"),
  NOT_APPROVED(1, "Not Approved"),
  CANCELLED(1, "Cancelled");

  private final String name;
  private final int id;

  FileStatus(int id, String name) {
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

  public static FileStatus getByType(String name) {
    if (StringUtils.isEmpty(name)) {
      return null;
    }
    for (FileStatus userStatus : values()) {
      if (userStatus.getType().equalsIgnoreCase(name)) {
        return userStatus;
      }
    }

    throw AppException.create(INVALID_ENUM, ENUM_NAME);
  }
}
