package com.excilys.computer_database.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computer_database.dto.CompanyDTO;
import com.excilys.computer_database.dto.ComputerDTO;
import com.excilys.computer_database.dto.validator.CompanyDTOValidator;
import com.excilys.computer_database.dto.validator.ComputerDTOValidator;
import com.excilys.computer_database.persistence.model.mapper.CompanyMapper;
import com.excilys.computer_database.persistence.model.mapper.ComputerMapper;
import com.excilys.computer_database.service.impl.CompanyServiceImpl;
import com.excilys.computer_database.service.impl.ComputerServiceImpl;

/**
 * 
 * @author rlarroque
 *
 */
@WebServlet(name="AddComputer", urlPatterns="/add_computer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CompanyDTO> listCompanies = new ArrayList<>();
		
		CompanyServiceImpl compService = CompanyServiceImpl.getInstance();
		
		listCompanies = CompanyMapper.toDTO(compService.getAll());
		CompanyDTOValidator.validate(listCompanies);
		
		request.setAttribute("companies", listCompanies); // Store the list of computers in the request scope
		request.getRequestDispatcher("/WEB-INF/jsp/addComputer.jsp").forward(request, response); // Forward to JSP page
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		Integer companyId = Integer.parseInt(request.getParameter("companyId"));
		
		ComputerDTO dto = new ComputerDTO(name);
		dto.setIntroducedDate(introduced);
		dto.setDiscontinuedDate(discontinued);
		dto.setCompanyId(companyId);
		ComputerDTOValidator.validate(dto);
		
		ComputerServiceImpl compService = ComputerServiceImpl.getInstance();
		compService.create(ComputerMapper.toComputer(dto));
		
		response.sendRedirect("display_computers?page=1&offset=10"); // redirect to JSP page
	}

}