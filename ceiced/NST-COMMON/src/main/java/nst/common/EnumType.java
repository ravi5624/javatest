package nst.common;

import java.io.Serializable;

public interface EnumType<T extends Serializable> extends Serializable {

  String INVALID_ENUM = "INVALID_ENUM";
  String ENUM_ID = "ID";
  String ENUM_NAME = "NAME";
  String ENUM_TYPE = "INVALID_TYPE";

  int getId();

  T getDBId();

  String getName();

  String getType();
}