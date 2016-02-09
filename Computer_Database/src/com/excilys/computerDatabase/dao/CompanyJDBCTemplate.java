package com.excilys.computerDatabase.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.dao.interfaces.CompanyDAO;
import com.excilys.computerDatabase.db.ConnectionFactory;
import com.excilys.computerDatabase.mapping.CompanyMapper;
import com.excilys.computerDatabase.model.Company;

/**
 * Implementation of CompanyDAO that is used to manipulate the db.
 * @author excilys
 */
public class CompanyJDBCTemplate implements CompanyDAO {
	
	// Query that will be used.
	private static String getCompaniesQuery = "SELECT * FROM `computer-database-db`.company;";
	
	private Connection connection;
    private Statement statement;
    
	public CompanyJDBCTemplate() {
		
	    try {
	    	connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Company> getCompanies() {
		
		List <Company> companies = new ArrayList<Company>();
		
		try {
			ResultSet rs = statement.executeQuery(getCompaniesQuery);
	
			while(rs.next()){
				companies.add(CompanyMapper.map(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return companies;
	}
}
