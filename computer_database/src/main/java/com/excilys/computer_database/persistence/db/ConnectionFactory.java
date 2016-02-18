package com.excilys.computer_database.persistence.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.computer_database.exception.ConnectionException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

/**
 * Class used to manage connection instances to the database. Basically every
 * caller will get a new instance of the database connection and wont return any
 * static variable which is safe enough here.
 * @author rlarroque
 *
 */
public class ConnectionFactory {

	// Static instance of the connectionFactory.
	private static ConnectionFactory instance = new ConnectionFactory();

	// Connection pool
	private static BoneCP connectionPool;

	// Database driver information
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

	private ConnectionFactory() {
		try {
			Class.forName(DRIVER_CLASS);

			// open and read the properties file, then close it.
			Properties prop = new Properties();
			InputStream in = ConnectionFactory.class.getClassLoader().getResourceAsStream("database.properties");
			prop.load(in);
			in.close();

			BoneCPConfig boneConfig = new BoneCPConfig();
			boneConfig.setJdbcUrl(prop.getProperty("datasource.url"));
			boneConfig.setUsername(prop.getProperty("datasource.username"));
			boneConfig.setPassword(prop.getProperty("datasource.password"));
			boneConfig.setMaxConnectionsPerPartition(2);
			
			connectionPool = new BoneCP(boneConfig);
			
		} catch (ClassNotFoundException e) {
			throw new ConnectionException("Cannot load the drive in ConnectionFactory");
		} catch (IOException e) {
			throw new ConnectionException("Connection setup failed in ConnectionFactory due to preperty file reading");
		} catch (SQLException e) {
			throw new ConnectionException("Connection setup failed in ConnectionFactory due to coonection pool issues");
		}
	}

	/**
	 * Get a new conncetion in the pool. Every caller will get its own
	 * instance.
	 * @return a new connection
	 */
	private Connection createConnection() {
		
		Connection connection = null;
		
		try {
			connection = connectionPool.getConnection();
		} catch (SQLException e) {
			throw new ConnectionException("Cannot get any connection");
		}

		return connection;
	}

	/**
	 * Call the createConnection method that will return a new connection to the
	 * db.
	 * @param test is set as true if you want to use the test database
	 * @return the new connection
	 */
	public static Connection getConnection() {
		return instance.createConnection();
	}
}
