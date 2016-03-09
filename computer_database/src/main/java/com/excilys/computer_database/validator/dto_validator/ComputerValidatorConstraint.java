/**
 * 
 */
package com.excilys.computer_database.validator.dto_validator;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.excilys.computer_database.webapp.dto.ComputerDTO;

/**
 * @author rlarroque
 */
public class ComputerValidatorConstraint implements ConstraintValidator <Computer, ComputerDTO> {
    
    @Override
    public void initialize(Computer computer) {
    }

    @Override
    public boolean isValid(ComputerDTO computer, ConstraintValidatorContext context) {
        
        if(computer.introducedDate.isEmpty() && computer.discontinuedDate.isEmpty()) {
            return true;
        }
        
        LocalDate introduced = LocalDate.parse(computer.introducedDate);
        LocalDate discontinued = LocalDate.parse(computer.discontinuedDate);
        
        return introduced.isBefore(discontinued);
    }
}
