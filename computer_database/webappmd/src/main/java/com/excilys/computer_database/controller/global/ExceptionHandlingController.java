package com.excilys.computer_database.controller.global;

import com.excilys.computer_database.controller.ApplicationController;
import com.excilys.computer_database.exception.IntegrityException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handling of exceptions.
 * @author rlarroque
 */
@ControllerAdvice
public class ExceptionHandlingController extends ApplicationController {
    
    @ExceptionHandler(IntegrityException.class)
    public ModelAndView getInternalIntegrationException(IntegrityException exception){
        
        ModelAndView model = new ModelAndView();
        model.addObject("exception", exception);
        model.setViewName(JSP_500);
        
        return model;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView getInternalException(Exception exception){

        ModelAndView model = new ModelAndView();
        model.addObject("exception", exception);
        model.setViewName(JSP_500);

        return model;
    }
}