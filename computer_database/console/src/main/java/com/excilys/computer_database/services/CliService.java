package com.excilys.computer_database.services;

import com.excilys.computer_database.dto.model.CompanyDTO;
import com.excilys.computer_database.dto.model.ComputerDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * @author rlarroque
 */
public interface CliService {

    /**
     * Get the list of all the existing computers on the db.
     * @return The List of Computer
     */
    List<ComputerDTO> getAllComputers();

    /**
     * Get the list of all the existing companies on the db.
     * @return The List of Companies
     */
    List<CompanyDTO> getAllCompanies();

    /**
     * Returns a computer according to the id passed.
     * @param id the id of the computer
     * @return the retrieved computer itself
     */
    ComputerDTO get(long id);

    /**
     * Method used to create a new computer. A computer has to be passed as an argument so all its parameters
     * can be added to the db.
     * @param computer a computer previously created
     * @return the id of the created computer
     */
    long create(ComputerDTO computer);

    /**
     * Method used to update an existing computer. A computer has to be passed as an argument so all its parameters
     * can be modified in the db.
     * @param computer a computer with the id of the one we want to modify but possibly with different parameters
     */
    void update(ComputerDTO computer);

    /**
     * Delete a computer according to the id passed.
     * @param id id of the computer you want to delete.
     */
    void delete(long id);

    /**
     * Delete all computers sharing a company that has this id.
     * @param id id of the company
     */
    void deleteByCompany(long id) ;
}
