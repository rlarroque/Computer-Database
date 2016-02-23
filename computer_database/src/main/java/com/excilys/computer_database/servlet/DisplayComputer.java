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
import com.excilys.computer_database.persistence.model.utils.Order;
import com.excilys.computer_database.persistence.model.utils.OrderColumn;
import com.excilys.computer_database.persistence.model.utils.OrderType;
import com.excilys.computer_database.service.impl.ComputerServiceImpl;
import com.excilys.computer_database.servlet.utils.PageConstructor;

/**
 * Servlet in charge of displaying computers with pagination.
 * @author rlarroque
 *
 */
@WebServlet(name = "DisplayComputers", urlPatterns = "/display_computers")
public class DisplayComputer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ComputerServiceImpl compService = ComputerServiceImpl.getInstance();

        Page page = new Page(Integer.parseInt(request.getParameter("page")),
                Integer.parseInt(request.getParameter("offset")), request.getParameter("filter"));

        if (request.getParameter("order") != null && !"".equals(request.getParameter("order"))) {

            if (request.getParameter("order_type") != null
                    && !"".equals(request.getParameter("order_type"))) {
                page.setOrder(new Order(OrderType.valueOf(request.getParameter("order_type")),
                        OrderColumn.fromString(request.getParameter("order"))));
            } else {
                page.setOrder(new Order(OrderType.ASC,
                        OrderColumn.fromString(request.getParameter("order"))));
            }
        } else {
            page.setOrder(new Order(OrderType.ASC, OrderColumn.ID));
        }

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
