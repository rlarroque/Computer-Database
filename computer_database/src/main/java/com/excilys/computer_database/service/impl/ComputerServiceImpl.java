package com.excilys.computer_database.service.impl;

import java.util.List;

import com.excilys.computer_database.dao.ComputerDAO;
import com.excilys.computer_database.dao.impl.ComputerDAOImpl;
import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.model.Computer;
import com.excilys.computer_database.service.ComputerService;

/**
 * This class is the implementation of the ComputerService interface. It is a
 * singleton and contains a DAO that is also a singleton. The layer service is
 * calling the DAO methods and also contains some validation of the integrity of
 * the data passed.
 * 
 * @author excilys
 *
 */
public class ComputerServiceImpl implements ComputerService {

	private static ComputerServiceImpl instance;
	private static ComputerDAO computerDAO;

	public static ComputerServiceImpl getInstance() {
		if (instance == null) {
			instance = new ComputerServiceImpl();
		}

		return instance;
	}

	private ComputerServiceImpl() {
		computerDAO = ComputerDAOImpl.getInstance();
	}

	@Override
	public List<Computer> getComputers() {
		return computerDAO.getComputers();
	}

	@Override
	public List<Computer> getComputersPage(int number, int startIndex) {
		return computerDAO.getComputersPage(number, startIndex);
	}

	@Override
	public Computer getComputer(int id) throws IntegrityException {

		if (id < 1) {
			throw new IntegrityException("Id cannot be negativ.");
		}

		return computerDAO.getComputer(id);
	}

	@Override
	public Computer getComputer(String name) throws IntegrityException{

		if (name == null || name == "") {
			throw new IntegrityException("A name is mandatory for a computer.");
		}

		return computerDAO.getComputer(name);
	}

	@Override
	public int createComputer(Computer computer) throws IntegrityException {

		if(computer == null){
			throw new IntegrityException("A null computer was found.");
		}

		if (computer.getName() == null || computer.getName() == "") {
			throw new IntegrityException("A name is mandatory for a computer.");
		}

		if (computer.getDiscontinued() != null && computer.getIntroduced() == null) {
			throw new IntegrityException("Discontinued date cannot exist if there is no introducing date.");
		}

		if (computer.getDiscontinued() != null && computer.getIntroduced() != null
				&& computer.getDiscontinued().isBefore(computer.getIntroduced())) {

			throw new IntegrityException("Discontinued date cannot be earlier than introducing date.");
		}

		return computerDAO.createComputer(computer);
	}

	@Override
	public void updateComputer(Computer computer) throws IntegrityException {
		
		if(computer == null){
			throw new IntegrityException("A null computer was found.");
		}
		
		if (computer.getName() == null || computer.getName() == "") {
			throw new IntegrityException("A name is mandatory for a computer.");
		}

		if (computer.getDiscontinued() != null && computer.getIntroduced() != null
				&& computer.getDiscontinued().isBefore(computer.getIntroduced())) {

			throw new IntegrityException("Discontinued date cannot be earlier than introducing date.");
		}

		computerDAO.updateComputer(computer);
	}

	@Override
	public void deleteComputer(int id) throws IntegrityException {

		if (id < 1) {
			throw new IntegrityException("Id cannot be negativ.");
		}

		computerDAO.deleteComputer(id);
	}

	@Override
	public int computerNumber() {
		return computerDAO.computerNumber();
	}
}
