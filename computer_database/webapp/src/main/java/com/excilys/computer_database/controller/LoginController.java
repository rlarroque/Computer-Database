package com.excilys.computer_database.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController extends ApplicationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(method = RequestMethod.GET, value = LOGIN)
	public String getLogin() {
		
		LOGGER.info("GET login");
		
		return JSP_LOGIN;
	}

}
