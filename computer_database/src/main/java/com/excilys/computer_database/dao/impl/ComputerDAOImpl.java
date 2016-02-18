package com.excilys.computer_database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computer_database.persistence.dao.ComputerDAO;
import com.excilys.computer_database.persistence.db.ConnectionFactory;
import com.excilys.computer_database.persistence.db.utils.DbUtils;
import com.excilys.computer_database.persistence.model.Computer;
import com.excilys.computer_database.persistence.model.mapper.ComputerMapper;

/**
 * Implementation of ComputerDAO that is used to manipulate the db.
 * @author rlarroque
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
	private static final String COUNT_COMPUTER_QUERY = "SELECT COUNT(*) from computer";

	private Logger logger = LoggerFactory.getLogger(getClass().getName());
	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resSet;

	public static ComputerDAOImpl getInstance() {
		if (instance == null) {
			instance = new ComputerDAOImpl();
		}

		return instance;
	}

	/**
	 * Initial the connection and the statement. To be called at the beginning of each queries.
	 */
	private void initConnection() {

		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
		} catch (SQLException e) {
			logger.error("Cannot connect to the DB. " + e.getMessage());
		}
	}

	/**
	 * Close the resultSet, statement and connection. To be called at the end of each queries.
	 */
	private void closeConnection() {

		try {
			DbUtils.close(resSet);
			DbUtils.close(statement);
			DbUtils.close(preparedStatement);
			DbUtils.close(connection);
		} catch (SQLException e) {
			logger.error("Cannot close the connection to the DB. " + e.getMessage());
		}
	}

	@Override
	public List<Computer> getAll() {

		initConnection();

		List<Computer> computers = new ArrayList<Computer>();

		try {
			resSet = statement.executeQuery(GET_COMPUTER_QUERY);

			while (resSet.next()) {
				computers.add(ComputerMapper.toComputer(resSet));
			}

		} catch (SQLException e) {
			logger.error("Cannot get computers. " + e.getMessage());
		} finally {
			closeConnection();
		}

		return computers;
	}

	@Override
	public List<Computer> getPage(int startIndex, int offset) {

		initConnection();

		List<Computer> computers = new ArrayList<Computer>();

		try {
			
			preparedStatement = connection.prepareStatement(GET_COMPUTER_PAGE_QUERY);
			preparedStatement.setInt(1, offset);
			preparedStatement.setInt(2, startIndex);
			resSet = preparedStatement.executeQuery();

			while (resSet.next()) {
				computers.add(ComputerMapper.toComputer(resSet));
			}

		} catch (SQLException e) {
			logger.error("Cannot get computers. " + e.getMessage());
		} finally {
			closeConnection();
		}

		return computers;
	}

	@Override
	public Computer get(int id) {

		initConnection();

		Computer computer = null;

		try {
			preparedStatement = connection.prepareStatement(GET_COMPUTER_BY_ID_QUERY);
			preparedStatement.setInt(1, id);
			resSet = preparedStatement.executeQuery();

			if (resSet.next()) {
				computer = ComputerMapper.toComputer(resSet);
			}

		} catch (SQLException e) {
			logger.error("Cannot get computer by id. " + e.getMessage());
		} finally {
			closeConnection();
		}

		return computer;
	}

	@Override
	public Computer get(String name) {

		initConnection();

		Computer computer = null;

		try {
			preparedStatement = connection.prepareStatement(GET_COMPUTER_BY_NAME_QUERY);
			preparedStatement.setString(1, name);
			resSet = preparedStatement.executeQuery();

			if (resSet.next()) {
				computer = ComputerMapper.toComputer(resSet);
			}

		} catch (SQLException e) {
			logger.error("Cannot get computer by name. " + e.getMessage());
		} finally {
			closeConnection();
		}

		return computer;
	}

	@Override
	public int create(Computer c) {

		initConnection();

		int resultKey = 0;

		try {
			preparedStatement = connection.prepareStatement(CREATE_COMPUTER_QUERY, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, c.getName());
			
			if (c.getIntroduced() == null) {
				preparedStatement.setObject(2, null);
			} else {
				preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(c.getIntroduced(), LocalTime.of(0, 0))));
			}

			if (c.getDiscontinued() == null) {
				preparedStatement.setObject(3, null);
			} else {
				preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.of(c.getDiscontinued(), LocalTime.of(0, 0))));
			}
			
			if(c.getCompany() == null){
				preparedStatement.setObject(4, null);
			} else{
				preparedStatement.setInt(4, c.getCompany().getId());
			}
			
			preparedStatement.executeUpdate();

			resSet = preparedStatement.getGeneratedKeys();

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
	public void update(Computer c) {

		initConnection();

		try {
			preparedStatement = connection.prepareStatement(UPDATE_COMPUTER_QUERY);
			preparedStatement.setString(1, c.getName());
			preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(c.getIntroduced(), LocalTime.of(0, 0))));
			preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.of(c.getDiscontinued(), LocalTime.of(0, 0))));
			preparedStatement.setInt(4, c.getCompany().getId());
			preparedStatement.setInt(5, c.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.error("Cannot update computer. " + e.getMessage());
		} finally {
			closeConnection();
		}
	}

	@Override
	public void delete(int id) {

		initConnection();

		try {
			preparedStatement = connection.prepareStatement(DELETE_COMPUTER_QUERY);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.error("Cannot delete computer. " + e.getMessage());
		} finally {
			closeConnection();
		}
	}

	@Override
	public int count() {
		
		int computerNumber = 0;
		
		initConnection();
		
		try{
			resSet = statement.executeQuery(COUNT_COMPUTER_QUERY);
			
			if(resSet.next()){
				computerNumber = resSet.getInt(1);
			}
		} catch(SQLException e){
		
			logger.error("Cannot count computers. " + e.getMessage());
		} finally {
			closeConnection();
		}
		
		return computerNumber;
	}
}
