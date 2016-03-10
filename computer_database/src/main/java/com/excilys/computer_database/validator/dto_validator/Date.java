/**
 * 
 */
package com.excilys.computer_database.validator.dto_validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Custom annotation for date validation.
 * @author rlarroque
 */
@Constraint(validatedBy = DateValidatorConstraint.class)
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Date {
    
    String message() default "Wrong Date format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
