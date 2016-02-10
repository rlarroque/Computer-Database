package com.excilys.computer_database.service.impl;

import java.util.List;

import com.excilys.computer_database.dao.CompanyDAO;
import com.excilys.computer_database.dao.impl.CompanyDAOImpl;
import com.excilys.computer_database.model.Company;
import com.excilys.computer_database.service.CompanyService;

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
	public List<Company> getCompanies() {
		return companyDAO.getCompanies();
	}
}
