package com.excilys.computerDatabase.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Some useful methods used to close connections, statements or ResultSets.
 * @author excilys
 *
 */
public class DbUtils {
	
	/**
	 * Close a passed connection.
	 * @param connection the connection to be closed
	 */
	public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
    			e.printStackTrace();
            }
        }
    }
 
	/**
	 * Close a passed statement.
	 * @param statement the statement to be closed
	 */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
    			e.printStackTrace();
            }
        }
    }
 
    /**
     * Close a passed resultSet.
     * @param resultSet the resultSet to be closed
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
    			e.printStackTrace();
            }
        }
    }

}
