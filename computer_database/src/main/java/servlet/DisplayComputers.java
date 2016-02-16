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
import com.excilys.computer_database.mapping.ComputerMapper;
import com.excilys.computer_database.service.impl.ComputerServiceImpl;

/**
 * 
 * @author excilys
 *
 */
@WebServlet(name = "DisplayComputers", urlPatterns = "/displayComputers")
public class DisplayComputers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ComputerDTO> listComputers = new ArrayList<>();

		ComputerServiceImpl compService = ComputerServiceImpl.getInstance();

		if (request.getParameter("offset") == null || request.getParameter("page") == null) {
			listComputers = ComputerMapper.listComputerToListDTO(compService.getComputersPage(10, 1));
			request.setAttribute("offset", 10);
		} else {
			listComputers = ComputerMapper.listComputerToListDTO(compService.getComputersPage(
															Integer.parseInt(request.getParameter("offset")),
															Integer.parseInt(request.getParameter("page"))));
			request.setAttribute("offset", request.getParameter("offset"));
		}
		
		int computerNumber = compService.computerNumber();
		
		request.setAttribute("currentPage", request.getParameter("page"));
		
		request.setAttribute("computerNumber", computerNumber); // Store the number of computers in the request scope
		request.setAttribute("computers", listComputers); // Store the list of computers in the request scope
		request.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response); // Forward to JSP page
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
