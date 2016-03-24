package com.excilys.computer_database.controller;

import com.excilys.computer_database.dto.ComputerDTO;
import com.excilys.computer_database.dto.PageParams;
import com.excilys.computer_database.model.utils.OrderColumn;
import com.excilys.computer_database.model.utils.OrderType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Abstract controller where all default routes and JSP path are exposed.
 * @author rlarroque
 */
@Controller
public abstract class  ApplicationController {
    
    // Mapping values
    protected static final String CONTEXT = "/computer_database/";
    protected static final String REST_API = "/api/rest";
    protected static final String DASHBOARD = "/dashboard";
    protected static final String COMPUTER = "/computer";
    protected static final String ADD = "/add";
    protected static final String EDIT = "/edit";
    protected static final String DELETE = "/delete";
    protected static final String ERROR_404 = "/error_404";
    protected static final String ERROR_403 = "/error_403";
    protected static final String LOGIN = "/login";
    protected static final String LOGOUT = "/logout";
    protected static final String REDIRECT = "redirect:";

    // Path variables
    protected static final String ID = "/{id}";

    // JSP pages
    protected static final String JSP_ADD = "/addComputer";
    protected static final String JSP_EDIT = "/editComputer";
    protected static final String JSP_DASHBOARD = "/dashboard";
    protected static final String JSP_LOGIN = "/login";
    protected static final String JSP_LOGOUT = "/login?logout";
    protected static final String JSP_403 = "/error/403";
    protected static final String JSP_404 = "/error/404";
    protected static final String JSP_500 = "/error/500";
    
    // Binding method parameter to a named model attribute, exposed to the web view
    @ModelAttribute("computerToAdd")
    public ComputerDTO getComputerToAdd() {
        return new ComputerDTO();
    }
    
    @ModelAttribute("computerToEdit")
    public ComputerDTO getComputerToEdit() {
        return new ComputerDTO();
    }

    @ModelAttribute("params")
    public PageParams getPage() {
    	return new PageParams(1, 10, OrderColumn.ID.toString(), OrderType.ASC.toString(), "");
    }
}
