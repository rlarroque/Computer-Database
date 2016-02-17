package com.excilys.computer_database.service;

import java.util.List;

import com.excilys.computer_database.model.Computer;
import com.excilys.computer_database.model.Page;

/**
 * Interface of the computer service
 * @author rlarroque
 *
 */
public interface ComputerService {

	/**
	 * Get the list of all the existing computers on the db.
	 * @return The List of Computer
	 */
	List<Computer> getAll();
	
	/**
	 * Get the list of all the computers and fill the given page.
	 * This method is used for pagination needs..
	 * @param page the page passed with current page and offset information
	 */
	void fillPage(Page page);

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
	 * Method used to create a new computer. A computer has to passed as an
	 * argument so all its parameters can be added to the db.
	 * @param c a computer previously created
	 * @return the id of the created computer
	 */
	int create(Computer computer);

	/**
	 * Method used to update an existing computer. A computer has to passed as
	 * an argument so all its parameters can be modified in the db.
	 * 
	 * @param c a computer with the id of the one we want to modify but 
	 *  possibly with different parameters
	 */
	void update(Computer computer);

	/**
	 * Delete a computer according to the id passed.
	 * @param id id of the computer you want to delete.
	 */
	void delete(int id);
	
	/**
	 * Retrieve the number if computers available on the db.
	 * @return the number of computers
	 */
	int count();

}
