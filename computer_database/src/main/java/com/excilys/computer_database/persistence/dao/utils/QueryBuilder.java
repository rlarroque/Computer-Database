package com.excilys.computer_database.persistence.dao.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.excilys.computer_database.persistence.model.Computer;
import com.excilys.computer_database.persistence.model.Page;

public class QueryBuilder {
	
	// SQL Queries
	private static final String GET_COMPUTER_QUERY = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id";
	private static final String CREATE_COMPUTER_QUERY = "INSERT INTO computer (name, introduced, discontinued, company_id) values (?, ?, ?, ?)";
	private static final String UPDATE_COMPUTER_QUERY = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? where id = ?";
	private static final String DELETE_COMPUTER_QUERY = "DELETE FROM computer where id = ";
	private static final String COUNT_COMPUTER_QUERY = "SELECT COUNT(*) from computer LEFT JOIN company ON computer.company_id = company.id";
	
	public static String getComputersQuery(){
		return GET_COMPUTER_QUERY;
	}
	
	public static String getComputersQuery(int id){
		String query = GET_COMPUTER_QUERY.concat(" WHERE computer.company_id=" + id);
		
		return query;
	}
	
	public static String getComputerPageQuery(Page page){
		
		String query = GET_COMPUTER_QUERY;
		
		if(page.getFilter() != null && !"".equals(page.getFilter())){
			query = query.concat(" WHERE computer.name LIKE '%")
						 .concat(page.getFilter())
						 .concat("%' OR company.name LIKE '%")
						 .concat(page.getFilter())
						 .concat("%'");
		}
		
		
		if(page.getOrder() != null && !"".equals(page.getOrder())){
			query = query.concat(" ORDER BY computer.")
						 .concat(page.getOrder())
						 .concat(" ASC");
		}
		
		query = query.concat(" LIMIT " + page.getOffset() + " OFFSET " + page.getStartIndex());
			
		return query;
	}
	
	public static String getComputerQuery(String name){
		String query = GET_COMPUTER_QUERY.concat(" WHERE computer.name=" + name);
		
		return query;
	}
	
	public static String getComputerQuery(int id){
		String query = GET_COMPUTER_QUERY.concat(" WHERE computer.id=" + id);
		
		return query;
	}
	
	public static String createQuery(){
		return CREATE_COMPUTER_QUERY;
	}
	
	public static void buildCreateQuery(Computer computer, PreparedStatement preparedStatement) throws SQLException {
		
		preparedStatement.setString(1, computer.getName());
		
		if (computer.getIntroduced() == null) {
			preparedStatement.setObject(2, null);
		} else {
			preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(computer.getIntroduced(), LocalTime.of(0, 0))));
		}

		if (computer.getDiscontinued() == null) {
			preparedStatement.setObject(3, null);
		} else {
			preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.of(computer.getDiscontinued(), LocalTime.of(0, 0))));
		}
		
		if(computer.getCompany() == null){
			preparedStatement.setObject(4, null);
		} else{
			preparedStatement.setInt(4, computer.getCompany().getId());
		}
	}
	
	public static String updateQuery(){
		return UPDATE_COMPUTER_QUERY;
	}
	
	public static void buildUpdateQuery(Computer computer, PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setString(1, computer.getName());
		preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(computer.getIntroduced(), LocalTime.of(0, 0))));
		preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.of(computer.getDiscontinued(), LocalTime.of(0, 0))));
		preparedStatement.setInt(4, computer.getCompany().getId());
		preparedStatement.setInt(5, computer.getId());
	}
	
	public static String deleteComputerQuery(Integer id){
		String query = DELETE_COMPUTER_QUERY.concat(id.toString());
		
		return query;
	}
	
	public static String countComputerQuery(Page page){
		String query = COUNT_COMPUTER_QUERY;
		
		if(page.getFilter() != null && !"".equals(page.getFilter())){
			query = query.concat(" WHERE computer.name LIKE '%")
						 .concat(page.getFilter())
						 .concat("%' OR company.name LIKE '%")
						 .concat(page.getFilter())
						 .concat("%'");
		}
		
		return query;
	}
}
