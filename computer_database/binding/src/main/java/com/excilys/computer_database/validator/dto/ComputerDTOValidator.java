package com.excilys.computer_database.validator.dto;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.excilys.computer_database.dto.ComputerDTO;
import com.excilys.computer_database.validator.dto.annotation.Computer;
import com.excilys.computer_database.mapper.DateMapper;

/**
 * Validation constraint of a computerDTO.
 * @author rlarroque
 */
public class ComputerDTOValidator implements ConstraintValidator <Computer, ComputerDTO> {
       
    @Autowired
    private DateMapper dateMapper;
    
	@Autowired
	public MessageSource messageSource;
    
    @Override
    public void initialize(Computer computer) {
    }

    @Override
    public boolean isValid(ComputerDTO computer, ConstraintValidatorContext context) {
        
        if(computer.getDiscontinuedDate().isEmpty()) {
            return true;
        }
        
        if(computer.getIntroducedDate().isEmpty() && !computer.getDiscontinuedDate().isEmpty()) {
            return false;
        }
        
        Locale locale = LocaleContextHolder.getLocale();
		String regex = messageSource.getMessage("date.pattern.validation", null, locale);

		if (!computer.getIntroducedDate().matches(regex)) {
			return true;
		}

		if (!computer.getDiscontinuedDate().matches(regex)) {
			return true;
		}
        
        LocalDate introduced = dateMapper.toLocalDate(computer.getIntroducedDate());
        LocalDate discontinued = dateMapper.toLocalDate(computer.getDiscontinuedDate());
        
        return introduced.isBefore(discontinued);
    }
}
