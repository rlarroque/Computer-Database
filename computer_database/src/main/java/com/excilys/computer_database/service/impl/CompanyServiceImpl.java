package com.excilys.computer_database.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.excilys.computer_database.persistence.dao.CompanyDAO;
import com.excilys.computer_database.persistence.dao.impl.CompanyDAOImpl;
import com.excilys.computer_database.persistence.dao.utils.DAOUtils;
import com.excilys.computer_database.persistence.model.Company;
import com.excilys.computer_database.service.CompanyService;
import com.excilys.computer_database.service.ComputerService;

/**
 * This class is the implementation of the ComputerService interface. It is a
 * singleton and contains a DAO that is also a singleton. The layer service is
 * calling the DAO methods and also contains some validation of the integrity of
 * the data passed.
 * @author rlarroque
 *
 */
public class CompanyServiceImpl implements CompanyService {

	private static CompanyServiceImpl instance;

	private CompanyDAO companyDAO;

	public static CompanyServiceImpl getInstance() {
		if (instance == null) {
			instance = new CompanyServiceImpl();
		}

		return instance;
	}

	private CompanyServiceImpl() {
		companyDAO = CompanyDAOImpl.getInstance();
	}

	@Override
	public List<Company> getAll() {
		return (companyDAO.getAll());
	}

	@Override
	public void delete(int id) throws SQLException{
		Connection connection = DAOUtils.initConnection();
		ComputerService compService = ComputerServiceImpl.getInstance();
		
		try{
			companyDAO.delete(id, connection);
			compService.deleteByCompany(id, connection);
			
			connection.commit();
		} catch(SQLException e){
			connection.rollback();
			throw new SQLException();
		}
	}
}
