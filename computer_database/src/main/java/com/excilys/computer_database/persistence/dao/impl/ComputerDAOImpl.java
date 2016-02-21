package com.excilys.computer_database.persistence.dao.impl;

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
import com.excilys.computer_database.persistence.dao.utils.DAOUtils;
import com.excilys.computer_database.persistence.model.Computer;
import com.excilys.computer_database.persistence.model.Page;
import com.excilys.computer_database.persistence.model.mapper.ComputerMapper;

/**
 * Implementation of ComputerDAO that is used to manipulate the db.
 * @author rlarroque
 */
public class ComputerDAOImpl implements ComputerDAO {

	private static ComputerDAOImpl instance;

	// SQL Queries
	private static final String GET_COMPUTER_QUERY = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id";
	private static final String GET_COMPUTER_BY_ID_QUERY = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id=?";
	private static final String GET_COMPUTER_BY_NAME_QUERY = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name=?";
	private static final String GET_COMPUTER_BY_COMPANY_QUERY = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id where computer.company_id=?";
	private static final String CREATE_COMPUTER_QUERY = "INSERT INTO computer (name, introduced, discontinued, company_id) values (?, ?, ?, ?)";
	private static final String UPDATE_COMPUTER_QUERY = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? where id = ?";
	private static final String DELETE_COMPUTER_QUERY = "DELETE FROM computer where id = ?";
	private static final String COUNT_COMPUTER_QUERY = "SELECT COUNT(*) from computer LEFT JOIN company ON computer.company_id = company.id";

	private static Logger LOGGER = LoggerFactory.getLogger(ComputerDAOImpl.class.getName());

	public static ComputerDAOImpl getInstance() {
		if (instance == null) {
			instance = new ComputerDAOImpl();
		}

		return instance;
	}

	@Override
	public List<Computer> getAll() {

		Connection connection = DAOUtils.initConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resSet = null;

		List<Computer> computers = new ArrayList<Computer>();

		try {
			preparedStatement = connection.prepareStatement(GET_COMPUTER_QUERY);
			resSet = preparedStatement.executeQuery();

			while (resSet.next()) {
				computers.add(ComputerMapper.toComputer(resSet));
			}

		} catch (SQLException e) {
			LOGGER.error("Cannot get computers. " + e.getMessage());
		} finally {
			DAOUtils.closeConnection(connection, preparedStatement, resSet);
		}

		return computers;
	}

	@Override
	public List<Computer> getPage(Page page) {

		Connection connection = DAOUtils.initConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resSet = null;

		List<Computer> computers = new ArrayList<>();

		try {
			String order;
			
			if(page.getOrder() == null){
				order = "computer.id";
			} else {
				order = "computer." + page.getOrder();
			}
			
			String query = GET_COMPUTER_QUERY.concat(" WHERE computer.name LIKE '%")
											 .concat(page.getFilter())
											 .concat("%' OR company.name LIKE '%")
											 .concat(page.getFilter())
											 .concat("%' ORDER BY ")
											 .concat(order)
											 .concat(" ASC")
											 .concat(" LIMIT ? OFFSET ?");
			
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, page.getOffset());
			preparedStatement.setInt(2, page.getStartIndex());
			
			resSet = preparedStatement.executeQuery();

			while (resSet.next()) {
				computers.add(ComputerMapper.toComputer(resSet));
			}

		} catch (SQLException e) {
			LOGGER.error("Cannot get computers. " + e.getMessage());
		} finally {
			DAOUtils.closeConnection(connection, preparedStatement, resSet);
		}

