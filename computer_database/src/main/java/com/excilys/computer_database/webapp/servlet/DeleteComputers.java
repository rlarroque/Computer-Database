package com.excilys.computer_database.webapp.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.computer_database.service.impl.ComputerServiceImpl;

/**
 * Servlet in charge of deleting computers.
 * @author rlarroque
 */
@WebServlet(name = "DeleteComputers", urlPatterns = "/delete_computers")
public class DeleteComputers extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Autowired
    ComputerServiceImpl computerservice;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Store the multiple ids retrieved in a list.
        List<String> ids = Arrays.asList(request.getParameter("selection").split("\\s*,\\s*"));

        for (String id : ids) {
            computerservice.delete(Integer.parseInt(id));
        }

        response.sendRedirect("display_computers?page=1&offset=10");
    }

}
