package nst.kernal.validation;

import nst.util.AllUtil;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;

public class MaxDateValidator implements ConstraintValidator<MaxDate, String> {
    private MaxDate maxDate;

    @Override
    public void initialize(MaxDate maxDate) {
        this.maxDate = maxDate;
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
        if (StringUtils.isEmpty(maxDate.value())) {
            boolean before = new Date().before(date);
            if (before == false) {
                ctx.disableDefaultConstraintViolation();
                ctx.buildConstraintViolationWithTemplate("Should be after {value}.")
                        .addNode("today")
                        .addConstraintViolation();
            }
            return before;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - Integer.parseInt(maxDate.value()));
        System.out.println("date = " + date);
        boolean before = calendar.getTime().before(date);
        if (before == false) {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
                    .addNode(maxDate.value())
                    .addConstraintViolation();
        }
        return before;
    }
}
