package com.excilys.computer_database.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.computer_database.persistence.model.Page;
import com.excilys.computer_database.persistence.model.mapper.PageMapper;
import com.excilys.computer_database.service.ComputerService;
import com.excilys.computer_database.validator.dto_validator.PageDTOValidator;
import com.excilys.computer_database.webapp.controller.utils.PageConstructor;
import com.excilys.computer_database.webapp.dto.PageDTO;

@Controller
public class DashboardController extends ApplicationController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);
	
    @Autowired
    ComputerService computerservice;
    
	@RequestMapping(method = RequestMethod.GET, value = {DASHBOARD, "/", ""})
	public String getDashboard(ModelMap map, HttpServletRequest request){
		
		LOGGER.info("GET Dashboard");	
		
		Page page = PageMapper.toPage(request);
        computerservice.fillPage(page);
        
		PageDTO dto = PageMapper.toDTO(page);
        PageConstructor.construct(dto);
        PageDTOValidator.validate(dto);
    
		map.addAttribute("page", dto);
		
		return JSP_DASHBOARD;
	}
}
