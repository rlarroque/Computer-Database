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
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computer_database.db.DbUtils;
import com.excilys.computer_database.dto.ComputerDTO;
import com.excilys.computer_database.model.Company;
import com.excilys.computer_database.model.Computer;

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

			LocalDateTime intro = LocalDateTime.of(2000, 1, 1, 0, 0);
			LocalDateTime disc = LocalDateTime.of(2001, 1, 1, 0, 0);

			if (resSet.next()) {
				Computer computer = ComputerMapper.resultSetToComputer(resSet);

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
		computer.setIntroduced(LocalDateTime.of(2000, 1, 1, 0, 0, 0));
		computer.setDiscontinued(LocalDateTime.of(2001, 1, 1, 0, 0, 0));
		computer.setCompany(company);

		ComputerDTO dto = ComputerMapper.computerToDTO(computer);

		assertEquals(dto.getName(), computer.getName());
		assertEquals(dto.getIntroducedDate(), computer.getIntroduced().toLocalDate().toString());
		assertEquals(dto.getIntroducedTime(), computer.getIntroduced().toLocalTime().toString());
		assertEquals(dto.getDiscontinuedDate(), computer.getDiscontinued().toLocalDate().toString());
		assertEquals(dto.getDiscontinuedTime(), computer.getDiscontinued().toLocalTime().toString());
		assertEquals(dto.getCompany_name(), computer.getCompany().getName());
	}

	@Test
	public void testComputerToDTOWithNull() {

		Computer computer = new Computer("Dummy Computer");
		computer.setIntroduced(null);
		computer.setDiscontinued(null);
		computer.setCompany(null);

		ComputerDTO dto = ComputerMapper.computerToDTO(computer);

		assertEquals(dto.getName(), computer.getName());
		assertEquals(dto.getIntroducedDate(), "");
		assertEquals(dto.getIntroducedTime(), "");
		assertEquals(dto.getDiscontinuedDate(), "");
		assertEquals(dto.getDiscontinuedTime(), "");
		assertEquals(dto.getCompany_name(), "");
	}

	@Test
	public void testDtoToComputer() {
		ComputerDTO dto = new ComputerDTO();
		dto.setName("Dummy Computer");
		dto.setIntroducedDate("2000-01-01");
		dto.setIntroducedTime("00:00");
		dto.setDiscontinuedDate("2001-01-01");
		dto.setDiscontinuedTime("00:00");
		dto.setCompany_name("Dummy Company");

		Computer computer = ComputerMapper.dtoToComputer(dto);

		assertEquals(computer.getName(), dto.getName());
		assertEquals(computer.getIntroduced().toLocalDate().toString(), dto.getIntroducedDate());
		assertEquals(computer.getIntroduced().toLocalTime().toString(), dto.getIntroducedTime());
		assertEquals(computer.getDiscontinued().toLocalDate().toString(), dto.getDiscontinuedDate());
		assertEquals(computer.getDiscontinued().toLocalTime().toString(), dto.getDiscontinuedTime());
		assertEquals(computer.getCompany().getName(), dto.getCompany_name());
	}

	@Test
	public void testDtoToComputerWithNullDate() {
		ComputerDTO dto = new ComputerDTO();
		dto.setName("Dummy Computer");
		dto.setIntroducedDate("");
		dto.setIntroducedTime("");
		dto.setDiscontinuedDate("");
		dto.setDiscontinuedTime("");
		dto.setCompany_name("");

		Computer computer = ComputerMapper.dtoToComputer(dto);

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
		dto.setIntroducedTime("");
		dto.setDiscontinuedDate("2001-01-01");
		dto.setDiscontinuedTime("");
		dto.setCompany_name("");

		Computer computer = ComputerMapper.dtoToComputer(dto);

		assertEquals(computer.getName(), dto.getName());
		assertEquals(computer.getIntroduced(), LocalDateTime.of(2000, 1, 1, 0, 0, 0));
		assertEquals(computer.getDiscontinued(), LocalDateTime.of(2001, 1, 1, 0, 0, 0));
		assertEquals(computer.getCompany(), null);
	}
}
