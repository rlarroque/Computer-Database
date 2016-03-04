package com.excilys.computer_database.mapping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.persistence.dao.utils.DAOUtils;
import com.excilys.computer_database.persistence.model.Company;
import com.excilys.computer_database.persistence.model.mapper.CompanyMapper;
import com.excilys.computer_database.webapp.dto.CompanyDTO;

@ContextConfiguration(locations = { "classpath:/test-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCompanyMapping {

    // Test queries
    private static final String CREATE_COMPANY_QUERY = "INSERT INTO company (name) values (?)";
    private static final String GET_COMPANIES_QUERY = "SELECT * FROM company;";
    private static final Logger LOGGER = LoggerFactory.getLogger(TestCompanyMapping.class);

    private Connection connection;
    private Statement statement;

    @Autowired
    private DataSource dataSource;

    /**
     * Before all tests remove all companies and add a single one.
     */
    @Before
    public void executeBeforeEachTests() {

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            statement.execute("SET FOREIGN_KEY_CHECKS=0");
            statement.executeUpdate("TRUNCATE company");
            statement.execute("SET FOREIGN_KEY_CHECKS=1");

            PreparedStatement ps = connection.prepareStatement(CREATE_COMPANY_QUERY);
            ps.setString(1, "Dummy Company");
            ps.executeUpdate();
            ps.close();
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
            DAOUtils.close(statement);
            DAOUtils.close(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot close connection!!!", e);
        }
    }

    /**
     * Test.
     */
    @Test
    public void testResultSetToCompany() {

        ResultSet resSet;
        try {
            resSet = statement.executeQuery(GET_COMPANIES_QUERY);

            if (resSet.next()) {
                assertEquals("Dummy Company", CompanyMapper.toCompany(resSet).getName());
            } else {
                fail("No ResultSet.");
            }

        } catch (SQLException e) {
            LOGGER.error("Cannot get companies table");
        }
    }

    /**
     * Test.
     */
    @Test
    public void testCompanyToDTO() {

        Company company = new Company(1l, "Dummy Company");
        CompanyDTO dto = CompanyMapper.toDTO(company);

        assertEquals(dto.getName(), company.getName());
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void testCompanyToDTOWithNull() {

        Company company = null;

        @SuppressWarnings("unused")
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
    @Test(expected = IntegrityException.class)
    public void testDtoToCompanyWithNull() {

        CompanyDTO dto = null;

        @SuppressWarnings("unused")
        Company company = CompanyMapper.toCompany(dto);
    }
}