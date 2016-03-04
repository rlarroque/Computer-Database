package com.excilys.computer_database.persistence.dao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Some useful methods used in the DAOs.
 * @author Thuranos
 *
 */
public class DAOUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DAOUtils.class.getName());

    /**
     * Close the parameters passed, connection, statement and result set.
     * @param connection connection to close
     * @param preparedStatement prepared statement to close
     * @param resSet result set to close
     */
    public static void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resSet) {

        try {
            close(resSet);
            close(preparedStatement);
            close(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot close connection and statement");
        }
    }
    
    /**
     * Close a passed connection.
     * @param connection the connection to be closed
     * @throws SQLException 
     */
    public static void close(Connection connection) throws SQLException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException sqle) {
                LOGGER.error("Cannot close connection!!!", sqle);
                throw sqle;
            }
        }
    }

    /**
     * Close a passed statement.
     * @param statement the statement to be closed
     * @throws SQLException thrown in case of SQL issues
     */
    public static void close(Statement statement) throws SQLException {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException sqle) {
                LOGGER.error("Cannot close statement!!!", sqle);
                throw sqle;
            }
        }
    }

    /**
     * Close a passed resultSet.
     * @param resultSet the resultSet to be closed
     * @throws SQLException thrown in case of SQL issues
     */
    public static void close(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException sqle) {
                LOGGER.error("Cannot close resultset!!!", sqle);
                throw sqle;
            }
        }
    }

    /**
     * Close a passed prepared statement.
     * @param preparedStatement prepared statement to close
     * @throws SQLException thrown in case of SQL issues
     */
    public static void close(PreparedStatement preparedStatement) throws SQLException {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException sqle) {
                LOGGER.error("Cannot close prepared statement!!!", sqle);
                throw sqle;
            }
        }
    }
}
