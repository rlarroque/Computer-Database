package com.excilys.computer_database.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computer_database.dto.PageDTO;
import com.excilys.computer_database.persistence.model.Page;
import com.excilys.computer_database.persistence.model.mapper.PageMapper;
import com.excilys.computer_database.service.impl.ComputerServiceImpl;
import com.excilys.computer_database.servlet.utils.PageConstructor;

/**
 * 
 * @author rlarroque
 *
 */
@WebServlet(name = "DisplayComputers", urlPatterns = "/displayComputers")
public class DisplayComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerServiceImpl compService = ComputerServiceImpl.getInstance();
		
		Page page = new Page(Integer.parseInt(request.getParameter("page")),
							 Integer.parseInt(request.getParameter("offset")));

		compService.fillPage(page);
		
		PageDTO dto = PageMapper.toDTO(page);
		PageConstructor.construct(dto);
		
		request.setAttribute("page", dto);
		request.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response); // Forward to JSP page
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
