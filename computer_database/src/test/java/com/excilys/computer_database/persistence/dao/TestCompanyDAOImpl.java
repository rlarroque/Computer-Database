package com.excilys.computer_database.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computer_database.persistence.dao.impl.CompanyDAOImpl;
import com.excilys.computer_database.persistence.dao.utils.DAOUtils;
import com.excilys.computer_database.persistence.model.Company;

@ContextConfiguration(locations = { "classpath:/test-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCompanyDAOImpl {

    // Test queries
    private static final String CREATE_COMPANY_QUERY = "INSERT INTO company (name) values (?)";

    private static final Logger LOGGER = LoggerFactory.getLogger(TestCompanyDAOImpl.class);

    private Connection connection;
    private Statement statement;

    @Autowired
    private CompanyDAOImpl companyDAO;

    @Autowired
    private DataSource dataSource;

    /**
     * Before all tests remove all companies.
     */
    @Before
    public void executeBeforeEachTests() {

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            statement.execute("SET FOREIGN_KEY_CHECKS=0");
            statement.executeUpdate("TRUNCATE company");
            statement.execute("SET FOREIGN_KEY_CHECKS=1");
        } catch (SQLException e) {
            LOGGER.error("Cannot truncate table");
        }
    }

    /**
     * After all tests close the connection and statement.
     */
    @After
    public void executeAfterEachTests() {

        try {
            DAOUtils.closeConnection(connection, null, null);
            DAOUtils.close(statement);
            DAOUtils.close(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot close connection");
        }
    }

    /**
     * Test.
     */
    @Test
    public void testGetCompaniesNone() {

        List<Company> companies = companyDAO.getAll();
        assertEquals(0, companies.size());
    }

    /**
     * Test.
     */
    @Test
    public void testGetCompanies() {

        addDummyCompany();
        addDummyCompany();

        List<Company> companies = companyDAO.getAll();
        assertEquals(2, companies.size());
    }
    
    /**
     * Test.
     */
    @Test
    public void testGetComputerById() {

        addDummyCompany();

        Company company = companyDAO.get(1);
        assertEquals("Dummy company", company.getName());
    }
    
    /**
     * Test.
     */
    @Test
    public void testGetComputerByName() {

        addDummyCompany();

        Company company = companyDAO.get("Dummy company");
        assertEquals("Dummy company", company.getName());
    }
    
    /**
     * Test.
     */
    @SuppressWarnings("unused")
    @Test(expected = EmptyResultDataAccessException.class)
    public void testGetComputerByNameWrong() {
        
        addDummyCompany();
        Company company = companyDAO.get("Not the right dummy company");
    }

    /**
     * Method used to add a dummy company to the test database.
     */
    public void addDummyCompany() {

        try {
            PreparedStatement ps = connection.prepareStatement(CREATE_COMPANY_QUERY);
            ps.setString(1, "Dummy company");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            LOGGER.error("Cannot create dummy company", e);
        }
    }
}
