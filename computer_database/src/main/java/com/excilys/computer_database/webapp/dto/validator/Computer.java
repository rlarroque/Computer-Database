/**
 * 
 */
package com.excilys.computer_database.webapp.dto.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Custom annoation for computerDTO validation. Make sure that the introduced date is before the discontinued date.
 * @author rlarroque
 */
@Constraint(validatedBy = ComputerValidatorConstraint.class)
@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Computer {

    String message() default "Introduced date must be before discontinued date ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
