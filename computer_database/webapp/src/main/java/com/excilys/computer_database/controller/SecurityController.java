package com.excilys.computer_database.controller;

import com.excilys.computer_database.dto.UserDTO;
import com.excilys.computer_database.mapper.UserMapper;
import com.excilys.computer_database.model.User;
import com.excilys.computer_database.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Controller in charge of security routes. ie: login and logout from the application.
 * @author rlarroque
 */
@Controller
public class SecurityController extends ApplicationController {

	@Resource(name = "userDetailsService")
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);
	
	@RequestMapping(method = RequestMethod.GET, value = LOGIN)
	public String getLogin() {
		
		LOGGER.info("GET login");
		
		return JSP_LOGIN;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = LOGOUT)
    public String logoutPage () {
		
		LOGGER.info("GET logout");
		
		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        
        return REDIRECT + JSP_LOGOUT;
    }

	@RequestMapping(method = RequestMethod.POST, value = USER + "/create")
	public String userAdd (@ModelAttribute("user") UserDTO dto){

		LOGGER.info("POST user creation");

		User user = userMapper.toUser(dto);
		userService.create(user);

		return REDIRECT + JSP_LOGIN;
	}
}
