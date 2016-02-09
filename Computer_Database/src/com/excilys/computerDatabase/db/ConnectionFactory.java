package com.excilys.computerDatabase.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class used to manage connection instances to the database. Basically every caller will get a new instance
 * of the database connection and wont return any static variable which is safe enough here.
 * @author excilys
 *
 */
public class ConnectionFactory {
	
	// Static instance of the connectionFactory.
	private static ConnectionFactory instance = new ConnectionFactory();
	
	// Database information
    public static final String URL = "jdbc:mysql://localhost/computer-database-db?zeroDateTimeBehavior=convertToNull";
    public static final String USER = "root";
    public static final String PASSWORD = "qwerty1234";
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 
     
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
     
    /**
     * Create a new connection when called. Every caller will get its own instance.
     * @return a new connection
     */
    private Connection createConnection() {
    	Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }
     
    /**
     * Call the createConnection method that will return a new connection to the db.
     * @return the new connection
     */
    public static Connection getConnection() {
    	return instance.createConnection();
    }
}
