package com.excilys.computer_database.cli;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Point of entry of the application for CLI launch.
 * @author rlarroque
 */
@SuppressWarnings("resource")
public class Launcher {

	/**
	 * Entry point.
	 * @param args args
	 */
	public static void main(String[] args) {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/console-context.xml");
		CmdLineInterface cli = applicationContext.getBean(CmdLineInterface.class);

		cli.startCmdLineInterface();
	}
}
