package com.excilys.computerDatabase.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.computerDatabase.model.Company;

/**
 * Mapper used to convert a resultSet into a Company object.
 * @author excilys
 *
 */
public class CompanyMapper {
	
	/**
	 * Take a resultSet as parameter and return a Company.
	 * @param rs resultSet that is returned from a SQL query
	 * @return a company corresponding to the resultSet
	 * @throws SQLException if a SQl exception occurred while reading the resultSet
	 */
	public static Company map(ResultSet rs) throws SQLException {
		
		Company company = new Company();
		
		company.setId(rs.getInt("id"));
		company.setName(rs.getString("name"));
		
		return company;
	}
}
