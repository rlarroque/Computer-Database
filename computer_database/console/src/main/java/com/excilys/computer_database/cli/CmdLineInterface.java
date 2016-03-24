package com.excilys.computer_database.cli;

import com.excilys.computer_database.dto.ComputerDTO;
import com.excilys.computer_database.services.CliService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main logic of the command line interface.
 * @author rlarroque
 *
 */
@Component
class CmdLineInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmdLineInterface.class);
    private static final Scanner SC;
    static {
        SC = new Scanner(System.in);
    }

    @Autowired
    private CliService cliService;

    /**
     * Will start an execution of the cmdLine program. The user will be able to pick a command such
     * as displaying a list of computer or creating new ones. The program wont exit unless the user
     * picks the exit command.
     */
    public void startCmdLineInterface() {

        boolean doContinue = true;

        try {
            // loop while 'exit' has not been chosen.
            while (doContinue) {
                displayWelcomeMsg();

                String cmd = SC.next();

                switch (cmd) {
                case "list -company":
                case "1":
                    listCompany();
                    break;

                case "list -computer":
                case "2":
                    listComputer();
                    break;

                case "create":
                case "3":
                    createComputer();
                    break;

                case "update":
                case "4":
                    updateComputer();
                    break;

                case "delete -computer":
                case "5":
                    deleteComputer();
                    break;

                case "delete -company":
                case "6":
                    deleteCompany();
                    break;

                case "details":
                case "7":
                    detailsComputer();
                    break;

                case "exit":
                case "8":
                    doContinue = false;
                    System.out.println("Operation terminated.");
                    break;

                default:
                    break;
                }

                System.out.println("\nPress 'enter' to continue.");
                System.in.read();
            }
        } catch (IOException ioe) {
        	LOGGER.warn(ioe.getMessage());
        } finally {
            SC.close();
        }
    }

    /**
     * Display the help menu.
     */
    private void displayWelcomeMsg() {
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
        System.out.println("        |  8 exit.                                   |");
        System.out.println("        |                                            |");
        System.out.println("        |############################################|");
    }

    /**
     * Print the list of computers.
     */
    private void listComputer() {
        for(ComputerDTO computer : cliService.getAllComputers()) {
            System.out.println(computer);
        }
    }

    /**
     * Print the list of companies.
     */
    private void listCompany() {
        cliService.getAllCompanies();
    }

    /**
     * Create a new computer according to the information asked.
     */
    private void createComputer() {

        ComputerDTO computer = new ComputerDTO();

        computer.setName(askString("Enter the name of the computer"));
        computer.setIntroducedDate(askString("Introducing date, format MM-dd-yyyy"));
        computer.setDiscontinuedDate(askString("Discontinuing date, format MM-dd-yyyy"));
        computer.setCompanyId(askLong("Enter the new company id:"));

        System.out.println("Computer created with id: " + cliService.create(computer));
    }

    /**
     * Delete a computer according to the id entered.
     */
    private void deleteComputer() {
        cliService.delete(askLong("Enter the id of the computer to delete:"));
        System.out.println("Computer deleted successfully!");
    }

    /**
     * Delete a company according to the id entered.
     */
    private void deleteCompany() {
        cliService.deleteByCompany(askLong("Enter the id of the company to delete:"));
        System.out.println("Computer deleted successfully!");
    }

    /**
     * update a computer according to the informations entered by the user.
     */
    private void updateComputer() {

        ComputerDTO computer = cliService.get(askLong("Enter the id of the computer:"));
        System.out.println("Computer retrieved: " + computer);

        computer.setName(askString("Enter the new name:"));
        computer.setIntroducedDate(askString("New introducing date of the computer, format MM-dd-yyyy:"));
        computer.setDiscontinuedDate(askString("New discontinuing date of the computer, format MM-dd-yyyy:"));
        computer.setCompanyId(askLong("Enter the new company tag of the computer:"));

        cliService.update(computer);
    }

    /**
     * Print the details of the computer according to the id entered.
     */
    private void detailsComputer() {
        ComputerDTO computer = cliService.get(askLong("Enter the id of the computer to retrieve:"));
        System.out.println("Computer retrieved: " + computer);
    }

    /**
     * Print a message then get client input.
     * @param msg message to print
     * @return client input
     */
    private String askString(String msg) {

        System.out.println(msg);
        String buffer = null;

        while ("".equals(buffer) || buffer == null) {
            buffer = SC.nextLine();
        }

        return buffer;
    }

    /**
     * Print a message then get client input.
     * @param msg message to print
     * @return client input
     */
    private long askLong(String msg) {

        System.out.println(msg);
        return SC.nextLong();
    }
}