package com.excilys.computer_database.cli;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.model.Company;
import com.excilys.computer_database.model.Computer;
import com.excilys.computer_database.services.CompanyService;
import com.excilys.computer_database.services.ComputerService;

/**
 * Utility methods that can be used in the CLI to modify and read the db.
 * @author rlarroque
 *
 */
public class CLIUtils {

    private Scanner sc;

    /**
     * Constructor.
     * @param sc scanner to type inputs
     */
    public CLIUtils(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Display the help menu.
     */
    protected void displayWelcomeMsg() {
        System.out.println("        |############################################|");
        System.out.println("        | Welcome to the fabulous Computer-Database! |");
        System.out.println("        | Here are the available commands:           |");
        System.out.println("        |############################################|");
        System.out.println("        |                                            |");
        System.out.println("        |  1 list -company.                          |");
        System.out.println("        |  2 list -computer.                         |");
        System.out.println("        |  3 create.                                 |");
        System.out.println("        |  4 update.                                 |");
        System.out.println("        |  5 delete computer.                        |");
        System.out.println("        |  6 delete company.                         |");
        System.out.println("        |  7 details.                                |");
        System.out.println("        |  8 exit.\n                                 |");
        System.out.println("        |                                            |");
        System.out.println("        |############################################|");
    }

    /**
     * Print the list of computers.
     * @param computerService service to pass
     */
    protected void listComputer(ComputerService computerService) {
        List<Computer> computers = computerService.getAll();

        for (Computer computer : computers) {
            System.out.println(computer);
        }
    }

    /**
     * Print the list of companies.
     * @param companyService service to pass
     */
    protected void listCompany(CompanyService companyService) {
        List<Company> companies = companyService.getAll();

        for (Company company : companies) {
            System.out.println(company);
        }
    }

    /**
     * Create a new computer according to the informations asked.
     * @param computerService service to pass
     * @exception IntegrityException thrown if integrity is not correct
     */
    protected void createComputer(ComputerService computerService) throws IntegrityException {

        try {

            Boolean before = true;
            Computer computer = new Computer();

            computer.setName(askString("Enter the name of the computer"));

            try {
                computer.setIntroduced(LocalDate.parse(askString("New introducing date of the computer, format YYYY-MM-DD")));
            } catch (IllegalArgumentException iae) {
                System.out.println("Not a good format for a date.");
            }

            do {
                try {
                    computer.setDiscontinued(LocalDate.parse(askString("New discontinuing date of the computer, format YYYY-MM-DD")));
                } catch (IllegalArgumentException iae) {
                    System.out.println("Not a good format for a date.");
                }

                if (computer.getDiscontinued().isBefore(computer.getIntroduced())) {
                    System.out.println("This date is invalid because earlier than the introduction date");
                } else {
                    before = false;
                }

            } while (before);

            Company company = new Company();
            company.setId((long) askInt("Enter the new company tag of the computer:"));
            computer.setCompany(company);

            computerService.create(computer);

        } catch (IntegrityException ie) {
            throw ie;
        }
    }

    /**
     * Delete a computer according to the id entered.
     * @param computerService service to pass
     * @exception IntegrityException thrown if integrity is not correct
     */
    protected void deleteComputer(ComputerService computerService) throws IntegrityException {

        try {
            computerService.delete(askInt("Enter the id of the computer to delete:"));
        } catch (IntegrityException ie) {
            throw ie;
        }
    }

    /**
     * Delete a company according to the id entered.
     * @param companyService service to pass
     * @exception IntegrityException thrown if integrity is not correct
     */
    protected void deleteCompany(CompanyService companyService) throws IntegrityException {

        try {
            companyService.delete(askInt("Enter the id of the company to delete:"));
        } catch (IntegrityException ie) {
            throw ie;
        }
    }

    /**
     * update a computer according to the informations entered by the user.
     * @param computerService service to pass
     * @exception IntegrityException thrown if integrity is not correct
     */
    protected void updateComputer(ComputerService computerService) throws IntegrityException {

        try {
            Boolean before = true;

            Computer computer = computerService.get(askInt("Enter the id of the computer:"));

            System.out.println("Computer retrieved: " + computer);

            computer.setName(askString(
                    "Enter the new name of the computer (press 'enter' to keep the old name):"));

            try {
                computer.setIntroduced(LocalDate.parse(askString(
                        "New introducing date of the computer, format YYYY-MM-DD (press 'enter' to keep the old date):")));
            } catch (IllegalArgumentException iae) {
                System.out.println("Not a good format for a date. Date hasn't been modified.");
            }

            do {

                try {
                    computer.setDiscontinued(LocalDate.parse(askString(
                            "New discontinuing date of the computer, format YYYY-MM-DD (press 'enter' to keep the old date):")));
                } catch (IllegalArgumentException iae) {
                    System.out.println("Not a good format for a date. Date hasn't been modified.");
                }

                if (computer.getDiscontinued().isBefore(computer.getIntroduced())) {
                    System.out.println(
                            "This date is invalid because earlier than the introduction date");
                } else {
                    before = false;
                }

            } while (before);

            Company company = new Company();
            company.setId((long) (askInt(
                    "Enter the new company tag of the computer (press 'enter' to keep the old one):")));
            computer.setCompany(company);

            computerService.update(computer);

        } catch (IntegrityException ie) {
            throw ie;
        }
    }

    /**
     * Print the details of the computer according to the id entered.
     * @param computerService service to pass
     * @exception IntegrityException thrown if integrity is not correct
     */
    protected void detailsComputer(ComputerService computerService) throws IntegrityException {

        try {
            Computer computer = computerService.get(askInt("Enter the id of the computer to retrieve:"));
            System.out.println(computer);

        } catch (IntegrityException ie) {
            throw ie;
        }
    }

    /**
     * Print a message then get client input.
     * @param msg message to print
     * @return client input
     */
    protected String askString(String msg) {

        System.out.println(msg);
        String buffer = null;

        while ("".equals(buffer) || buffer == null) {
            buffer = sc.nextLine();
        }

        return buffer;
    }

    /**
     * Print a message then get client input.
     * @param msg message to print
     * @return client input
     */
    protected String askStringUnprotected(String msg) {

        System.out.println(msg);
        String buffer = sc.nextLine();

        return buffer;
    }

    /**
     * Print a message then get client input.
     * @param msg message to print
     * @return client input
     */
    protected int askInt(String msg) {

        System.out.println(msg);
        int buffer = sc.nextInt();

        return buffer;
    }
}
