package nst.kernal.validation;

import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import nst.util.AllUtil;

public class CombiDateValidator implements ConstraintValidator<CombiDate, Object> {

  private CombiDate combiDate;

  @Override
  public void initialize(CombiDate combiDate) {
    this.combiDate = combiDate;
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext ctx) {
    if (value == null) {
      return true;
    }
    try {
      String startField = AllUtil.getFieldValue(value, combiDate.startField());
      String endField = AllUtil.getFieldValue(value, combiDate.endField());

      Date startDate = AllUtil.toUIDate(startField);
      Date endDate = AllUtil.toUIDate(endField);

      if (startDate == null && endDate == null) {
        return true;
      }
      if (startDate == null) {
        return false;
      }
      if (endDate == null) {
        return true;
      }
      return endDate.after(startDate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

}