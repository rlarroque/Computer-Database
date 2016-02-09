package com.excilys.computerDatabase.main;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import javax.naming.directory.InvalidAttributesException;

import com.excilys.computerDatabase.dao.interfaces.CompanyDAO;
import com.excilys.computerDatabase.dao.interfaces.ComputerDAO;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.sun.xml.internal.ws.addressing.model.InvalidAddressingHeaderException;

public class CmdLineInterface {
	
	private ComputerDAO computerDAO;
	private CompanyDAO companyDAO;
	
	private static final Scanner sc;
	static{
		sc = new Scanner(System.in);
	}
	
	public CmdLineInterface(ComputerDAO computerDAO, CompanyDAO companyDAO) {
		this.computerDAO = computerDAO;
		this.companyDAO = companyDAO;
	}
	
	/**
	 * Will start an execution of the cmdLine program. The user will be able to pick a command such as displaying a list
	 * of computer or creating new ones. The program wont exit unless the user picks the exit command.
	 */
	public void startCmdLineInterface() {
		
		boolean doContinue = true;
		
		// loop while 'exit' has not been chosen.s
		while(doContinue)
		{
			displayWelcomeMsg();
			
			String cmd = sc.next();
			
			switch (cmd) {
			case "list -company":
			case "1":
				listCompany(companyDAO);
				break;
				
			case "list -computer":
			case "2":
				listComputer(computerDAO);
				break;
				
			case "create":
			case "3":
				createComputer(computerDAO);
				break;
				
			case "update":
			case "4":
				updateComputer(computerDAO);
				break;
				
			case "delete":
			case "5":
				deleteComputer(computerDAO);
				break;
				
			case "details":
			case "6":
				detailsComputer(computerDAO);
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
			try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		sc.close();
	}

	/**
	 * Display the help menu.
	 */
	public static void displayWelcomeMsg(){
		System.out.println("Welcome to the fabulous Computer-Database!");
		System.out.println("Here are the available commands:");
		System.out.println("  1 list -company.");
		System.out.println("  2 list -computer.");
		System.out.println("  3 create.");
		System.out.println("  4 update.");
		System.out.println("  5 delete.");
		System.out.println("  6 details.");
		System.out.println("  7 exit.\n");
	}
	
	public static void listComputer(ComputerDAO computerTemplate){
		List <Computer> computers = computerTemplate.getComputers();
        
        for (Computer computer : computers) {
			System.out.println(computer);
		}
	}
	
	public static void listCompany(CompanyDAO companyTemplate){
		List <Company> companies = companyTemplate.getCompanies();
        
        for (Company company : companies) {
			System.out.println(company);
		}
	}
	
	public static void createComputer(ComputerDAO computerTemplate){
		
		sc.nextLine();
		
		Boolean before = true;
		String buffer = null;
		Timestamp discontinuedDate = null;
		Timestamp IntroducedDate = null;
		
		System.out.println("Enter the name of the computer:");		
		buffer = sc.nextLine();
		Computer computer = new Computer(buffer);
		
		System.out.println("Introduction date of the computer, format YYYY-MM-DD (press 'enter' for current date):");
		buffer = sc.nextLine();
		
		if("".equals(buffer)){
			IntroducedDate = new Timestamp(new java.util.Date().getTime());
		} else{
			
			try{
				IntroducedDate = Timestamp.valueOf(buffer.concat(" 00:00:00"));
			} catch(IllegalArgumentException iae){
				System.out.println("Not a good format for a date. Current date has been chosen.");
				IntroducedDate = new Timestamp(new java.util.Date().getTime());
			}
		}
		
		computer.setIntroduced(IntroducedDate);
		
		do {
			
			System.out.println("Discontinuing date of the computer, format YYYY-MM-DD (press 'enter' for current date):");
			buffer = sc.nextLine();
					
			if("".equals(buffer)){
				discontinuedDate = new Timestamp(new java.util.Date().getTime());
			} else{
				
				try{
					discontinuedDate = Timestamp.valueOf(buffer.concat(" 00:00:00"));
				} catch(IllegalArgumentException iae){
					System.out.println("Not a good format for a date. Current date has been chosen.");
					discontinuedDate = new Timestamp(new java.util.Date().getTime());
				}
			}
			
			if(discontinuedDate.before(IntroducedDate)){
				System.out.println("This date is invalid because earlier than the introduction date");
			} else{
				before = false;
			}
			
		} while (before);
		
		computer.setDiscontinued(discontinuedDate);
		
		System.out.println("Enter the company tag of the computer:");		
		int company_id = sc.nextInt();
		
		computer.setCompany_id(company_id);
		
		computerTemplate.createComputer(computer);
		
		sc.nextLine();
	}
	
	private static void deleteComputer(ComputerDAO computerTemplate) {
		System.out.println("Enter the id of the computer to delete:");		
		int id = sc.nextInt();
		
		computerTemplate.deleteComputer(id);
	}

	private static void updateComputer(ComputerDAO computerTemplate) {
		
		Boolean before = true;
		String buffer = null;
		Timestamp discontinuedDate = null;
		Timestamp IntroducedDate = null;
		
		System.out.println("Enter the id of the computer:");		
		int bufferInt = sc.nextInt();
		sc.nextLine();
		Computer computer = computerTemplate.getComputer(bufferInt);
		
		System.out.print("Computer retrieved: ");	
		System.out.println(computer);
		
		System.out.println("Enter the new name of the computer (press 'enter' to keep the old name):");
		buffer = sc.nextLine();
		
		if(! "".equals(buffer)){
			computer.setName(buffer);
		}
		
		System.out.println("New introducing date of the computer, format YYYY-MM-DD (press 'enter' to keep the old date):");
		buffer = sc.nextLine();
		
		if(! "".equals(buffer)){
			
			try{
				IntroducedDate = Timestamp.valueOf(buffer.concat(" 00:00:00"));
				computer.setIntroduced(IntroducedDate);
			} catch(IllegalArgumentException iae){
				System.out.println("Not a good format for a date. Date hasn't been modified.");
			}
		}
		
		do {
			
			System.out.println("New discontinuing date of the computer, format YYYY-MM-DD (press 'enter' to keep the old date):");
			buffer = sc.nextLine();
					
			if(! "".equals(buffer)){
				
				try{
					discontinuedDate = Timestamp.valueOf(buffer.concat(" 00:00:00"));
					computer.setDiscontinued(discontinuedDate);
				} catch(IllegalArgumentException iae){
					System.out.println("Not a good format for a date. Date hasn't been modified.");
				}
			}
			
			if(discontinuedDate.before(IntroducedDate)){
				System.out.println("This date is invalid because earlier than the introduction date");
			} else{
				before = false;
			}
			
		} while (before);
			
		System.out.println("Enter the new company tag of the computer (press 'enter' to keep the old one):");		
		buffer = sc.nextLine();
		
		if(! "".equals(buffer)){
			
			try{
				computer.setCompany_id(Integer.parseInt(buffer));
			} catch (NumberFormatException nfe)
		    {
				System.out.println("NumberFormatException: " + nfe.getMessage());
		    }
		}
		
		computerTemplate.updateComputer(computer);
	}
	
	private static void detailsComputer(ComputerDAO computerTemplate) {
		System.out.println("Enter the id of the computer to retrieve:");
		int id = sc.nextInt();
		
		Computer computer = computerTemplate.getComputer(id);
		System.out.println(computer);
	}
}
