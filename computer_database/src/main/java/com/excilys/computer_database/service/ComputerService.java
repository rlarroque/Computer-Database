package com.excilys.computer_database.service;

import java.sql.SQLException;
import java.util.List;

import com.excilys.computer_database.persistence.model.Computer;
import com.excilys.computer_database.persistence.model.Page;

/**
 * Interface of the computer service.
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
     * Get the list of all the computers and fill the given page. This method is used for pagination needs..
     * @param page the page passed with current page and offset information
     */
    void fillPage(Page page);

    /**
     * Returns a computer according to the id passed.
     * @param id the id of the computer
     * @return the retrieved computer itself
     */
    Computer get(long id);

    /**
     * Returns a computer according to the name passed.
     * @param name the name of the computer
     * @return the retrieved computer itself
     */
    Computer get(String name);

    /**
     * Method used to create a new computer. A computer has to passed as an argument so all its parameters can be added to the db.
     * @param computer a computer previously created
     * @return the id of the created computer
     */
    long create(Computer computer);

    /**
     * Method used to update an existing computer. A computer has to passed as an argument so all its parameters can be modified in the db.
     * @param computer a computer with the id of the one we want to modify but possibly with different parameters
     */
    void update(Computer computer);

    /**
     * Delete a computer according to the id passed.
     * @param id id of the computer you want to delete.
     */
    void delete(long id);

    /**
     * Delete all computers sharing a company that has this id.
     * @param id id of the company
     * @throws SQLException thrown in case of SQL issues
     */
    void deleteByCompany(long id) throws SQLException;

    /**
     * Retrieve the number if computers available on the db.
     * @param page page information
     * @return the number of computers
     */
    int count(Page page);

}
