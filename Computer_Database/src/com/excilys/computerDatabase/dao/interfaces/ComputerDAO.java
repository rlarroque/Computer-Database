package com.excilys.computerDatabase.dao.interfaces;

import java.util.List;

import com.excilys.computerDatabase.model.Computer;

public interface ComputerDAO {
	
	/**
	 * Get the list of all the existing computers on the db.
	 * @return The List of Computer
	 */
	public List<Computer> getComputers();
	
	/**
	 * Returns a computer according to the id passed.
	 * @param id the id of the computer
	 * @return the retrieved computer itself
	 */
	public Computer getComputer(int id);
	
	/**
	 * Returns a computer according to the name passed.
	 * @param name the name of the computer
	 * @return the retrieved computer itself
	 */
	public Computer getComputer(String name);
	
	/**
	 * Method used to create a new computer. A computer has to passed as an argument 
	 * so all its parameters can be added to the db.
	 * @param c a computer previously created
	 * @return the id of the created computer
	 */
	public int createComputer(Computer c);
	
	/**
	 * Method used to update an existing computer. A computer has to passed as an argument 
	 * so all its parameters can be modified in the db.
	 * @param c a computer with the id of the one we want to modify but possibly with
	 * different parameters
	 */
	public void updateComputer(Computer c);
	
	/**
	 * Delete a computer according to the id passed.
	 * @param id id of the computer you want to delete.
	 */
	public void deleteComputer(int id);
}
