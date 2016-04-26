/**
 * 
 */
package com.excilys.computer_database.validator.dto;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.excilys.computer_database.mapper.DateMapper;
import com.excilys.computer_database.validator.dto.annotation.Date;

/**
 * Constraint of date validation.
 * 
 * @author rlarroque
 */
@Component
public class DateValidator implements ConstraintValidator<Date, String> {

	@Autowired
	private DateMapper dateMapper;

	@Autowired
	public MessageSource messageSource;

	@Override
	public void initialize(Date date) {
	}

	@Override
	public boolean isValid(String date, ConstraintValidatorContext context) {
		if (date == null || date.isEmpty()) {
			return true;
		}

		Locale locale = LocaleContextHolder.getLocale();
		String format = messageSource.getMessage("date.pattern", null, locale);
		String regex = messageSource.getMessage("date.pattern.validation", null, locale);

		LocalDate localDate = null;

		if (!date.matches(regex)) {
			return false;
		}

		try {
			localDate = new Timestamp(new SimpleDateFormat(format).parse(date).getTime()).toLocalDateTime()
					.toLocalDate();
		} catch (ParseException e) {
			return false;
		}

		LocalDate min = LocalDate.of(1970, Month.JANUARY, 2);
		
		if(min.isAfter(localDate)){
			return false;
		}
		
		return dateMapper.isValid(date);
	}
}
