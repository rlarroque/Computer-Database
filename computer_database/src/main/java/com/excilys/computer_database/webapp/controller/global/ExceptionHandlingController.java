/**
 * 
 */
package com.excilys.computer_database.webapp.controller.global;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.webapp.controller.ApplicationController;

/**
 * @author rlarroque
 */
@ControllerAdvice
public class ExceptionHandlingController extends ApplicationController {
    
    @ExceptionHandler(IntegrityException.class)
    public ModelAndView getInternalError(Exception exception){
        
        ModelAndView model = new ModelAndView();
        model.addObject("exception", exception);
        model.setViewName(JSP_500);
        
        return model;
    }
}
