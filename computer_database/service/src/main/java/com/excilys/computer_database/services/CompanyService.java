package com.excilys.computer_database.services;

import java.util.List;

import com.excilys.computer_database.model.Company;

/**
 * Interface of the company service.
 * @author rlarroque
 *
 */
public interface CompanyService {

    /**
     * Get the list of all the existing companies in the db.
     * @return the List of Companies
     */
    List<Company> getAll();
    
    /**
     * Returns a company according to the id passed.
     * @param id the id of the company
     * @return the retrieved company itself
     */
    Company get(long id);

    /**
     * Returns a company according to the name passed.
     * @param name the name of the company
     * @return the retrieved company itself
     */
    Company get(String name);

    /**
     * Delete a company based on the id passed. The computers with this company will also be deleted.
     * @param id id of the company to delete
     */
    void delete(long id);
}
