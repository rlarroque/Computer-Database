package com.excilys.computer_database.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.computer_database.model.Company;
import com.excilys.computer_database.model.Computer;

/**
 * Mapper used to convert a resultSet into a Computer object.
 * @author excilys
 *
 */
public interface ComputerMapper{

	/**
	 * @param rs resultSet that is returned from a SQL query
	 * @return a computer corresponding to the resultSet
	 * @throws SQLException if a SQl exception occurred while reading the resultSet
	 */
	public static Computer map(ResultSet rs) throws SQLException {
		
		Computer computer = new Computer();
		
		computer.setId(rs.getInt("computer.id"));
		computer.setName(rs.getString("computer.name"));
		
		if(rs.getTimestamp("introduced") != null){
			computer.setIntroduced(rs.getTimestamp("introduced").toLocalDateTime());				
		}
		
		if(rs.getTimestamp("discontinued") != null){
			computer.setDiscontinued(rs.getTimestamp("discontinued").toLocalDateTime());				
		}
		
		Company company = new Company();
		company.setId(rs.getInt("company.id"));
		company.setName(rs.getString("company.name"));
		
		computer.setCompany(company);	
		
		return computer;
	}

}
