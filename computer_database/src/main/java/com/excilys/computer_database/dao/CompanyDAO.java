package com.excilys.computer_database.dao;

import java.util.List;

import com.excilys.computer_database.model.Company;

/**
 * Interface of the computer DAO
 * @author excilys
 *
 */
public interface CompanyDAO {
	
	/**
	 * Get the list of all the existing companies in the db.
	 * @return the List of Companies
	 */
	public List<Company> getCompanies();
}
