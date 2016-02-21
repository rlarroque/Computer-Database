package com.excilys.computer_database.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.persistence.dao.ComputerDAO;
import com.excilys.computer_database.persistence.dao.impl.ComputerDAOImpl;
import com.excilys.computer_database.persistence.model.Computer;
import com.excilys.computer_database.persistence.model.Page;
import com.excilys.computer_database.persistence.model.validator.ComputerValidator;
import com.excilys.computer_database.service.ComputerService;

/**
 * This class is the implementation of the ComputerService interface. It is a
 * singleton and contains a DAO that is also a singleton. The layer service is
 * calling the DAO methods and also contains some validation of the integrity of
 * the data passed.
 * 
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
	public void fillPage(Page page) {
		page.setStartIndex((page.getPageNumber() - 1) * page.getOffset());
		page.setComputers(computerDAO.getPage(page));
		
		page.setTotalComputer(count(page));
		page.setTotalPage(page.getTotalComputer() / page.getOffset());			
		
		if(page.getTotalComputer() % page.getOffset() != 0){
			page.setTotalPage(page.getPageNumber() + 1);
		}
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
	public void delete(int id) throws SQLException {

		if (id < 1) {
			throw new IntegrityException("Id cannot be negativ.");
		}

		computerDAO.delete(id, null);
	}

	@Override
	public int count(Page page) {
		return computerDAO.count(page);
	}

	@Override
	public void deleteByCompany(int id, Connection connection) throws SQLException {
		if (id < 1) {
			throw new IntegrityException("Id cannot be negativ.");
		}
		
		computerDAO.deleteByCompany(id, connection);
	}
}