		return computers;
	}
	
	private List<Computer> getByComapny(int id, Connection connection) {

		PreparedStatement preparedStatement = null;
		ResultSet resSet = null;

		List<Computer> computers = new ArrayList<>();

		try {
			preparedStatement = connection.prepareStatement(GET_COMPUTER_BY_COMPANY_QUERY);
			preparedStatement.setInt(1, id);
			resSet = preparedStatement.executeQuery();

			while (resSet.next()) {
				computers.add(ComputerMapper.toComputer(resSet));
			}

		} catch (SQLException e) {
			LOGGER.error("Cannot get computer by id. " + e.getMessage());
		} finally {
			DAOUtils.closeConnection(null, preparedStatement, resSet);
		}

		return computers;
	}
	
	@Override
	public Computer get(int id) {

		Connection connection = DAOUtils.initConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resSet = null;

		Computer computer = null;

		try {
			preparedStatement = connection.prepareStatement(GET_COMPUTER_BY_ID_QUERY);
			preparedStatement.setInt(1, id);
			resSet = preparedStatement.executeQuery();

			if (resSet.next()) {
				computer = ComputerMapper.toComputer(resSet);
			}

		} catch (SQLException e) {
			LOGGER.error("Cannot get computer by id. " + e.getMessage());
		} finally {
			DAOUtils.closeConnection(connection, preparedStatement, resSet);
		}

		return computer;
	}

	@Override
	public Computer get(String name) {

		Connection connection = DAOUtils.initConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resSet = null;

		Computer computer = null;

		try {
			preparedStatement = connection.prepareStatement(GET_COMPUTER_BY_NAME_QUERY);
			preparedStatement.setString(1, name);
			resSet = preparedStatement.executeQuery();

			if (resSet.next()) {
				computer = ComputerMapper.toComputer(resSet);
			}

		} catch (SQLException e) {
			LOGGER.error("Cannot get computer by name. " + e.getMessage());
		} finally {
			DAOUtils.closeConnection(connection, preparedStatement, resSet);
		}

		return computer;
	}

	@Override
	public int create(Computer c) {

		Connection connection = DAOUtils.initConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resSet = null;

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
			
			connection.commit();

			if (resSet.next()) {
				resultKey = resSet.getInt(1);
			}

		} catch (SQLException e) {
			LOGGER.error("Cannot create computer. " + e.getMessage());
		} finally {
			DAOUtils.closeConnection(connection, preparedStatement, resSet);
		}

		return resultKey;
	}

	@Override
	public void update(Computer c) {

		Connection connection = DAOUtils.initConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resSet = null;

		try {
			preparedStatement = connection.prepareStatement(UPDATE_COMPUTER_QUERY);
			preparedStatement.setString(1, c.getName());
			preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(c.getIntroduced(), LocalTime.of(0, 0))));
			preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.of(c.getDiscontinued(), LocalTime.of(0, 0))));
			preparedStatement.setInt(4, c.getCompany().getId());
			preparedStatement.setInt(5, c.getId());
			preparedStatement.executeUpdate();
			
			connection.commit();

		} catch (SQLException e) {
			LOGGER.error("Cannot update computer. " + e.getMessage());
		} finally {
			DAOUtils.closeConnection(connection, preparedStatement, resSet);
		}
	}

	@Override
	public void delete(int id, Connection connection) throws SQLException {
		
		boolean createConnection = (connection == null);

		if(createConnection){
			connection = DAOUtils.initConnection();			
		}
		
		PreparedStatement preparedStatement = null;
		ResultSet resSet = null;

		try {
			preparedStatement = connection.prepareStatement(DELETE_COMPUTER_QUERY);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			
			if(createConnection){
				connection.commit();	
			}
		} catch (SQLException e) {
			LOGGER.error("Cannot delete computer. " + e.getMessage());
			throw new SQLException();
		} finally {
			if(createConnection){
				DAOUtils.closeConnection(connection, preparedStatement, resSet);				
			} else{
				DAOUtils.closeConnection(null, preparedStatement, resSet);
			}
		}
	}
	
	@Override
	public void deleteByCompany(int id, Connection connection) throws SQLException {
		List<Computer> computers = getByComapny(id, connection);
		
		for (Computer computer : computers) {
			delete(computer.getId(), connection);
		}
	}

	@Override
	public int count(Page page) {
		
		int computerNumber = 0;
		
		Connection connection = DAOUtils.initConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resSet = null;
		
		try{
			String query = COUNT_COMPUTER_QUERY.concat(" WHERE computer.name LIKE '%")
											   .concat(page.getFilter())
											   .concat("%' OR company.name LIKE '%")
										       .concat(page.getFilter())
											   .concat("%'");

			preparedStatement = connection.prepareStatement(query);
						
			resSet = preparedStatement.executeQuery();
			
			if(resSet.next()){
				computerNumber = resSet.getInt(1);
			}
		} catch(SQLException e){
		
			LOGGER.error("Cannot count computers. " + e.getMessage());
		} finally {
			DAOUtils.closeConnection(connection, preparedStatement, resSet);
		}
		
		return computerNumber;
	}
}
