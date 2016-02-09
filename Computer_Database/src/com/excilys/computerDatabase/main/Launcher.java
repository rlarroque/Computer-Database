package com.excilys.computerDatabase.main;

import com.excilys.computerDatabase.dao.CompanyJDBCTemplate;
import com.excilys.computerDatabase.dao.ComputerJDBCTemplate;
import com.excilys.computerDatabase.dao.interfaces.CompanyDAO;
import com.excilys.computerDatabase.dao.interfaces.ComputerDAO;

public class Launcher {

	public static void main(String[] args) {
		
		ComputerDAO computerTemplate = new ComputerJDBCTemplate();
		CompanyDAO companyTemplate = new CompanyJDBCTemplate();
		
		CmdLineInterface CLI = new CmdLineInterface(computerTemplate, companyTemplate);
		CLI.startCmdLineInterface();
	}
}