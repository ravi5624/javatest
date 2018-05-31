package nst.kernal;

import javax.persistence.AttributeConverter;
import nst.common.EnumType;

public abstract class EnumIdToColumnConverter<E extends EnumType> implements
    AttributeConverter<EnumType, Integer> {

  @Override
  public Integer convertToDatabaseColumn(EnumType enumType) {
    return enumType.getId();
  }

  @Override
  public EnumType convertToEntityAttribute(Integer enumId) {
    return getEnum(enumId);
  }

  public abstract EnumType getEnum(Integer enumId);
}