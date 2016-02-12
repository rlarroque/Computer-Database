package com.excilys.computer_database.service;

import java.util.List;

import com.excilys.computer_database.model.Computer;

/**
 * Interface of the computer service
 * 
 * @author excilys
 *
 */
public interface ComputerService {

	/**
	 * Get the list of all the existing computers on the db.
	 * 
	 * @return The List of Computer
	 */
	List<Computer> getComputers();

	/**
	 * Returns a computer according to the id passed.
	 * 
	 * @param id
	 *            the id of the computer
	 * @return the retrieved computer itself
	 */
	Computer getComputer(int id);

	/**
	 * Returns a computer according to the name passed.
	 * 
	 * @param name
	 *            the name of the computer
	 * @return the retrieved computer itself
	 */
	Computer getComputer(String name);

	/**
	 * Method used to create a new computer. A computer has to passed as an
	 * argument so all its parameters can be added to the db.
	 * 
	 * @param c
	 *            a computer previously created
	 * @return the id of the created computer
	 */
	int createComputer(Computer c);

	/**
	 * Method used to update an existing computer. A computer has to passed as
	 * an argument so all its parameters can be modified in the db.
	 * 
	 * @param c
	 *            a computer with the id of the one we want to modify but
	 *            possibly with different parameters
	 */
	void updateComputer(Computer c);

	/**
	 * Delete a computer according to the id passed.
	 * 
	 * @param id
	 *            id of the computer you want to delete.
	 */
	void deleteComputer(int id);

}
