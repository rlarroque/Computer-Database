/**
 * 
 */
package com.excilys.computer_database.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.excilys.computer_database.webapp.dto.ComputerDTO;

/**
 * @author rlarroque
 */
@Controller
public abstract class  ApplicationController {
    
    // Mapping values
    protected static final String CONTEXT = "/computer_database/";
    protected static final String DASHBOARD = "/dashboard";
    protected static final String COMPUTER = "/computer";
    protected static final String ADD = "/add";
    protected static final String EDIT = "/edit";
    protected static final String DELETE = "/delete";
    protected static final String ERROR_404 = "/error_404";
    protected static final String ERROR_403 = "/error_403";
    protected static final String REDIRECT = "redirect:";
    
    // JSP pages
    protected static final String JSP_ADD = "/addComputer";
    protected static final String JSP_EDIT = "/editComputer";
    protected static final String JSP_DASHBOARD = "/dashboard";
    protected static final String JSP_403 = "/403";
    protected static final String JSP_404 = "/404";
    protected static final String JSP_500 = "/500";
    
    // Binding
    @ModelAttribute("computerToAdd")
    public ComputerDTO getComputerToAdd() {
        return new ComputerDTO();
    }
    
    @ModelAttribute("computerToEdit")
    public ComputerDTO getComputerToEdit() {
        return new ComputerDTO();
    }
}
