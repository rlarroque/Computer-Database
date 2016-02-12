package com.excilys.computer_database.main;

import com.excilys.computer_database.dao.CompanyDAO;
import com.excilys.computer_database.dao.ComputerDAO;
import com.excilys.computer_database.dao.impl.CompanyDAOImpl;
import com.excilys.computer_database.dao.impl.ComputerDAOImpl;
import com.excilys.computer_database.service.CompanyService;
import com.excilys.computer_database.service.ComputerService;
import com.excilys.computer_database.service.impl.CompanyServiceImpl;
import com.excilys.computer_database.service.impl.ComputerServiceImpl;

public class Launcher {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		ComputerService computerService = ComputerServiceImpl.getInstance();
		CompanyService companyService = CompanyServiceImpl.getInstance();
		
		ComputerDAO computerDAO = ComputerDAOImpl.getInstance();
		CompanyDAO companyDAO = CompanyDAOImpl.getInstance();

		CmdLineInterface CLI = new CmdLineInterface(computerDAO, companyDAO);
		CLI.startCmdLineInterface();
	}
}