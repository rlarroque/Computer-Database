package com.excilys.computer_database.cli;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.computer_database.services.CompanyService;
import com.excilys.computer_database.services.ComputerService;

/**
 * Point of entry of the application for CLI launch.
 * @author rlarroque
 *
 */
public class Launcher {

    /**
     * Entry point.
     * @param args args
     */
    public static void main(String[] args) {
        
        CmdLineInterface cli = null;
       
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml")) {
            ComputerService computerService = context.getBean(ComputerService.class);
            CompanyService companyService = context.getBean(CompanyService.class);            
            
            cli = new CmdLineInterface(computerService, companyService);
        }
        
        cli.startCmdLineInterface();
    }
}