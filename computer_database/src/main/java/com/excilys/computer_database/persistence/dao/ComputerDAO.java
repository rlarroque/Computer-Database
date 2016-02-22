package com.excilys.computer_database.persistence.dao;

import java.util.List;

import com.excilys.computer_database.persistence.model.Computer;
import com.excilys.computer_database.persistence.model.Page;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interface of the computer DAO
 * @author rlarroque
 *
 */
public interface ComputerDAO {
	
	/**
	 * Get the list of all the existing computers on the db.
	 * @return the List of Computer
	 */
	List<Computer> getAll();
	
	/**
	 * Get the list of all the computers between the limits passed.
	 * This method is used for pagination needs..
	 * @param page contains all the page information needed to display the computers
	 * @return the List of computers of the current page.
	 */
	List<Computer> getPage(Page page);
	
	/**
	 * Returns a computer according to the id passed.
	 * @param id the id of the computer
	 * @return the retrieved computer itself
	 */
	Computer get(int id);
	
	/**
	 * Returns a computer according to the name passed.
	 * @param name the name of the computer
	 * @return the retrieved computer itself
	 */
	Computer get(String name);
	
	/**
	 * Method used to create a new computer. A computer has to passed as an argument 
	 * so all its parameters can be added to the db.
	 * @param c a computer previously created
	 * @return the id of the created computer
	 */
	int create(Computer c);
	
	/**
	 * Method used to update an existing computer. A computer has to passed as an argument 
	 * so all its parameters can be modified in the db.
	 * @param c a computer with the id of the one we want to modify but possibly with
	 * different parameters
	 */
	void update(Computer c);
	
	/**
	 * Delete a computer according to the id passed.
	 * @param id id of the computer you want to delete.
	 * @param connecion a connection to use to delete the computer (passed when a company is
	 * deleted so all the related computers are delete within the same connection and a rollback 
	 * is possible)
	 * @throws SQLException 
	 */
	void delete(int id, Connection connection) throws SQLException;
	
	/**
	 * Delete all computers sharing a company that has this id
	 * @param id id of the company 
	 * @param connection to use
	 * @throws SQLException 
	 */
	void deleteByCompany(int id, Connection connection) throws SQLException;
	
	/**
	 * Retrieve the number if computers available on the db.
	 * @param page page information
	 * @return the number of computers
	 */
	int count(Page page);

}