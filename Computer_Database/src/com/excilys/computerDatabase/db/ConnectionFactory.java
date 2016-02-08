package com.excilys.computerDatabse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static ConnectionFactory instance = new ConnectionFactory();
    public static final String URL = "jdbc:mysql://localhost/computer-database-db?zeroDateTimeBehavior=convertToNull";
    public static final String USER = "root";
    public static final String PASSWORD = "qwerty1234";
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 
     
    //private constructor
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
     
    private Connection createConnection() {
    	Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }   
    
    /* private static void closeConnection(){
    	Connection connection = getConnection();
    	
    	try {
    		if(connection!=null)
                connection.close();
        }catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR: Unable to close the connection to Database.");
		} 
    } */
     
    public static Connection getConnection() {
    	return instance.createConnection();
    }
}
