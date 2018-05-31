package nst.kernal.validation;

import java.util.Calendar;
import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import nst.util.AllUtil;
import org.springframework.util.StringUtils;

public class PastDateValidator implements ConstraintValidator<PastDate, String> {

  private PastDate pastDate;

  @Override
  public void initialize(PastDate pastDate) {
    this.pastDate = pastDate;
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext ctx) {
    if (value == null) {
      return true;
    }
    Date date = AllUtil.toUIDate(value);
    if (date == null) {
      return false;
    }
    if (StringUtils.isEmpty(pastDate.value())) {
      boolean after = new Date().after(date);
      if (after == false) {
        ctx.disableDefaultConstraintViolation();
        ctx.buildConstraintViolationWithTemplate("Should be before {value}.")
            .addNode("today")
            .addConstraintViolation();
      }
      return after;
    }

    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - Integer.parseInt(pastDate.value()));
    System.out.println("date = " + date);
    boolean after = calendar.getTime().after(date);
    if (after == false) {
      ctx.disableDefaultConstraintViolation();
      ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
          .addNode(pastDate.value())
          .addConstraintViolation();
    }
    return after;
  }
}
