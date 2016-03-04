package com.excilys.computer_database.persistence.dao;

import java.sql.SQLException;
import java.util.List;

import com.excilys.computer_database.persistence.model.Company;

/**
 * Interface of the computer DAO.
 * @author rlarroque
 *
 */
public interface CompanyDAO {

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
     * Delete a company based on the id passed.
     * @param id id of the company to delete
     * @throws SQLException thrown in case of SQL issues
     */
    void delete(long id) ;
}
