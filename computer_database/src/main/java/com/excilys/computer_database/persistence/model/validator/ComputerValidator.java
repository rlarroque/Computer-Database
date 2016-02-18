package com.excilys.computer_database.persistence.model.validator;

import java.util.List;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.persistence.model.Computer;

public interface ComputerValidator {

	public static void validate(Computer computer) throws IntegrityException{
		
		if(computer == null){
			throw new IntegrityException("The computer is null.");
		}
		
		if(computer.getName() == null || "".equals(computer.getName())){
			throw new IntegrityException("The computer's name is null.");
		}
		
		if (computer.getDiscontinued() != null && computer.getIntroduced() == null) {
			throw new IntegrityException("Discontinued date cannot exist if there is no introducing date for computer " + computer.getName());
		}

		if (computer.getDiscontinued() != null && computer.getIntroduced() != null
				&& computer.getDiscontinued().isBefore(computer.getIntroduced())) {

			throw new IntegrityException("Discontinued date cannot be earlier than introducing date for computer " + computer.getName());
		}
	}
	
	public static void validate(List<Computer> list) throws IntegrityException {
		for (Computer computer : list) {
			validate(computer);
		}
	}
}
