package com.excilys.computerDatabase.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyMapper {
	
	public static Company map(ResultSet rs) throws SQLException {
		
		Company company = new Company();
		
		company.setId(rs.getInt("id"));
		company.setName(rs.getString("name"));
		
		return company;
	}
}
