package com.excilys.computer_database.mapping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computer_database.dto.CompanyDTO;
import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.persistence.db.ConnectionFactory;
import com.excilys.computer_database.persistence.db.utils.DbUtils;
import com.excilys.computer_database.persistence.model.Company;
import com.excilys.computer_database.persistence.model.mapper.CompanyMapper;

public class TestCompanyMapping {

    // Test queries
    private static final String CREATE_COMPANY_QUERY = "INSERT INTO company (name) values (?)";
    private static final String GET_COMPANIES_QUERY = "SELECT * FROM company;";

    // Test Database Information
    public static String test_url;
    public static String test_user;
    public static String test_password;

    private Connection connection;
    private Statement statement;
    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    /**
     * Get connection.
     * @return the connection
     */
    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(test_url, test_user, test_password);
        } catch (SQLException e) {
            logger.error("Cannot connect to the test database");
        }
        return connection;
    }
    
    @BeforeClass
    public static void executeBeforeAllTests() throws IOException{
        Properties prop = new Properties();
        InputStream in = ConnectionFactory.class.getClassLoader()
                .getResourceAsStream("database.properties");
        prop.load(in);
        in.close();
        
        test_url = prop.getProperty("datasource.url");
        test_user = prop.getProperty("datasource.username");
        test_password = prop.getProperty("datasource.password");
    }

    /**
     * Before all tests remove all companies and add a single one.
     */
    @Before
    public void executeBeforeEachTests() {

        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.execute("SET FOREIGN_KEY_CHECKS=0");
            statement.executeUpdate("TRUNCATE company");
            statement.execute("SET FOREIGN_KEY_CHECKS=1");

            PreparedStatement ps = connection.prepareStatement(CREATE_COMPANY_QUERY);
            ps.setString(1, "Dummy Company");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            logger.error("Cannot truncate table");
        }
    }

    /**
     * After all tests close the connection and statement.
     */
    @After
    public void executeAfterEachTests() {

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
    public void testMap() {

        ResultSet resSet;
        try {
            resSet = statement.executeQuery(GET_COMPANIES_QUERY);

            if (resSet.next()) {
                assertEquals("Dummy Company", CompanyMapper.toCompany(resSet).getName());
            } else {
                fail("No ResultSet.");
            }

        } catch (SQLException e) {
            logger.error("Cannot get companuies table");
        }
    }

    /**
     * Test.
     */
    @Test
    public void testCompanyToDTO() {
        Company company = new Company(1, "Dummy Company");
        CompanyDTO dto = CompanyMapper.toDTO(company);
        assertEquals(dto.getName(), company.getName());
    }

    /**
     * Test.
     */
    @SuppressWarnings("unused")
    @Test(expected = IntegrityException.class)
    public void testCompanyToDTOWithNull() {
        Company company = null;
        CompanyDTO dto = CompanyMapper.toDTO(company);
    }

    /**
     * Test.
     */
    @Test
    public void testDtoToCompany() {
        CompanyDTO dto = new CompanyDTO(1, "Dummy Company");
        Company company = CompanyMapper.toCompany(dto);
        assertEquals("Dummy Company", company.getName());
    }

    /**
     * Test.
     */
    @SuppressWarnings("unused")
    @Test(expected = IntegrityException.class)
    public void testDtoToCompanyWithNull() {
        CompanyDTO dto = null;
        Company company = CompanyMapper.toCompany(dto);
    }
}