/**
 * 
 */
package com.excilys.computer_database.controller.global;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.controller.ApplicationController;

/**
 * Handling of execptions.
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
