/**
 * 
 */
package com.excilys.computer_database.dto.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computer_database.dto.validator.annotation.Date;
import com.excilys.computer_database.mapper.DateMapper;

/**
 * Constraint of date validation.
 * @author rlarroque
 */
@Component
public class DateValidator implements ConstraintValidator<Date, String> {

    @Autowired
    private DateMapper dateMapper;

    @Override
    public void initialize(Date date) {
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {
        if (date == null || date.isEmpty()) {
            return true;
        }

        return dateMapper.isValid(date);
    }
}
