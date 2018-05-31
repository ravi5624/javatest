package nst.kernal;

import javax.persistence.AttributeConverter;
import nst.common.EnumType;

public abstract class EnumNameToColumnConverter<E extends EnumType> implements
    AttributeConverter<EnumType, String> {

  @Override
  public String convertToDatabaseColumn(EnumType enumType) {
    return enumType.getName();
  }

  @Override
  public EnumType convertToEntityAttribute(String enumName) {
    return getEnum(enumName);
  }

  public abstract EnumType getEnum(String enumId);
}