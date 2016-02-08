package com.excilys.computerDatabase.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComputerMapper{

	public static Computer map(ResultSet rs) throws SQLException {
		
		Computer computer = new Computer();
		
		computer.setId(rs.getInt("id"));
		computer.setName(rs.getString("name"));
		computer.setIntroduced(rs.getTimestamp("introduced"));
		computer.setDiscontinued(rs.getTimestamp("discontinued"));
		computer.setCompany_id(rs.getInt("company_id"));
		
		return computer;
	}

}
