package com.excilys.computer_database.main;

import java.io.IOException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computer_database.dao.CompanyDAO;
import com.excilys.computer_database.dao.ComputerDAO;
import com.excilys.computer_database.exception.IntegrityException;

public class CmdLineInterface {

	private Logger logger = LoggerFactory.getLogger(getClass().getName());
	private ComputerDAO computerDAO;
	private CompanyDAO companyDAO;
	private CLIUtils utils;

	private static final Scanner sc;

	static {
		sc = new Scanner(System.in);
	}

	public CmdLineInterface(ComputerDAO computerService, CompanyDAO companyService) {
		this.computerDAO = computerService;
		this.companyDAO = companyService;
		utils = new CLIUtils(sc);
	}

	/**
	 * Will start an execution of the cmdLine program. The user will be able to
	 * pick a command such as displaying a list of computer or creating new
	 * ones. The program wont exit unless the user picks the exit command.
	 */
	public void startCmdLineInterface() {

		boolean doContinue = true;

		try {
			// loop while 'exit' has not been chosen.
			while (doContinue) {
				utils.displayWelcomeMsg();

				String cmd = sc.next();

				switch (cmd) {
				case "list -company":
				case "1":
					utils.listCompany(companyDAO);
					break;

				case "list -computer":
				case "2":
					utils.listComputer(computerDAO);
					break;

				case "create":
				case "3":
					utils.createComputer(computerDAO);
					break;

				case "update":
				case "4":
					utils.updateComputer(computerDAO);
					break;

				case "delete":
				case "5":
					utils.deleteComputer(computerDAO);
					break;

				case "details":
				case "6":
					utils.detailsComputer(computerDAO);
					break;

				case "exit":
				case "7":
					doContinue = false;
					System.out.println("Operation terminated.");
					break;

				default:
					break;
				}

				System.out.println("\nPress 'enter' to continue.");
				System.in.read();
			}
			
		} catch (IntegrityException ie) {
			logger.warn("An integrity exception happend: " + ie.getMessage());
		} catch (IOException ioe) {
			logger.warn(ioe.getMessage());
		} finally {
			sc.close();			
		}
	}
}