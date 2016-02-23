package com.excilys.computer_database.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computer_database.dto.PageDTO;
import com.excilys.computer_database.dto.validator.PageDTOValidator;
import com.excilys.computer_database.persistence.model.Page;
import com.excilys.computer_database.persistence.model.mapper.PageMapper;
import com.excilys.computer_database.service.impl.ComputerServiceImpl;
import com.excilys.computer_database.servlet.utils.PageConstructor;

/**
 * Servlet in charge of searching computers via filter option.
 * @author rlarroque
 */
@WebServlet(name = "Search", urlPatterns = "/search")
public class Search extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ComputerServiceImpl compService = ComputerServiceImpl.getInstance();

        Page page = new Page(1, 10, "id");
        page.setFilter(request.getParameter("search"));

        compService.fillPage(page);

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
