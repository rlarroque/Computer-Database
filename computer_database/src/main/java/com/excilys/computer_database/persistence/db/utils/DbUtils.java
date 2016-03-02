package com.excilys.computer_database.persistence.db.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.excilys.computer_database.persistence.db.ConnectionFactory;

/**
 * Some useful methods used to close connections, statements or ResultSets.
 * @author rlarroque
 *
 */
public class DbUtils {

    /**
     * Close a passed connection.
     * @param connection the connection to be closed
     */
    public static void close(Connection connection) {
        if (connection != null) {
            ConnectionFactory.closeConnection();
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
                throw sqle;
            }
        }
    }
}
