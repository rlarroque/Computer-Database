/**
 * 
 */
package com.excilys.computer_database.controller.global;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.computer_database.controller.ApplicationController;

/**
 * Handling of 404 and 403 pages
 * @author rlarroque
 */
@Controller
public class ErrorController extends ApplicationController {
    
    @RequestMapping(method = RequestMethod.GET, value = ERROR_403)
    public String getForbiden(){
        return JSP_403;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = ERROR_404)
    public String getNotFound(){
        return JSP_404;
    }
}
