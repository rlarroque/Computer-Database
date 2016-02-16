package com.excilys.computer_database.dao.impl;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computer_database.dao.CompanyDAO;
import com.excilys.computer_database.db.ConnectionFactory;
import com.excilys.computer_database.db.DbUtils;
import com.excilys.computer_database.mapping.CompanyMapper;
import com.excilys.computer_database.model.Company;


/**
 * Implementation of CompanyDAO that is used to manipulate the db.
 * @author excilys
 */
public class CompanyDAOImpl implements CompanyDAO {
	
	private static CompanyDAOImpl instance;
	
	// Query that will be used.
	private static final String GET_COMPANIES_QUERY = "SELECT * FROM company;";
	
	private Logger logger = LoggerFactory.getLogger(getClass().getName());
	private Connection connection;
    private Statement statement;
    private ResultSet resSet;

	public static CompanyDAOImpl getInstance(){
		if(instance == null){
			instance = new CompanyDAOImpl();
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
	    	DbUtils.close(connection);
		} catch (SQLException e) {
			logger.error("Cannot close the connection to the DB. " + e.getMessage());
		}
	}

	@Override
	public List<Company> getCompanies() {
		
		initConnection();
		
		List <Company> companies = new ArrayList<Company>();
		
		try {
			resSet = statement.executeQuery(GET_COMPANIES_QUERY);
	
			while(resSet.next()){
				companies.add(CompanyMapper.resultSetToCompany(resSet));
			}
			
		} catch (SQLException e) {
			logger.error("Cannot get companies. " + e.getMessage());
		} finally {
			closeConnection();
		}
		
		return companies;
	}
}
