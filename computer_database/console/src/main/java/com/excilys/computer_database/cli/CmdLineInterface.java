package com.excilys.computer_database.cli;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.services.CompanyService;
import com.excilys.computer_database.services.ComputerService;

/**
 * Main logic of the command line interface.
 * @author rlarroque
 *
 */
@Component
public class CmdLineInterface {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(CmdLineInterface.class);
    private static final Scanner SC;

    static {
        SC = new Scanner(System.in);
    }
    
    private CLIUtils utils;

    @Autowired
    private ComputerService computerService;
    
    @Autowired
    private CompanyService companyService;

    /**
     * Constructor.
     * @param computerService computer service
     * @param companyService company service
     */
    public CmdLineInterface() {
        utils = new CLIUtils(SC);
    }

    /**
     * Will start an execution of the cmdLine program. The user will be able to pick a command such
     * as displaying a list of computer or creating new ones. The program wont exit unless the user
     * picks the exit command.
     * @throws SQLException in case of SQL issue
     */
    public void startCmdLineInterface() {

        boolean doContinue = true;

        try {
            // loop while 'exit' has not been chosen.
            while (doContinue) {
                utils.displayWelcomeMsg();

                String cmd = SC.next();

                switch (cmd) {
                case "list -company":
                case "1":
                    utils.listCompany(companyService);
                    break;

                case "list -computer":
                case "2":
                    utils.listComputer(computerService);
                    break;

                case "create":
                case "3":
                    utils.createComputer(computerService);
                    break;

                case "update":
                case "4":
                    utils.updateComputer(computerService);
                    break;

                case "delete -computer":
                case "5":
                    utils.deleteComputer(computerService);
                    break;

                case "delete -company":
                case "6":
                    utils.deleteCompany(companyService);
                    break;

                case "details":
                case "7":
                    utils.detailsComputer(computerService);
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

        } catch (IntegrityException ie) {
        	LOGGER.warn("An integrity exception happend: " + ie.getMessage());
        } catch (IOException ioe) {
        	LOGGER.warn(ioe.getMessage());
        } finally {
            SC.close();
        }
    }
}