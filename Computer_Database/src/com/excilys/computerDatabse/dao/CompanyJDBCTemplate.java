package com.excilys.computerDatabse.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.CompanyMapper;
import com.excilys.computerDatabse.dao.interfaces.CompanyDAO;
import com.excilys.computerDatabse.db.ConnectionFactory;

public class CompanyJDBCTemplate implements CompanyDAO {
	
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
