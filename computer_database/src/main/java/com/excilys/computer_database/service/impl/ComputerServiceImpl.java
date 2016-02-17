package com.excilys.computer_database.service.impl;

import java.util.List;

import com.excilys.computer_database.dao.ComputerDAO;
import com.excilys.computer_database.dao.impl.ComputerDAOImpl;
import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.model.Computer;
import com.excilys.computer_database.model.validator.ComputerValidator;
import com.excilys.computer_database.service.ComputerService;

/**
 * This class is the implementation of the ComputerService interface. It is a
 * singleton and contains a DAO that is also a singleton. The layer service is
 * calling the DAO methods and also contains some validation of the integrity of
 * the data passed.
 * @author rlarroque
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
	public List<Computer> getAll() {
		return computerDAO.getAll();
	}

	@Override
	public List<Computer> getPage(int number, int startIndex) {
		return computerDAO.getPage(number, startIndex);
	}

	@Override
	public Computer get(int id) {

		if (id < 1) {
			throw new IntegrityException("Id cannot be negativ.");
		}

		return computerDAO.get(id);
	}

	@Override
	public Computer get(String name) {

		if (name == null || name == "") {
			throw new IntegrityException("A name is mandatory for a computer.");
		}

		return computerDAO.get(name);
	}

	@Override
	public int create(Computer computer) {
		ComputerValidator.validate(computer);
		
		return computerDAO.create(computer);
	}

	@Override
	public void update(Computer computer) {
		ComputerValidator.validate(computer);
		computerDAO.update(computer);
	}

	@Override
	public void delete(int id) {

		if (id < 1) {
			throw new IntegrityException("Id cannot be negativ.");
		}

		computerDAO.delete(id);
	}

	@Override
	public int count() {
		return computerDAO.count();
	}
}
