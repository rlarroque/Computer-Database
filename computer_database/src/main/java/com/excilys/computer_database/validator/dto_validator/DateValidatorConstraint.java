/**
 * 
 */
package com.excilys.computer_database.validator.dto_validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.DateValidator;

/**
 * Constraint of date validation.
 * @author rlarroque
 */
public class DateValidatorConstraint implements ConstraintValidator<Date, String> {
    
    @Override
    public void initialize(Date date) {
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {
        if(date == null || date.isEmpty()) {
            return true;
        }
        
        return isValidDate(date);
    }

    private static boolean isValidDate(String date) {
        return DateValidator.getInstance().isValid(date, getPattern());
    }
    
    private static String getPattern() {
        String pattern = null;        
        
        return pattern;
    }
}
