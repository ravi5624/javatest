package nst.app.enums;

import nst.common.EnumType;
import nst.common.error.AppException;
import org.springframework.util.StringUtils;

import javax.persistence.Convert;
import java.util.Arrays;

@Convert
public enum UserType implements EnumType<Integer> {
  OWNER(1, "Owner",
      ApplicationType.ACCIDENT, ApplicationType.LIL, ApplicationType.EIL,
      ApplicationType.OESCL, ApplicationType.OLIFT,
      ApplicationType.LEDL, ApplicationType.LEML, ApplicationType.LERL),

  /*Part - 2*/
  /*Only Self license Renewal, Modification & Duplicate*/
  EM_AGENCY(2, "E & M Agency",
      ApplicationType.ACCIDENT,
      ApplicationType.EMAL,
      ApplicationType.ALMODI, ApplicationType.ALDUPL, ApplicationType.ALRENE,
      ApplicationType.LEDL, ApplicationType.LERL,
      ApplicationType.LIL, ApplicationType.EIL,
      ApplicationType.OESCL, ApplicationType.OLIFT),

  IT_AGENCY(3, "I & T Agency",
      ApplicationType.ACCIDENT,
      ApplicationType.ITAL,
      ApplicationType.ALMODI, ApplicationType.ALDUPL, ApplicationType.ALRENE,
      ApplicationType.LEDL, ApplicationType.LERL),

  OM_AGENCY(4, "O & M Agency",
      ApplicationType.ACCIDENT,
      ApplicationType.OMAL,
      ApplicationType.ALMODI, ApplicationType.ALDUPL, ApplicationType.ALRENE,
      ApplicationType.LEDL, ApplicationType.LERL),

  GUEST(5, "Guest", ApplicationType.ACCIDENT),

  WIREMAN(6, "Wireman",
      ApplicationType.WIREXM,
      ApplicationType.REPEATER,
      ApplicationType.WIREXMP,
      ApplicationType.WIRMODI,
      ApplicationType.WIRREN,
      ApplicationType.ISPERMIT,
      ApplicationType.DUPPER){
    @Override
    public String getGujName() {
      return "વાયરમેન";
    }
  },

  SUPERVISOR(7, "Supervisor",
      ApplicationType.SUPEXM,
      ApplicationType.SUPMODI,
      ApplicationType.SUPREN,
      ApplicationType.SUPEXMP,
      ApplicationType.ISPERMIT,
      ApplicationType.DUPPER,
      ApplicationType.REPEATER
      ){
    @Override
    public String getGujName() {
      return "સુપરવાઇઝર";
    }
  }
  ,

  CONTRACTOR(8, "Contractor",
      ApplicationType.CONLIC,
      ApplicationType.CONMODI,
      ApplicationType.CONPARTMODI,
      ApplicationType.CONREN,
      /*ApplicationType.ISPERMIT,
      ApplicationType.DUPPER,
      ApplicationType.REPEATER,*/
      ApplicationType.FORMI),
  ;

  private final String name;
  private final int id;
  private final ApplicationType[] operations;

  UserType(int id, String name, ApplicationType... operations) {
    this.id = id;
    this.name = name;
    this.operations = operations;
  }

  public String getType() {
    return name();
  }

  public String getName() {
    return name;
  }

  public String getGujName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public boolean hasOperation(ApplicationType type) {
    return operations != null && operations.length > 0 && Arrays.stream(operations)
        .anyMatch(op -> type == op);
  }

  public ApplicationType[] getOperations() {
    return operations;
  }

  public Integer getDBId() {
    return id;
  }

  public static UserType getById(Integer id) {
    if (StringUtils.isEmpty(id)) {
      return null;
    }
    for (UserType userStatus : values()) {
      if (userStatus.id == id) {
        return userStatus;
      }
    }
    throw AppException.create(INVALID_ENUM, ENUM_ID);
  }

  public static UserType getByType(String name) {
    if (!StringUtils.isEmpty(name)) {
      for (UserType userStatus : values()) {
        if (userStatus.getType().equalsIgnoreCase(name)) {
          return userStatus;
        }
      }
    }
    throw AppException.create(INVALID_ENUM, ENUM_NAME);
  }
}
