package com.excilys.computer_database.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computer_database.dao.ComputerDAO;
import com.excilys.computer_database.db.ConnectionFactory;
import com.excilys.computer_database.db.DbUtils;
import com.excilys.computer_database.mapping.ComputerMapper;
import com.excilys.computer_database.model.Computer;
import com.mysql.jdbc.PreparedStatement;

/**
 * Implementation of ComputerDAO that is used to manipulate the db.
 * 
 * @author excilys
 */
public class ComputerDAOImpl implements ComputerDAO {

	private static ComputerDAOImpl instance;

	// SQL Queries
	private static final String GET_COMPUTER_PAGE_QUERY = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id LIMIT ? OFFSET ?";
	private static final String GET_COMPUTER_QUERY = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id";
	private static final String GET_COMPUTER_BY_ID_QUERY = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id where computer.id=?";
	private static final String GET_COMPUTER_BY_NAME_QUERY = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id where computer.name=?";
	private static final String CREATE_COMPUTER_QUERY = "INSERT INTO computer (name, introduced, discontinued, company_id) values (?, ?, ?, ?)";
	private static final String UPDATE_COMPUTER_QUERY = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? where id = ?";
	private static final String DELETE_COMPUTER_QUERY = "DELETE FROM computer where id = ?";

	private Logger logger = LoggerFactory.getLogger(getClass().getName());
	private Connection connection;
	private Statement statement;
	private ResultSet resSet;

	public static ComputerDAOImpl getInstance() {
		if (instance == null) {
			instance = new ComputerDAOImpl();
		}

		return instance;
	}

	private void initConnection() {

		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
		} catch (SQLException e) {
			logger.error("Cannot connect to the DB. " + e.getMessage());
		}
	}

	private void closeConnection() {

		try {
			DbUtils.close(resSet);
			DbUtils.close(statement);
			DbUtils.close(connection);
		} catch (SQLException e) {
			logger.error("Cannot close the connection to the DB. " + e.getMessage());
		}
	}

	@Override
	public List<Computer> getComputers() {

		initConnection();

		List<Computer> computers = new ArrayList<Computer>();

		try {
			resSet = statement.executeQuery(GET_COMPUTER_QUERY);

			while (resSet.next()) {
				computers.add(ComputerMapper.resultSetToComputer(resSet));
			}

		} catch (SQLException e) {
			logger.error("Cannot get computers. " + e.getMessage());
		} finally {
			closeConnection();
		}

		return computers;
	}

	@Override
	public List<Computer> getComputersPage(int number, int startIndex) {

		initConnection();

		List<Computer> computers = new ArrayList<Computer>();

		try {

			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(GET_COMPUTER_PAGE_QUERY);
			ps.setInt(1, number);
			ps.setInt(2, startIndex);
			resSet = ps.executeQuery();

			while (resSet.next()) {
				computers.add(ComputerMapper.resultSetToComputer(resSet));
			}

		} catch (SQLException e) {
			logger.error("Cannot get computers. " + e.getMessage());
		} finally {
			closeConnection();
		}

		return computers;
	}

	@Override
	public Computer getComputer(int id) {

		initConnection();

		Computer computer = null;

		try {
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(GET_COMPUTER_BY_ID_QUERY);
			ps.setInt(1, id);
			resSet = ps.executeQuery();

			if (resSet.next()) {
				computer = ComputerMapper.resultSetToComputer(resSet);
			}

		} catch (SQLException e) {
			logger.error("Cannot get computer by id. " + e.getMessage());
		} finally {
			closeConnection();
		}

		return computer;
	}

	@Override
	public Computer getComputer(String name) {

		initConnection();

		Computer computer = null;

		try {
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(GET_COMPUTER_BY_NAME_QUERY);
			ps.setString(1, name);
			resSet = ps.executeQuery();

			if (resSet.next()) {
				computer = ComputerMapper.resultSetToComputer(resSet);
			}

		} catch (SQLException e) {
			logger.error("Cannot get computer by name. " + e.getMessage());
		} finally {
			closeConnection();
		}

		return computer;
	}

	@Override
	public int createComputer(Computer c) {

		initConnection();

		int resultKey = 0;

		try {
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(CREATE_COMPUTER_QUERY,
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, c.getName());
			
			if (c.getIntroduced() == null) {
				ps.setObject(2, null);
			} else {
				ps.setTimestamp(2, Timestamp.valueOf(c.getIntroduced()));
			}

			if (c.getDiscontinued() == null) {
				ps.setObject(3, null);
			} else {
				ps.setTimestamp(3, Timestamp.valueOf(c.getDiscontinued()));
			}
			
			if(c.getCompany() == null){
				ps.setObject(4, null);
			} else{
				ps.setInt(4, c.getCompany().getId());
			}
			
			ps.executeUpdate();

			resSet = ps.getGeneratedKeys();

			if (resSet.next()) {
				resultKey = resSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("Cannot create computer. " + e.getMessage());
		} finally {
			closeConnection();
		}

		return resultKey;
	}

	@Override
	public void updateComputer(Computer c) {

		initConnection();

		try {
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(UPDATE_COMPUTER_QUERY);
			ps.setString(1, c.getName());
			ps.setTimestamp(2, Timestamp.valueOf(c.getIntroduced()));
			ps.setTimestamp(3, Timestamp.valueOf(c.getDiscontinued()));
			ps.setInt(4, c.getCompany().getId());
			ps.setInt(5, c.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("Cannot update computer. " + e.getMessage());
		} finally {
			closeConnection();
		}
	}

	@Override
	public void deleteComputer(int id) {

		initConnection();

		try {
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(DELETE_COMPUTER_QUERY);
			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("Cannot delete computer. " + e.getMessage());
		} finally {
			closeConnection();
		}
	}
}
