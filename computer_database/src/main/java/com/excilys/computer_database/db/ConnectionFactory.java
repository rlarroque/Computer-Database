package com.excilys.computer_database.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.computer_database.exception.ConnectionException;

/**
 * Class used to manage connection instances to the database. Basically every
 * caller will get a new instance of the database connection and wont return any
 * static variable which is safe enough here.
 * 
 * @author excilys
 *
 */
public class ConnectionFactory {

	// Static instance of the connectionFactory.
	private static ConnectionFactory instance = new ConnectionFactory();

	// Database driver information
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

	private ConnectionFactory() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			throw new ConnectionException("Cannot load the drive in ConnectionFactory");
		}
	}

	/**
	 * Create a new connection when called. Every caller will get its own
	 * instance.
	 * 
	 * @return a new connection
	 */
	private Connection createConnection() {
		Connection connection = null;
		try {

			// open and read the properties file
			Properties prop = new Properties();
			InputStream in = ConnectionFactory.class.getClassLoader().getResourceAsStream("database.properties");
			prop.load(in);
			in.close();

			// get the properties
			String connectionURL = prop.getProperty("datasource.url");
			String username = prop.getProperty("datasource.username");
			String password = prop.getProperty("datasource.password");

			// open the database connection
			connection = DriverManager.getConnection(connectionURL, username, password);

		} catch (SQLException sqle) {
			throw new ConnectionException("Connection to the database failed in ConnectionFactory due to sql issues");
		} catch (IOException e) {
			throw new ConnectionException("Connection to the database failed in ConnectionFactory due to preperty file reading");
		}
		return connection;
	}

	/**
	 * Call the createConnection method that will return a new connection to the
	 * db.
	 * 
	 * @param test
	 *            is set as true if you want to use the test database
	 * @return the new connection
	 */
	public static Connection getConnection() {
		return instance.createConnection();
	}
}
