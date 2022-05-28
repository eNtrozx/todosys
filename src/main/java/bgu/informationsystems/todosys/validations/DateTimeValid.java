package bgu.informationsystems.todosys.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = DateTimeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateTimeValid{

    public String message() default "Invalid datetime, use MM/dd/yyyy format!";
    public String format() default "MM/dd/yyyy";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}