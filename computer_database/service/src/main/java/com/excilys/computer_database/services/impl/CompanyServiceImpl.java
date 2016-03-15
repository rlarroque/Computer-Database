package com.excilys.computer_database.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.dao.CompanyDAO;
import com.excilys.computer_database.dao.ComputerDAO;
import com.excilys.computer_database.model.Company;
import com.excilys.computer_database.services.CompanyService;

/**
 * This class is the implementation of the ComputerService interface. It is a singleton and contains a DAO that is also a singleton. The layer service
 * is calling the DAO methods and also contains some validation of the integrity of the data passed.
 * @author rlarroque
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    
    @Autowired
    private CompanyDAO companyDAO;
    
    @Autowired
    private ComputerDAO computerDao;

    @Override
    public List<Company> getAll() {
        return (companyDAO.getAll());
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(long id) {
          
        computerDao.deleteByCompany(id);
        companyDAO.delete(id);
    }
    
    @Override
    public Company get(long id) {

        if (id < 1) {
            throw new IntegrityException("Id cannot be negativ.");
        }

        return companyDAO.get(id);
    }

    @Override
    public Company get(String name) {

        if (name == null || name == "") {
            throw new IntegrityException("A name is mandatory for a company.");
        }

        return companyDAO.get(name);
    }
}
