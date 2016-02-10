package com.excilys.computer_database.main;

import com.excilys.computer_database.service.CompanyService;
import com.excilys.computer_database.service.ComputerService;
import com.excilys.computer_database.service.impl.CompanyServiceImpl;
import com.excilys.computer_database.service.impl.ComputerServiceImpl;

public class Launcher {

	public static void main(String[] args) {

		ComputerService computerService = ComputerServiceImpl.getInstance();
		CompanyService companyService = CompanyServiceImpl.getInstance();

		CmdLineInterface CLI = new CmdLineInterface(computerService, companyService);
		CLI.startCmdLineInterface();
	}
}