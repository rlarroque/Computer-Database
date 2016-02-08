package com.excilys.computerDatabase.main;

import java.util.List;
import java.util.Scanner;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabse.dao.CompanyJDBCTemplate;
import com.excilys.computerDatabse.dao.ComputerJDBCTemplate;
import java.sql.Timestamp;

public class CmdLineInterface {
	
	public static void main(String[] args) {
		
		boolean doContinue = true;
		ComputerJDBCTemplate computerTemplate = new ComputerJDBCTemplate();
		CompanyJDBCTemplate companyTemplate = new CompanyJDBCTemplate();
		
		Scanner sc = new Scanner(System.in);
		
		while(doContinue)
		{
			displayWelcomeMsg();
			
			String cmd = sc.nextLine();
			
			switch (cmd) {
			case "list -company":
			case "1":
				listCompany(companyTemplate);
				break;
				
			case "list -computer":
			case "2":
				listComputer(computerTemplate);
				break;
				
			case "create":
			case "3":
				createComputer(sc, computerTemplate);
				break;
				
			case "update":
			case "4":
				updateComputer(sc, computerTemplate);
				break;
				
			case "delete":
			case "5":
				deleteComputer(sc, computerTemplate);
				break;
				
			case "details":
			case "6":
				detailsComputer(sc, computerTemplate);
				break;
				
			case "exit":
			case "7":
				doContinue = false;
				System.out.println("Operation terminated.");
				break;

			default:
				break;
			}
		}
		
		sc.close();
	}

	public static void displayWelcomeMsg(){
		System.out.println("\nWelcome to the fabulous Computer-Database!");
		System.out.println("Here are the available commands:");
		System.out.println("	1 list -computer. To display all the computers.");
		System.out.println("	2 list -company. To display all the companies.");
		System.out.println("	3 create. To create a new computer.");
		System.out.println("	4 update. To update an existing computer;");
		System.out.println("	5 delete. To delete an existing computer.");
		System.out.println("	6 details. To display a computer's details.");
		System.out.println("	7 exit. To exit the application.\n");
	}
	
	public static void listComputer(ComputerJDBCTemplate computerTemplate){
		List <Computer> computers = computerTemplate.getComputers();
        
        for (Computer computer : computers) {
			System.out.println(computer);
		}
	}
	
	public static void listCompany(CompanyJDBCTemplate companyTemplate){
		List <Company> companies = companyTemplate.getCompanies();
        
        for (Company company : companies) {
			System.out.println(company);
		}
	}
	
	public static void createComputer(Scanner sc, ComputerJDBCTemplate computerTemplate){
		
		String buffer = null;
		Timestamp discontinuedDate = null;
		Timestamp IntroducedDate = null;
		
		System.out.println("Enter the name of the computer:");		
		buffer = sc.nextLine();
		Computer computer = new Computer(buffer);
		
		System.out.println("Enter the introducing date of the computer (press 'enter' for current date):");
		buffer = sc.nextLine();
		
		if("".equals(buffer)){
			IntroducedDate = new Timestamp(new java.util.Date().getTime());
		} else{
			IntroducedDate = Timestamp.valueOf(buffer);
		}
		
		computer.setIntroduced(IntroducedDate);
		
		System.out.println("Enter the discontinuing date of the computer (press 'enter' for current date):");
		buffer = sc.nextLine();
				
		if("".equals(buffer)){
			discontinuedDate = new Timestamp(new java.util.Date().getTime());
		} else{
			discontinuedDate = Timestamp.valueOf(buffer);
		}
		
		computer.setDiscontinued(discontinuedDate);
		
		System.out.println("Enter the company tag of the computer:");		
		int company_id = sc.nextInt();
		
		computer.setCompany_id(company_id);
		
		computerTemplate.createComputer(computer);
	}
	
	private static void deleteComputer(Scanner sc, ComputerJDBCTemplate computerTemplate) {
		System.out.println("Enter the id of the computer to delete:");		
		int id = sc.nextInt();
		
		computerTemplate.deleteComputer(id);
	}

	private static void updateComputer(Scanner sc, ComputerJDBCTemplate computerTemplate) {
		
	}
	
	private static void detailsComputer(Scanner sc, ComputerJDBCTemplate computerTemplate) {
		
	}
}
