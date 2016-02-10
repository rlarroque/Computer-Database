package com.excilys.computer_database.service.impl;

import java.util.List;

import com.excilys.computer_database.dao.ComputerDAO;
import com.excilys.computer_database.dao.impl.ComputerDAOImpl;
import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.model.Computer;
import com.excilys.computer_database.service.ComputerService;

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
	public Computer getComputer(int id) throws IntegrityException{
		
		if(id < 1){
			throw new IntegrityException("Id cannot be negativ.");
		}
		
		return computerDAO.getComputer(id);
	}

	@Override
	public Computer getComputer(String name) {
		return computerDAO.getComputer(name);
	}

	@Override
	public int createComputer(Computer c) throws IntegrityException{
		
		if(c.getDiscontinued().isBefore(c.getIntroduced())){
			throw new IntegrityException("Discontinued date cannot be earlier than introducing date.");
		}
		
		return computerDAO.createComputer(c);
	}

	@Override
	public void updateComputer(Computer c) throws IntegrityException {
		
		if(c.getDiscontinued().isBefore(c.getIntroduced())){
			throw new IntegrityException("Discontinued date cannot be earlier than introducing date.");
		}
		
		computerDAO.updateComputer(c);
	}

	@Override
	public void deleteComputer(int id) throws IntegrityException{
		
		if(id < 1){
			throw new IntegrityException("Id cannot be negativ.");
		}
		
		computerDAO.deleteComputer(id);
	}
}
