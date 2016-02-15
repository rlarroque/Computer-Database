package com.excilys.computer_database.service;

import java.util.List;

import com.excilys.computer_database.dto.ComputerDTO;

/**
 * Interface of the computer service
 * @author excilys
 *
 */
public interface ComputerService {

	/**
	 * Get the list of all the existing computers on the db.
	 * @return The List of Computer
	 */
	List<ComputerDTO> getComputers();
	
	/**
	 * Get the list of all the computers between the limits passed.
	 * This method is used for pagination needs..
	 * @param first position in the db of the first computer of the current page
	 * @param last position in the db of the last computer of the current page
	 * @return the List of computers of the current page.
	 */
	List<ComputerDTO> getComputersPage(int number, int startIndex);

	/**
	 * Returns a computer according to the id passed.
	 * @param id the id of the computer
	 * @return the retrieved computer itself
	 */
	ComputerDTO getComputer(int id);

	/**
	 * Returns a computer according to the name passed.
	 * @param name the name of the computer
	 * @return the retrieved computer itself
	 */
	ComputerDTO getComputer(String name);

	/**
	 * Method used to create a new computer. A computer has to passed as an
	 * argument so all its parameters can be added to the db.
	 * @param c a computer previously created
	 * @return the id of the created computer
	 */
	int createComputer(ComputerDTO dto);

	/**
	 * Method used to update an existing computer. A computer has to passed as
	 * an argument so all its parameters can be modified in the db.
	 * 
	 * @param c a computer with the id of the one we want to modify but 
	 *  possibly with different parameters
	 */
	void updateComputer(ComputerDTO dto);

	/**
	 * Delete a computer according to the id passed.
	 * @param id id of the computer you want to delete.
	 */
	void deleteComputer(int id);
	
	/**
	 * Retrieve the number if computers available on the db.
	 * @return the number of computers
	 */
	int computerNumber();

}
