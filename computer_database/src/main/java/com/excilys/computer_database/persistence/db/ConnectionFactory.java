package com.excilys.computer_database.persistence.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computer_database.exception.ConnectionException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

/**
 * Class used to manage connection instances to the database. Basically every caller will get a new instance of the database connection and wont
 * return any static variable which is safe enough here.
 * @author rlarroque
 *
 */
public class ConnectionFactory {

    private static class TransactionManager extends ThreadLocal<Connection> {

        @Override
        protected Connection initialValue() {

            Connection connection = null;

            try {
                connection = connectionPool.getConnection();
            } catch (SQLException e) {
                LOGGER.error("Cannot get any connection!!! " + e.getMessage());
                throw new ConnectionException("Cannot get any connection");
            }

            return connection;
        }

        @Override
        public void remove() {

            try {
                get().close();
            } catch (SQLException e) {
                LOGGER.error("Cannot close connection!!! " + e.getMessage());
                throw new ConnectionException("Cannot close connection.");
            } finally {
                super.remove();
            }
        }
    }

    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionFactory.class.getName());
    private static ConnectionFactory instance = new ConnectionFactory();
    private TransactionManager transactionMngr;
    private static BoneCP connectionPool;

    /**
     * Get the singleton instance of the connection factory.
     * @return the instance
     */
    public static ConnectionFactory getInstance() {
        return instance;
    }

    /**
     * Constructor.
     */
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
            boneConfig.setPartitionCount(3);
            boneConfig.setMaxConnectionsPerPartition(2);

            connectionPool = new BoneCP(boneConfig);

            transactionMngr = new TransactionManager();

            LOGGER.info("Connected to the database");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Cannot load the drive in ConnectionFactory!!! " + e.getMessage());
            throw new ConnectionException("Cannot load the drive in ConnectionFactory");
        } catch (IOException e) {
            LOGGER.error("Connection setup failed in ConnectionFactory due to preperty file reading!!! " + e.getMessage());
            throw new ConnectionException("Connection setup failed in ConnectionFactory due to preperty file reading");
        } catch (SQLException e) {
            LOGGER.error("Connection setup failed in ConnectionFactory due to coonection pool issues!!! " + e.getMessage());
            throw new ConnectionException("Connection setup failed in ConnectionFactory due to coonection pool issues");
        }
    }

    /**
     * Call the createConnection method that will return a new connection to the db.
     * @return the new connection
     */
    public static Connection getConnection() {
        return getInstance().transactionMngr.get();
    }

    /**
     * Call the createConnection method that will return a new connection to the db with autocommit set to false.
     * @return the connection
     * @throws SQLException thrown in case of SQL issues
     */
    public static Connection getConnectionNoCommit() throws SQLException {
        Connection connection = getInstance().transactionMngr.get();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("Cannot set autocommit to false!!! " + e.getMessage());
            throw new SQLException();
        }

        return connection;
    }

    /**
     * Close the connection by removing it from the ThreadLocal Manager.
     */
    public static void closeConnection() {
        getInstance().transactionMngr.remove();
    }
}
