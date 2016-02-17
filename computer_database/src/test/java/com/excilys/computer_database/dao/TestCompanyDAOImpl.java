package com.excilys.computer_database.dao;

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

import com.excilys.computer_database.dao.impl.CompanyDAOImpl;
import com.excilys.computer_database.db.ConnectionFactory;
import com.excilys.computer_database.db.DbUtils;
import com.excilys.computer_database.model.Company;

public class TestCompanyDAOImpl {

	// Test queries
	private static final String CREATE_COMPANY_QUERY = "INSERT INTO company (name) values (?)";

	private CompanyDAOImpl companyDAO;
	private Connection connection;
	private Statement statement;
	private Logger logger = LoggerFactory.getLogger(getClass().getName());


	@Before
	public void executeBeforeEachTests() {
		companyDAO = CompanyDAOImpl.getInstance();

		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			statement.execute("SET FOREIGN_KEY_CHECKS=0");
			statement.executeUpdate("TRUNCATE company");
			statement.execute("SET FOREIGN_KEY_CHECKS=0");
		} catch (SQLException e) {
			logger.error("Cannot truncate table");
		}
	}

	@After
	public void executeAfterEachTests() {
		companyDAO = null;

		try {
			DbUtils.close(statement);
			DbUtils.close(connection);
		} catch (SQLException e) {
		}
	}

	@Test
	public void testGetCompanies() {
		List<Company> companies = companyDAO.getAll();
		assertEquals(0, companies.size());

		try {
			PreparedStatement ps = connection.prepareStatement(CREATE_COMPANY_QUERY);
			ps.setString(1, "Dummy Company");
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			logger.error("Cannot connect to the test database");
		}

		companies = companyDAO.getAll();
		assertEquals(1, companies.size());
	}
}
