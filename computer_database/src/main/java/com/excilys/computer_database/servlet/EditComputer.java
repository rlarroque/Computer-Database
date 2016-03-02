package com.excilys.computer_database.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.computer_database.dto.CompanyDTO;
import com.excilys.computer_database.dto.ComputerDTO;
import com.excilys.computer_database.dto.validator.ComputerDTOValidator;
import com.excilys.computer_database.persistence.model.mapper.CompanyMapper;
import com.excilys.computer_database.persistence.model.mapper.ComputerMapper;
import com.excilys.computer_database.service.impl.CompanyServiceImpl;
import com.excilys.computer_database.service.impl.ComputerServiceImpl;

/**
 * Servlet in charge of editing the computers.
 * @author rlarroque
 */
@WebServlet(name = "EditComputer", urlPatterns = "/edit_computer")
public class EditComputer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Autowired
    ComputerServiceImpl computerservice;

    @Autowired
    CompanyServiceImpl companyservice;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<CompanyDTO> listCompanies = CompanyMapper.toDTO(companyservice.getAll());
        ComputerDTO dto = ComputerMapper.toDTO(computerservice.get(Integer.parseInt(request.getParameter("computer"))));

        ComputerDTOValidator.validate(dto);

        request.setAttribute("computer", dto);
        request.setAttribute("companies", listCompanies);
        request.getRequestDispatcher("/WEB-INF/jsp/editComputer.jsp").forward(request, response); // Forward to JSP page
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ComputerDTO dto = ComputerMapper.toDTO(request);
        
        ComputerDTOValidator.validate(dto);
        computerservice.update(ComputerMapper.toComputer(dto));

        response.sendRedirect("display_computers?page=1&offset=10"); // redirect to JSP page
    }

}