package com.excilys.computer_database.controller.global;

import com.excilys.computer_database.exception.CliException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computer_database.controller.ApplicationController;
import com.excilys.computer_database.exception.IntegrityException;

/**
 * Handling of exceptions.
 * @author rlarroque
 */
@ControllerAdvice
public class ExceptionHandlingController extends ApplicationController {
    
    @ExceptionHandler(IntegrityException.class)
    public ModelAndView getInternalIntegrationError(IntegrityException exception){
        
        ModelAndView model = new ModelAndView();
        model.addObject("exception", exception);
        model.setViewName(JSP_500);
        
        return model;
    }

    @ExceptionHandler(CliException.class)
    public ModelAndView getInternalError(CliException exception){

        ModelAndView model = new ModelAndView();
        model.addObject("exception", exception);
        model.setViewName(JSP_500);

        return model;
    }
}