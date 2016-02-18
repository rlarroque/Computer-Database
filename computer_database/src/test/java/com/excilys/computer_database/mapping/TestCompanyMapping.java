package com.excilys.computer_database.mapping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computer_database.dto.CompanyDTO;
import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.persistence.db.utils.DbUtils;
import com.excilys.computer_database.persistence.model.Company;
import com.excilys.computer_database.persistence.model.mapper.CompanyMapper;

public class TestCompanyMapping {

	// Test queries
	private static final String CREATE_COMPANY_QUERY = "INSERT INTO company (name) values (?)";
	private static final String GET_COMPANIES_QUERY = "SELECT * FROM company;";

	// Test Database Information
	public static final String TEST_URL = "jdbc:mysql://localhost/test_database?zeroDateTimeBehavior=convertToNull";
	public static final String TEST_USER = "root";
	public static final String TEST_PASSWORD = "qwerty1234";

	private Connection connection;
	private Statement statement;
	private Logger logger = LoggerFactory.getLogger(getClass().getName());

	private Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(TEST_URL, TEST_USER, TEST_PASSWORD);
		} catch (SQLException e) {
			logger.error("Cannot connect to the test database");
		}
		return connection;
	}

	@Before
	public void executeBeforeEachTests() {

		try {
			connection = getConnection();
			statement = connection.createStatement();
			statement.execute("SET FOREIGN_KEY_CHECKS=0");
			statement.executeUpdate("TRUNCATE company");
			statement.execute("SET FOREIGN_KEY_CHECKS=0");

			PreparedStatement ps = connection.prepareStatement(CREATE_COMPANY_QUERY);
			ps.setString(1, "Dummy Company");
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			logger.error("Cannot truncate table");
		}
	}

	@After
	public void executeAfterEachTests() {

		try {
			DbUtils.close(statement);
			DbUtils.close(connection);
		} catch (SQLException e) {
		}
	}

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

	@Test
	public void testCompanyToDTO() {
		Company company = new Company(1, "Dummy Company");
		CompanyDTO dto = CompanyMapper.toDTO(company);
		assertEquals(dto.getName(), company.getName());
	}

	@SuppressWarnings("unused")
	@Test(expected = IntegrityException.class)
	public void testCompanyToDTOWithNull() {
		Company company = null;
		CompanyDTO dto = CompanyMapper.toDTO(company);
	}

	@Test
	public void testDtoToCompany() {
		CompanyDTO dto = new CompanyDTO(1, "Dummy Company");
		Company company = CompanyMapper.toCompany(dto);
		assertEquals("Dummy Company", company.getName());
	}

	@SuppressWarnings("unused")
	@Test(expected = IntegrityException.class)
	public void testDtoToCompanyWithNull() {		
		CompanyDTO dto = null;
		Company company = CompanyMapper.toCompany(dto);
	}
}