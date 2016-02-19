package com.excilys.computer_database.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computer_database.dto.CompanyDTO;
import com.excilys.computer_database.dto.ComputerDTO;
import com.excilys.computer_database.dto.validator.ComputerDTOValidator;
import com.excilys.computer_database.persistence.model.mapper.CompanyMapper;
import com.excilys.computer_database.persistence.model.mapper.ComputerMapper;
import com.excilys.computer_database.service.CompanyService;
import com.excilys.computer_database.service.ComputerService;
import com.excilys.computer_database.service.impl.CompanyServiceImpl;
import com.excilys.computer_database.service.impl.ComputerServiceImpl;

@WebServlet(name="EditComputer", urlPatterns="/edit_computer")
public class EditComputer extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CompanyService compnayService = CompanyServiceImpl.getInstance();
		ComputerService computerService = ComputerServiceImpl.getInstance();
		
		List<CompanyDTO> listCompanies = CompanyMapper.toDTO(compnayService.getAll());
		
		ComputerDTO computer = ComputerMapper.toDTO(computerService.get(Integer.parseInt(request.getParameter("computer"))));
		
		ComputerDTOValidator.validate(computer);
		
		request.setAttribute("computer", computer);
		request.setAttribute("companies", listCompanies);
		request.getRequestDispatcher("/WEB-INF/jsp/editComputer.jsp").forward(request, response); // Forward to JSP page
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		int id = Integer.parseInt(request.getParameter("computerId"));
		int companyId = Integer.parseInt(request.getParameter("companyId"));
		
		ComputerDTO dto = new ComputerDTO(name);
		dto.setId(id);
		dto.setIntroducedDate(introduced);
		dto.setDiscontinuedDate(discontinued);
		dto.setCompanyId(companyId);
		
		ComputerServiceImpl compService = ComputerServiceImpl.getInstance();
		compService.update(ComputerMapper.toComputer(dto));
		
		response.sendRedirect("displayComputers?page=1&offset=10"); // redirect to JSP page
	}

}