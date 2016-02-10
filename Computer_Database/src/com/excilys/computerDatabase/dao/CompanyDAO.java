package com.excilys.computerDatabase.dao.interfaces;

import java.util.List;

import com.excilys.computerDatabase.model.Company;

public interface CompanyDAO {
	
	/**
	 * Get the list of all the existing companies in the db.
	 * @return the List of Companies
	 */
	public List<Company> getCompanies();
}
