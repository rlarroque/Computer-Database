package com.excilys.computer_database.validator;

import java.util.List;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.model.Computer;

/**
 * Interface with static methods used to validate the integrity of a computer.
 * @author rlarroque
 */
public interface ComputerValidator {

    /**
     * Validate the integrity of a computer.
     * @param computer computer to validate
     * @throws IntegrityException thrown if the integrity is not respected
     */
    static void validate(Computer computer) throws IntegrityException {

        if (computer == null) {
            throw new IntegrityException("The computer is null.");
        }

        if (computer.getName() == null || "".equals(computer.getName())) {
            throw new IntegrityException("The computer's name is null.");
        }

        if (computer.getDiscontinued() != null && computer.getIntroduced() == null) {
            throw new IntegrityException("Discontinued date cannot exist if there is no introducing date for computer " + computer.getName());
        }

        if (computer.getDiscontinued() != null && 
            computer.getIntroduced() != null && 
            computer.getDiscontinued().isBefore(computer.getIntroduced())) {

            throw new IntegrityException("Discontinued date cannot be earlier than introducing date for computer " + computer.getName());
        }
    }

    /**
     * Validate the integrity of a list of computers.
     * @param list list to validate
     * @throws IntegrityException thrown if the integrity is not respected
     */
    static void validate(List<Computer> list) throws IntegrityException {
        for (Computer computer : list) {
            validate(computer);
        }
    }
}
