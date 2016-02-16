package com.excilys.computer_database.service;

import java.util.List;

import com.excilys.computer_database.model.Company;

/**
 * Interface of the company service
 * @author excilys
 *
 */
public interface CompanyService {
	
	/**
	 * Get the list of all the existing companies in the db.
	 * @return the List of Companies
	 */
	public List<Company> getCompanies();
}
