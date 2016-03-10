/**
 * 
 */
package com.excilys.computer_database.webapp.dto.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computer_database.persistence.model.mapper.DateMapper;

/**
 * Constraint of date validation.
 * @author rlarroque
 */
@Component
public class DateValidatorConstraint implements ConstraintValidator<Date, String> {

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
