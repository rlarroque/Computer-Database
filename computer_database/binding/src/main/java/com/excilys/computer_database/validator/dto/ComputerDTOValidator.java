package com.excilys.computer_database.validator.dto;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.computer_database.dto.model.ComputerDTO;
import com.excilys.computer_database.validator.dto.annotation.Computer;
import com.excilys.computer_database.mapper.DateMapper;

/**
 * Validation constraint of a computerDTO.
 * @author rlarroque
 */
public class ComputerDTOValidator implements ConstraintValidator <Computer, ComputerDTO> {
       
    @Autowired
    private DateMapper dateMapper;
    
    @Override
    public void initialize(Computer computer) {
    }

    @Override
    public boolean isValid(ComputerDTO computer, ConstraintValidatorContext context) {
        
        if(computer.getDiscontinuedDate().isEmpty()) {
            return true;
        }
        
        if(computer.getDiscontinuedDate().isEmpty() && !computer.getDiscontinuedDate().isEmpty()) {
            return false;
        }
        
        LocalDate introduced = dateMapper.toLocalDate(computer.getIntroducedDate());
        LocalDate discontinued = dateMapper.toLocalDate(computer.getDiscontinuedDate());
        
        return introduced.isBefore(discontinued);
    }
}
