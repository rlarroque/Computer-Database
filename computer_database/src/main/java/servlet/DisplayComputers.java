package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computer_database.dto.ComputerDTO;
import com.excilys.computer_database.service.impl.ComputerServiceImpl;
import com.excilys.computer_database.service.impl.PageService;

/**
 * Servlet implementation class DisplayComputers
 */
@WebServlet(name="DisplayComputers", urlPatterns="/displayComputers")
public class DisplayComputers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ComputerDTO> listComputers = new ArrayList<>();
		
		ComputerServiceImpl compService = ComputerServiceImpl.getInstance();
		PageService Page = PageService.getInstance();
		
		Page.setCurrentPage(Integer.parseInt(request.getParameter("page")));
		Page.setOffset(Integer.parseInt(request.getParameter("offset")));
		
		listComputers = compService.getComputersPage();
		
		for (ComputerDTO computerDTO : listComputers) {
			System.out.println(computerDTO);
		}
		
		request.setAttribute("computers", listComputers); // Store the list of computers in the request scope
		request.getRequestDispatcher("/jsp/dashboard.jsp").forward(request, response); // Forward to JSP page
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
