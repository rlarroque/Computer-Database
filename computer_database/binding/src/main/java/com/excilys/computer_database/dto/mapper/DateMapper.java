package com.excilys.computer_database.dto.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.commons.validator.routines.DateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.excilys.computer_database.exception.DateException;

/**
 * Mapper used to manipulate date in String or LocalDate format.
 * @author rlarroque
 */
@Component
public class DateMapper {
    
    @Autowired
    private MessageSource messageSource;
    
    /**
     * Used to retrieve a String based on a local date and the current format.
     * @param isoDate the localDate to stringify
     * @return the string of the locaDate
     */
    public String toString(LocalDate isoDate) {
        return isoDate.format(DateTimeFormatter.ofPattern(getPattern()));
    }
    
    /**
     * Convert a String into a localDate with the consideration of the current format.
     * @param date the String to convert
     * @return the localeDate converted
     */
    public LocalDate toLocalDate(String date) {
        if (date == null || "".equals(date)) {
            return null;
        } 
        
        if(DateValidator.getInstance().isValid(date, getPattern())) {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(getPattern()));
        } else {
            throw new DateException("The date '" + date + "' is not following the pattern " + getPattern());
        }
    }
    
    /**
     * Tells if a string correspond to a valid date.
     * @param date the String to test
     * @return true if is valid, else false
     */
    public boolean isValid(String date) {
        return DateValidator.getInstance().isValid(date.replace('/', '-'), getPattern());
    }
    
    /**
     * Retrieve the current format of date.
     * @return the pattern
     */
    private String getPattern() {
        Locale userLocale = LocaleContextHolder.getLocale();
        
        return messageSource.getMessage("date.pattern", null, userLocale);
    }
}
