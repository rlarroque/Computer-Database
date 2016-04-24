package com.excilys.computer_database.api;

import com.excilys.computer_database.dto.PageParams;
import com.excilys.computer_database.model.utils.OrderColumn;
import com.excilys.computer_database.model.utils.OrderType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author rlarroque
 */
@Controller
public abstract  class ApiController {

    protected static final String REST_API = "/rest";
    protected static final String COMPUTER = "/computer";
    protected static final String PAGINATED = "/paginated";
    protected static final String COMPANY = "/company";
    protected static final String ID = "/{id}";

    @ModelAttribute("params")
    public PageParams getPage() {
        return new PageParams(1, 10, OrderColumn.ID.toString(), OrderType.ASC.toString(), "");
    }
}
