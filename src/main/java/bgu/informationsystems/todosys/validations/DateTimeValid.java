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

    public String message() default "Invalid datetime, use ISO 8601 format!";
    public String format() default "yyyy-MM-dd'T'HH:mm:ssZ";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}