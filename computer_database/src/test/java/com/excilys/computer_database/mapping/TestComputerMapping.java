package com.excilys.computer_database.mapping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computer_database.db.DbUtils;
import com.excilys.computer_database.dto.ComputerDTO;
import com.excilys.computer_database.model.Company;
import com.excilys.computer_database.model.Computer;
import com.excilys.computer_database.model.mapper.ComputerMapper;

public class TestComputerMapping {

	// Test queries
	private static final String CREATE_COMPANY_QUERY = "INSERT INTO company (name) values (?)";
	private static final String GET_COMPUTER_BY_NAME_QUERY = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id where computer.name=?";
	private static final String CREATE_COMPUTER_QUERY = "INSERT INTO computer (name, introduced, discontinued, company_id) values (?, ?, ?, ?)";

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

			ps = connection.prepareStatement(CREATE_COMPUTER_QUERY);
			ps.setString(1, "Dummy computer");
			ps.setTimestamp(2, Timestamp.valueOf("2000-01-01 00:00:00"));
			ps.setTimestamp(3, Timestamp.valueOf("2001-01-01 00:00:00"));
			ps.setInt(4, 1);
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
	public void testResultSetToComputer() {

		ResultSet resSet;

		try {
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(GET_COMPUTER_BY_NAME_QUERY);
			ps.setString(1, "Dummy computer");
			resSet = ps.executeQuery();

			LocalDate intro = LocalDate.of(2000, 1, 1);
			LocalDate disc = LocalDate.of(2001, 1, 1);

			if (resSet.next()) {
				Computer computer = ComputerMapper.toComputer(resSet);

				assertEquals("Dummy computer", computer.getName());
				assertEquals(intro, computer.getIntroduced());
				assertEquals(disc, computer.getDiscontinued());
				assertEquals("Dummy Company", computer.getCompany().getName());
			} else {
				fail("No ResultSet.");
			}

		} catch (SQLException e) {
			fail("Cannot get companies table");
		}
	}

	@Test
	public void testComputerToDTO() {

		Company company = new Company(1, "Dummy Company");
		Computer computer = new Computer("Dummy Computer");
		computer.setIntroduced(LocalDate.of(2000, 1, 1));
		computer.setDiscontinued(LocalDate.of(2001, 1, 1));
		computer.setCompany(company);

		ComputerDTO dto = ComputerMapper.toDTO(computer);

		assertEquals(dto.getName(), computer.getName());
		assertEquals(dto.getIntroducedDate(), computer.getIntroduced().toString());
		assertEquals(dto.getDiscontinuedDate(), computer.getDiscontinued().toString());
		assertEquals(dto.getCompanyName(), computer.getCompany().getName());
	}

	@Test
	public void testComputerToDTOWithNull() {

		Computer computer = new Computer("Dummy Computer");

		ComputerDTO dto = ComputerMapper.toDTO(computer);

		assertEquals(dto.getName(), computer.getName());
		assertEquals(dto.getIntroducedDate(), "");
		assertEquals(dto.getDiscontinuedDate(), "");
		assertEquals(dto.getCompanyName(), "");
	}

	@Test
	public void testDtoToComputer() {
		ComputerDTO dto = new ComputerDTO();
		dto.setName("Dummy Computer");
		dto.setIntroducedDate("2000-01-01");
		dto.setDiscontinuedDate("2001-01-01");
		dto.setCompanyId(1);
		dto.setCompanyName("Dummy Company");

		Computer computer = ComputerMapper.toComputer(dto);

		assertEquals(computer.getName(), dto.getName());
		assertEquals(computer.getIntroduced().toString(), dto.getIntroducedDate());
		assertEquals(computer.getDiscontinued().toString(), dto.getDiscontinuedDate());
		assertEquals(computer.getCompany().getName(), dto.getCompanyName());
	}

	@Test
	public void testDtoToComputerWithNullDate() {
		ComputerDTO dto = new ComputerDTO();
		dto.setName("Dummy Computer");
		dto.setIntroducedDate("");
		dto.setDiscontinuedDate("");
		dto.setCompanyId(0);
		dto.setCompanyName("");

		Computer computer = ComputerMapper.toComputer(dto);

		assertEquals(computer.getName(), dto.getName());
		assertEquals(computer.getIntroduced(), null);
		assertEquals(computer.getDiscontinued(), null);
		assertEquals(computer.getCompany(), null);
	}

	@Test
	public void testDtoToComputerWithNullTime() {
		ComputerDTO dto = new ComputerDTO();
		dto.setName("Dummy Computer");
		dto.setIntroducedDate("2000-01-01");
		dto.setDiscontinuedDate("2001-01-01");
		dto.setCompanyId(0);

		Computer computer = ComputerMapper.toComputer(dto);

		assertEquals(computer.getName(), dto.getName());
		assertEquals(computer.getIntroduced(), LocalDate.of(2000, 1, 1));
		assertEquals(computer.getDiscontinued(), LocalDate.of(2001, 1, 1));
		assertEquals(computer.getCompany(), null);
	}
}
