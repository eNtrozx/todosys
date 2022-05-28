package bgu.informationsystems.todosys.validations;

import java.text.DateFormat;
import java.text.SimpleDateFormat; 

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateTimeValidator implements ConstraintValidator<DateTimeValid, String> {

    private String dateFormat;

    @Override
    public void initialize(DateTimeValid constraintAnnotation) {
        dateFormat = constraintAnnotation.format();
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {
        if(date == null || date.length() == 0)
           return true;
        try {  
            DateFormat sdf = new SimpleDateFormat(this.dateFormat);
            sdf.setLenient(false);
            sdf.parse(date);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}