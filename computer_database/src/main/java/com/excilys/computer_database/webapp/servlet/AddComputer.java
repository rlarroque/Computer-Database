package com.excilys.computer_database.webapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.computer_database.persistence.model.mapper.CompanyMapper;
import com.excilys.computer_database.persistence.model.mapper.ComputerMapper;
import com.excilys.computer_database.service.impl.CompanyServiceImpl;
import com.excilys.computer_database.service.impl.ComputerServiceImpl;
import com.excilys.computer_database.webapp.dto.CompanyDTO;
import com.excilys.computer_database.webapp.dto.ComputerDTO;
import com.excilys.computer_database.webapp.dto.validator.CompanyDTOValidator;
import com.excilys.computer_database.webapp.dto.validator.ComputerDTOValidator;

/**
 * Servlet in charge of adding computers.
 * @author rlarroque
 *
 */
@WebServlet(name = "AddComputer", urlPatterns = "/add_computer")
public class AddComputer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Autowired
    ComputerServiceImpl computerService;

    @Autowired
    CompanyServiceImpl companyService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<CompanyDTO> listCompanies = new ArrayList<>();

        listCompanies = CompanyMapper.toDTO(companyService.getAll());
        CompanyDTOValidator.validate(listCompanies);

        request.setAttribute("companies", listCompanies); // Store the list of computers in the request scope
        request.getRequestDispatcher("/WEB-INF/jsp/addComputer.jsp").forward(request, response); // Forward to JSP page
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ComputerDTO dto = ComputerMapper.toDTO(request);
        
        ComputerDTOValidator.validate(dto);
        computerService.create(ComputerMapper.toComputer(dto));

        response.sendRedirect("display_computers?page=1&offset=10"); // redirect to JSP page
    }

}
