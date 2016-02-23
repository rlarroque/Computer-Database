package com.excilys.computer_database.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computer_database.persistence.dao.impl.CompanyDAOImpl;
import com.excilys.computer_database.persistence.db.ConnectionFactory;
import com.excilys.computer_database.persistence.db.utils.DbUtils;
import com.excilys.computer_database.persistence.model.Company;

public class TestCompanyDAOImpl {

    // Test queries
    private static final String CREATE_COMPANY_QUERY = "INSERT INTO company (name) values (?)";

    private CompanyDAOImpl companyDAO;
    private Connection connection;
    private Statement statement;
    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    /**
     * Before all tests remove all companies.
     */
    @Before
    public void executeBeforeEachTests() {
        companyDAO = CompanyDAOImpl.getInstance();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            statement.execute("SET FOREIGN_KEY_CHECKS=0");
            statement.executeUpdate("TRUNCATE company");
            statement.execute("SET FOREIGN_KEY_CHECKS=1");
        } catch (SQLException e) {
            logger.error("Cannot truncate table");
        }
    }

    /**
     * After all tests close the connection and statement.
     */
    @After
    public void executeAfterEachTests() {
        companyDAO = null;

        try {
            DbUtils.close(statement);
            DbUtils.close(connection);
        } catch (SQLException e) {
        }
    }

    /**
     * Test.
     */
    @Test
    public void testGetCompanies() {
        Connection mconnection = null;

        List<Company> companies = companyDAO.getAll();
        assertEquals(0, companies.size());

        try {
            mconnection = ConnectionFactory.getConnection();
            PreparedStatement ps = mconnection.prepareStatement(CREATE_COMPANY_QUERY);
            ps.setString(1, "Dummy Company");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Cannot connect to the test database");
        } finally {

            try {
                companies = companyDAO.getAll();
                assertEquals(1, companies.size());

                mconnection.close();
            } catch (SQLException e) {
                logger.debug("Cannot close connection!");
            }
        }
    }
}
