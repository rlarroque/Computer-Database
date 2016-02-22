package com.excilys.computer_database.persistence.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.excilys.computer_database.persistence.model.Company;

/**
 * Interface of the computer DAO
 * @author rlarroque
 *
 */
public interface CompanyDAO {
	
	/**
	 * Get the list of all the existing companies in the db.
	 * @return the List of Companies
	 */
	public List<Company> getAll();
	
	/**
	 * Delete a company based on the id passed. 
	 * @param id id of the company to delete
	 * @throws SQLException 
	 */
	public void delete(int id, Connection connection) throws SQLException;
}