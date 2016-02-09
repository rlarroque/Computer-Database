package com.excilys.computerDatabase.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.computerDatabase.model.Computer;

/**
 * Mapper used to convert a resultSet into a Computer object.
 * @author excilys
 *
 */
public class ComputerMapper{

	/**
	 * Take a resultSet as parameter and return a Computer.
	 * @param rs resultSet that is returned from a SQL query
	 * @return a computer corresponding to the resultSet
	 * @throws SQLException if a SQl exception occurred while reading the resultSet
	 */
	public static Computer map(ResultSet rs) throws SQLException {
		
		Computer computer = new Computer();
		
		if(rs.next()){
			computer.setId(rs.getInt("id"));
			computer.setName(rs.getString("name"));
			computer.setIntroduced(rs.getTimestamp("introduced"));
			computer.setDiscontinued(rs.getTimestamp("discontinued"));
			computer.setCompany_id(rs.getInt("company_id"));	
		}

		return computer;
	}

}
