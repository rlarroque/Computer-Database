/**
 * 
 */
package com.excilys.computer_database.webapp.dto.validator;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.computer_database.persistence.model.mapper.DateMapper;
import com.excilys.computer_database.webapp.dto.ComputerDTO;

/**
 * Validation constraint of a computerDTO.
 * @author rlarroque
 */
public class ComputerValidatorConstraint implements ConstraintValidator <Computer, ComputerDTO> {
       
    @Autowired
    private DateMapper dateMapper;
    
    @Override
    public void initialize(Computer computer) {
    }

    @Override
    public boolean isValid(ComputerDTO computer, ConstraintValidatorContext context) {
        
        if(computer.discontinuedDate.isEmpty()) {
            return true;
        }
        
        if(computer.introducedDate.isEmpty() && !computer.discontinuedDate.isEmpty()) {
            return false;
        }
        
        LocalDate introduced = dateMapper.toLocalDate(computer.introducedDate);
        LocalDate discontinued = dateMapper.toLocalDate(computer.discontinuedDate);
        
        return introduced.isBefore(discontinued);
    }
}
