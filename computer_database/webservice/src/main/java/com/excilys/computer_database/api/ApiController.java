package com.excilys.computer_database.api;

import org.springframework.stereotype.Controller;

/**
 * @author rlarroque
 */
@Controller
public abstract  class ApiController {

    protected static final String REST_API = "/rest";
    protected static final String COMPUTER = "/computer";
    protected static final String COMPANY = "/company";
    protected static final String ID = "/{id}";

}
