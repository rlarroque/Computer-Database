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

	private ComputerDAO computerDAO;

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
	public Computer getComputer(int id) throws IntegrityException {

		if (id < 1) {
			throw new IntegrityException("Id cannot be negativ.");
		}

		return computerDAO.getComputer(id);
	}

	@Override
	public Computer getComputer(String name) {

		if (name == null || name == "") {
			throw new IntegrityException("A name is mandatory for a computer.");
		}

		return computerDAO.getComputer(name);
	}

	@Override
	public int createComputer(Computer c) throws IntegrityException {

		if (c.getName() == null || c.getName() == "") {
			throw new IntegrityException("A name is mandatory for a computer.");
		}

		if (c.getDiscontinued().isBefore(c.getIntroduced())) {
			throw new IntegrityException("Discontinued date cannot be earlier than introducing date.");
		}

		return computerDAO.createComputer(c);
	}

	@Override
	public void updateComputer(Computer c) throws IntegrityException {

		if (c.getName() == null || c.getName() == "") {
			throw new IntegrityException("A name is mandatory for a computer.");
		}

		if (c.getDiscontinued().isBefore(c.getIntroduced())) {
			throw new IntegrityException("Discontinued date cannot be earlier than introducing date.");
		}

		computerDAO.updateComputer(c);
	}

	@Override
	public void deleteComputer(int id) throws IntegrityException {

		if (id < 1) {
			throw new IntegrityException("Id cannot be negativ.");
		}

		computerDAO.deleteComputer(id);
	}
}
