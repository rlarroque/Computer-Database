package com.excilys.computer_database.main;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.persistence.dao.CompanyDAO;
import com.excilys.computer_database.persistence.dao.ComputerDAO;
import com.excilys.computer_database.persistence.model.Company;
import com.excilys.computer_database.persistence.model.Computer;

/**
 * Utility methods that can be used in the CLI to modify and read the db.
 * 
 * @author rlarroque
 *
 */
public class CLIUtils {

	private Scanner sc;

	public CLIUtils(Scanner sc) {
		this.sc = sc;
	}

	/**
	 * Display the help menu.
	 */
	protected void displayWelcomeMsg() {
		System.out.println("		|############################################|");
		System.out.println("		| Welcome to the fabulous Computer-Database! |");
		System.out.println("		| Here are the available commands:           |");
		System.out.println("		|############################################|");
		System.out.println("		|                                            |");
		System.out.println("		|  1 list -company.                          |");
		System.out.println("		|  2 list -computer.                         |");
		System.out.println("		|  3 create.                                 |");
		System.out.println("		|  4 update.                                 |");
		System.out.println("		|  5 delete.                                 |");
		System.out.println("		|  6 details.                                |");
		System.out.println("		|  7 exit.\n                                 |");
		System.out.println("		|                                            |");
		System.out.println("		|############################################|");
	}

	/**
	 * Print the list of computers.
	 * 
	 * @param computerService
	 */
	protected void listComputer(ComputerDAO computerService) {
		List<Computer> computers = computerService.getAll();

		for (Computer computer : computers) {
			System.out.println(computer);
		}
	}

	/**
	 * Print the list of companies.
	 * 
	 * @param companyService
	 */
	protected void listCompany(CompanyDAO companyService) {
		List<Company> companies = companyService.getAll();

		for (Company company : companies) {
			System.out.println(company);
		}
	}

	/**
	 * Create a new computer according to the informations asked.
	 * 
	 * @param computerService
	 */
	protected void createComputer(ComputerDAO computerService) throws IntegrityException {

		try {
			sc.nextLine();

			Boolean before = true;
			String buffer = null;
			Timestamp discontinuedDate = null;
			Timestamp IntroducedDate = null;

			System.out.println("Enter the name of the computer:");
			buffer = sc.nextLine();
			Computer computer = new Computer(buffer);

			System.out
					.println("Introduction date of the computer, format YYYY-MM-DD (press 'enter' for current date):");
			buffer = sc.nextLine();

			if ("".equals(buffer)) {
				IntroducedDate = new Timestamp(new java.util.Date().getTime());
			} else {

				try {
					IntroducedDate = Timestamp.valueOf(buffer.concat(" 00:00:00"));
				} catch (IllegalArgumentException iae) {
					System.out.println("Not a good format for a date. Current date has been chosen.");
					IntroducedDate = new Timestamp(new java.util.Date().getTime());
				}
			}

			//computer.setIntroduced(IntroducedDate.toLocalDateTime());

			do {

				System.out.println(
						"Discontinuing date of the computer, format YYYY-MM-DD (press 'enter' for current date):");
				buffer = sc.nextLine();

				if ("".equals(buffer)) {
					discontinuedDate = new Timestamp(new java.util.Date().getTime());
				} else {

					try {
						discontinuedDate = Timestamp.valueOf(buffer.concat(" 00:00:00"));
					} catch (IllegalArgumentException iae) {
						System.out.println("Not a good format for a date. Current date has been chosen.");
						discontinuedDate = new Timestamp(new java.util.Date().getTime());
					}
				}

				if (discontinuedDate.before(IntroducedDate)) {
					System.out.println("This date is invalid because earlier than the introduction date");
				} else {
					before = false;
				}

			} while (before);

			//computer.setDiscontinued(discontinuedDate.toLocalDateTime());

			System.out.println("Enter the company tag of the computer:");
			int company_id = sc.nextInt();

			Company company = new Company();
			company.setId(company_id);

			computer.setCompany(company);

			computerService.create(computer);

			sc.nextLine();

		} catch (IntegrityException ie) {
			throw ie;
		}
	}

	/**
	 * Delete a computer according to the id entered.
	 * 
	 * @param computerService
	 */
	protected void deleteComputer(ComputerDAO computerService) throws IntegrityException {

		try {
			System.out.println("Enter the id of the computer to delete:");
			int id = sc.nextInt();

			computerService.delete(id);

		} catch (IntegrityException ie) {
			throw ie;
		}
	}

	/**
	 * update a computer according to the informations entered by the user.
	 * 
	 * @param computerService
	 */
	protected void updateComputer(ComputerDAO computerService) throws IntegrityException {

		try {
			Boolean before = true;
			String buffer = null;
			Timestamp discontinuedDate = null;
			Timestamp IntroducedDate = null;

			System.out.println("Enter the id of the computer:");
			int bufferInt = sc.nextInt();
			sc.nextLine();

			Computer computer = computerService.get(bufferInt);

			System.out.print("Computer retrieved: ");
			System.out.println(computer);

			System.out.println("Enter the new name of the computer (press 'enter' to keep the old name):");
			buffer = sc.nextLine();

			if (!"".equals(buffer)) {
				computer.setName(buffer);
			}

			System.out.println(
					"New introducing date of the computer, format YYYY-MM-DD (press 'enter' to keep the old date):");
			buffer = sc.nextLine();

			if (!"".equals(buffer)) {

				try {
					IntroducedDate = Timestamp.valueOf(buffer.concat(" 00:00:00"));
					//computer.setIntroduced(IntroducedDate.toLocalDateTime());
				} catch (IllegalArgumentException iae) {
					System.out.println("Not a good format for a date. Date hasn't been modified.");
				}
			}

			do {

				System.out.println(
						"New discontinuing date of the computer, format YYYY-MM-DD (press 'enter' to keep the old date):");
				buffer = sc.nextLine();

				if (!"".equals(buffer)) {

					try {
						discontinuedDate = Timestamp.valueOf(buffer.concat(" 00:00:00"));
						//computer.setDiscontinued(discontinuedDate.toLocalDateTime());
					} catch (IllegalArgumentException iae) {
						System.out.println("Not a good format for a date. Date hasn't been modified.");
					}
				}

				if (discontinuedDate.before(IntroducedDate)) {
					System.out.println("This date is invalid because earlier than the introduction date");
				} else {
					before = false;
				}

			} while (before);

			System.out.println("Enter the new company tag of the computer (press 'enter' to keep the old one):");
			buffer = sc.nextLine();

			if (!"".equals(buffer)) {

				try {
					// computer.setCompany_id(Integer.parseInt(buffer));
				} catch (NumberFormatException nfe) {
					System.out.println("NumberFormatException: " + nfe.getMessage());
				}
			}

			computerService.update(computer);

		} catch (IntegrityException ie) {
			throw ie;
		}
	}

	/**
	 * Print the details of the computer according to the id entered.
	 * 
	 * @param computerService
	 */
	protected void detailsComputer(ComputerDAO computerService) throws IntegrityException {

		try {
			System.out.println("Enter the id of the computer to retrieve:");
			int id = sc.nextInt();

			Computer computer = computerService.get(id);
			System.out.println(computer);

		} catch (IntegrityException ie) {
			throw ie;
		}
	}
}
