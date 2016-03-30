package com.excilys.computer_database.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller in charge of security routes. ie: login and logout from the application.
 * @author rlarroque
 */
@Controller
public class SecurityController extends ApplicationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);
	
	@RequestMapping(method = RequestMethod.GET, value = LOGIN)
	public String getLogin() {
		
		LOGGER.info("GET login");
		
		return "login-md";
	}

	@RequestMapping(method = RequestMethod.GET, value = LOGOUT)
    public String logoutPage () {
		
		LOGGER.info("GET logout");
		
		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        
        return REDIRECT + JSP_LOGOUT;
    }

}
