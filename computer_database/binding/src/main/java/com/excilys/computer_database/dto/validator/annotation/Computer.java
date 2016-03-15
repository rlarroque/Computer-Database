package com.excilys.computer_database.dto.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.excilys.computer_database.dto.validator.ComputerDTOValidator;

/**
 * Custom annoation for computerDTO validation. Make sure that the introduced date is before the discontinued date.
 * @author rlarroque
 */
@Constraint(validatedBy = ComputerDTOValidator.class)
@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Computer {

    String message() default "Introduced date must be before discontinued date ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
