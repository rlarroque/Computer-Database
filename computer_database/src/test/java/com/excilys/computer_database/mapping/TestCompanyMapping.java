package com.excilys.computer_database.mapping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computer_database.db.DbUtils;
import com.excilys.computer_database.dto.CompanyDTO;
import com.excilys.computer_database.dto.ComputerDTO;
import com.excilys.computer_database.model.Company;
import com.excilys.computer_database.model.Computer;

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
	public void executeBeforeAllTests() {

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
	public void executerApresChaqueTest() {

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
				assertEquals("Dummy Company", CompanyMapper.map(resSet).getName());
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
		CompanyDTO dto = CompanyMapper.companyToDTO(company);
		assertEquals(dto.getName(), company.getName());
	}

	@Test
	public void testCompanyToDTOWithNull() {

		Company company = new Company(1, "");
		CompanyDTO dto = CompanyMapper.companyToDTO(company);
		assertEquals("", dto.getName());

		company = new Company(1, null);
		dto = CompanyMapper.companyToDTO(company);
		assertEquals("", dto.getName());

		company = null;
		dto = CompanyMapper.companyToDTO(company);
		assertEquals(null, dto);
	}

	@Test
	public void testDtoToCompany() {
		CompanyDTO dto = new CompanyDTO("Dummy Company");
		Company company = CompanyMapper.dtoToCompany(dto);
		assertEquals("Dummy Company", company.getName());
	}

	@Test
	public void testDtoToCompanyWithNull() {
		CompanyDTO dto = new CompanyDTO("");
		Company company = CompanyMapper.dtoToCompany(dto);
		assertEquals(null, company);
		
		dto = new CompanyDTO();
		company = CompanyMapper.dtoToCompany(dto);
		assertEquals(null, company);
		
		dto = null;
		company = CompanyMapper.dtoToCompany(dto);
		assertEquals(null, company);
	}
}