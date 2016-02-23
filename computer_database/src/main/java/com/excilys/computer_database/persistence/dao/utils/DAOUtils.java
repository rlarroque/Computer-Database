package com.excilys.computer_database.persistence.dao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computer_database.persistence.db.ConnectionFactory;
import com.excilys.computer_database.persistence.db.utils.DbUtils;

/**
 * Some useful methods used in the DAOs.
 * @author Thuranos
 *
 */
public class DAOUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DAOUtils.class.getName());

    /**
     * Retrieve the connection from the pool in the connection factory.
     * @return the connection
     */
    public static Connection initConnection() {
        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("Cannot deactivate AutoCommit");
        }

        return connection;
    }

    /**
     * Close the parameters passed, connection, statement and result set.
     * @param connection connection to close
     * @param preparedStatement prepared statement to close
     * @param resSet result set to close
     */
    public static void closeConnection(Connection connection, PreparedStatement preparedStatement,
            ResultSet resSet) {

        try {
            DbUtils.close(resSet);
            DbUtils.close(preparedStatement);
            DbUtils.close(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot close connection and statement");
        }
    }
}
