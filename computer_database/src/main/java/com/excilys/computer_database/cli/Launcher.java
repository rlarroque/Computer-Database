package com.excilys.computer_database.cli;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.computer_database.service.impl.CompanyServiceImpl;
import com.excilys.computer_database.service.impl.ComputerServiceImpl;

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
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        
        ComputerServiceImpl computerService = (ComputerServiceImpl) context.getBean(ComputerServiceImpl.class);
        CompanyServiceImpl companyService = (CompanyServiceImpl) context.getBean(CompanyServiceImpl.class);
        
        CmdLineInterface cli = new CmdLineInterface(computerService, companyService);
        cli.startCmdLineInterface();
    }
}