package com.excilys.computer_database.webapp.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.computer_database.persistence.model.Page;
import com.excilys.computer_database.persistence.model.mapper.PageMapper;
import com.excilys.computer_database.service.impl.ComputerServiceImpl;
import com.excilys.computer_database.webapp.dto.PageDTO;
import com.excilys.computer_database.webapp.dto.validator.PageDTOValidator;
import com.excilys.computer_database.webapp.servlet.utils.PageConstructor;

/**
 * Servlet in charge of searching computers via filter option.
 * @author rlarroque
 */
@WebServlet(name = "Search", urlPatterns = "/search")
public class Search extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Autowired
    ComputerServiceImpl computerservice;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Page page = new Page(1, 10, "id");
        page.setFilter(request.getParameter("search"));

        computerservice.fillPage(page);

        PageDTO dto = PageMapper.toDTO(page);
        PageConstructor.construct(dto);

        PageDTOValidator.validate(dto);

        request.setAttribute("page", dto);
        request.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response); // Forward to JSP page
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
