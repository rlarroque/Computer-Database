package com.excilys.computer_database.dto.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.excilys.computer_database.dto.validator.DateValidator;

/**
 * Custom annotation for date validation.
 * @author rlarroque
 */
@Constraint(validatedBy = DateValidator.class)
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Date {
    
    String message() default "Wrong Date format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
