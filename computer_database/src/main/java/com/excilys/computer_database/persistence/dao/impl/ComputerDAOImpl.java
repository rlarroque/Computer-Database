package com.excilys.computer_database.persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computer_database.persistence.dao.ComputerDAO;
import com.excilys.computer_database.persistence.dao.utils.DAOUtils;
import com.excilys.computer_database.persistence.dao.utils.QueryBuilder;
import com.excilys.computer_database.persistence.model.Computer;
import com.excilys.computer_database.persistence.model.Page;
import com.excilys.computer_database.persistence.model.mapper.ComputerMapper;

/**
 * Implementation of ComputerDAO that is used to manipulate the db.
 * @author rlarroque
 */
public class ComputerDAOImpl implements ComputerDAO {

	private static ComputerDAOImpl instance;
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
			preparedStatement = connection.prepareStatement(QueryBuilder.getComputersQuery());
			resSet = preparedStatement.executeQuery();

			while (resSet.next()) {
				computers.add(ComputerMapper.toComputer(resSet));
			}

		} catch (SQLException e) {
			LOGGER.error("Cannot get all computers!!! " + e.getMessage());
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
			preparedStatement = connection.prepareStatement(QueryBuilder.getComputerPageQuery(page));
			resSet = preparedStatement.executeQuery();

			while (resSet.next()) {
				computers.add(ComputerMapper.toComputer(resSet));
			}

		} catch (SQLException e) {
			LOGGER.error("Cannot get the page of computers!!! " + e.getMessage());
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
			preparedStatement = connection.prepareStatement(QueryBuilder.getComputersQuery(id));
			resSet = preparedStatement.executeQuery();

			while (resSet.next()) {
				computers.add(ComputerMapper.toComputer(resSet));
			}

		} catch (SQLException e) {
			LOGGER.error("Cannot get all the computers with the company id: " + id + "!!! " + e.getMessage());
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
			preparedStatement = connection.prepareStatement(QueryBuilder.getComputerQuery(id));
			resSet = preparedStatement.executeQuery();

			if (resSet.next()) {
				computer = ComputerMapper.toComputer(resSet);
			}

		} catch (SQLException e) {
			LOGGER.error("Cannot get the computer with the id " + id + "!!! " + e.getMessage());
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
			preparedStatement = connection.prepareStatement(QueryBuilder.getComputerQuery(name));
			resSet = preparedStatement.executeQuery();

			if (resSet.next()) {
				computer = ComputerMapper.toComputer(resSet);
			}

		} catch (SQLException e) {
			LOGGER.error("Cannot get the computer with the name " + name + "!!! " + e.getMessage());
		} finally {
			DAOUtils.closeConnection(connection, preparedStatement, resSet);
		}

		return computer;
	}

	@Override
	public int create(Computer computer) {

		Connection connection = DAOUtils.initConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resSet = null;

		int resultKey = 0;

		try {
			preparedStatement = connection.prepareStatement(QueryBuilder.createQuery(), Statement.RETURN_GENERATED_KEYS);
			QueryBuilder.buildCreateQuery(computer, preparedStatement);
			
			preparedStatement.executeUpdate();
			resSet = preparedStatement.getGeneratedKeys();
			
			connection.commit();

			if (resSet.next()) {
				resultKey = resSet.getInt(1);
			}

		} catch (SQLException e) {
			LOGGER.error("Cannot create computer!!! " + e.getMessage());
		} finally {
			DAOUtils.closeConnection(connection, preparedStatement, resSet);
		}

		return resultKey;
	}

	@Override
	public void update(Computer computer) {

		Connection connection = DAOUtils.initConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resSet = null;

		try {
			preparedStatement = connection.prepareStatement(QueryBuilder.updateQuery());
			QueryBuilder.buildUpdateQuery(computer, preparedStatement);
			preparedStatement.executeUpdate();
			
			connection.commit();

		} catch (SQLException e) {
			LOGGER.error("Cannot update computer!!! " + e.getMessage());
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
			preparedStatement = connection.prepareStatement(QueryBuilder.deleteComputerQuery(id));
			preparedStatement.executeUpdate();
			
			if(createConnection){
				connection.commit();	
			}
		} catch (SQLException e) {
			LOGGER.error("Cannot delete computer wit id: " + id + "!!! " + e.getMessage());
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
			preparedStatement = connection.prepareStatement(QueryBuilder.countComputerQuery(page));
			resSet = preparedStatement.executeQuery();
			
			if(resSet.next()){
				computerNumber = resSet.getInt(1);
			}
		} catch(SQLException e){
		
			LOGGER.error("Cannot count computers!!! " + e.getMessage());
		} finally {
			DAOUtils.closeConnection(connection, preparedStatement, resSet);
		}
		
		return computerNumber;
	}
}
