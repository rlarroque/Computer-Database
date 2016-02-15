package servlet;

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
import com.excilys.computer_database.service.impl.CompanyServiceImpl;
import com.excilys.computer_database.service.impl.ComputerServiceImpl;

@WebServlet(name="AddComputer", urlPatterns="/addComputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CompanyDTO> listCompanies = new ArrayList<>();
		
		CompanyServiceImpl compService = CompanyServiceImpl.getInstance();
		
		listCompanies = compService.getCompanies();
		
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
		
		ComputerServiceImpl compService = ComputerServiceImpl.getInstance();
		compService.createComputer(dto);
		
		response.sendRedirect("displayComputers?page=1&offset=10"); // redirect to JSP page
	}

}
