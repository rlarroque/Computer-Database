/**
 * 
 */
package com.excilys.computer_database.webapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author rlarroque
 */
@Controller
public class ExceptionHandlingController extends ApplicationController {

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public String getForbiden(){
        return JSP_403;
    }
    
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String getNotFound(){
        return JSP_404;
    }
    
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String getInternalError(){
        return JSP_500;
    }
}
