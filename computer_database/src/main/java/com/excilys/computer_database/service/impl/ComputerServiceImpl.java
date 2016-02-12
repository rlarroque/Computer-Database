package com.excilys.computer_database.service.impl;

import java.util.List;

import com.excilys.computer_database.dao.ComputerDAO;
import com.excilys.computer_database.dao.impl.ComputerDAOImpl;
import com.excilys.computer_database.dto.ComputerDTO;
import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.mapping.ComputerMapper;
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
	private static PageService page;

	public static ComputerServiceImpl getInstance() {
		if (instance == null) {
			instance = new ComputerServiceImpl();
		}

		return instance;
	}

	private ComputerServiceImpl() {
		computerDAO = ComputerDAOImpl.getInstance();
		page = PageService.getInstance();
	}

	@Override
	public List<ComputerDTO> getComputers() {
		return ComputerMapper.listComputerToListDTO(computerDAO.getComputers());
	}
	
	@Override
	public List<ComputerDTO> getComputersPage() {
		return ComputerMapper.listComputerToListDTO(computerDAO.getComputersPage(page.getOffset(), page.startIndex()));
	}
	
	@Override
	public List<ComputerDTO> getComputersPage(int number, int startIndex) {
		return ComputerMapper.listComputerToListDTO(computerDAO.getComputersPage(number, startIndex));
	}
	
	/**
	 * 
	 * @return
	 */
	public List<ComputerDTO> nextPage(){
		page.nextPage();
		
		return null;
		//return getComputersPage(page.startIndex(), page.last());
	}
	
	/**
	 * 
	 * @return
	 * @throws IntegrityException
	 */
	public List<ComputerDTO> previousPage() throws IntegrityException{
		try{
			page.previousPage();
		} catch (IntegrityException ie){
			throw ie;
		}
		
		return null;
		//return getComputersPage(page.startIndex(), page.last());
	}

	@Override
	public ComputerDTO getComputer(int id) throws IntegrityException {

		if (id < 1) {
			throw new IntegrityException("Id cannot be negativ.");
		}

		return ComputerMapper.computerToDTO(computerDAO.getComputer(id));
	}

	@Override
	public ComputerDTO getComputer(String name) {

		if (name == null || name == "") {
			throw new IntegrityException("A name is mandatory for a computer.");
		}

		return ComputerMapper.computerToDTO(computerDAO.getComputer(name));
	}

	@Override
	public int createComputer(ComputerDTO dto) throws IntegrityException {
		
		Computer computer = ComputerMapper.dtoToComputer(dto);

		if (computer.getName() == null || computer.getName() == "") {
			throw new IntegrityException("A name is mandatory for a computer.");
		}

		if (computer.getDiscontinued().isBefore(computer.getIntroduced())) {
			throw new IntegrityException("Discontinued date cannot be earlier than introducing date.");
		}

		return computerDAO.createComputer(computer);
	}

	@Override
	public void updateComputer(ComputerDTO dto) throws IntegrityException {
		
		Computer computer = ComputerMapper.dtoToComputer(dto);

		if (computer.getName() == null || computer.getName() == "") {
			throw new IntegrityException("A name is mandatory for a computer.");
		}

		if (computer.getDiscontinued().isBefore(computer.getIntroduced())) {
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
}
