package com.excilys.computer_database.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computer_database.service.impl.ComputerServiceImpl;

@WebServlet(name="DeleteComputers", urlPatterns="/delete_computers")
public class DeleteComputers extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ComputerServiceImpl compService = ComputerServiceImpl.getInstance();

		//Store the multiple ids retrieved in a list.
		List<String> ids = Arrays.asList(request.getParameter("selection").split("\\s*,\\s*"));
		
		for (String id : ids) {
			compService.delete(Integer.parseInt(id));
		}
		
		response.sendRedirect("displayComputers?page=1&offset=10");
	}

}
