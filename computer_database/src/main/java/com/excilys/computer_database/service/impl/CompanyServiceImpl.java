package com.excilys.computer_database.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.persistence.dao.CompanyDAO;
import com.excilys.computer_database.persistence.dao.ComputerDAO;
import com.excilys.computer_database.persistence.db.ConnectionFactory;
import com.excilys.computer_database.persistence.model.Company;
import com.excilys.computer_database.service.CompanyService;

/**
 * This class is the implementation of the ComputerService interface. It is a singleton and contains a DAO that is also a singleton. The layer service
 * is calling the DAO methods and also contains some validation of the integrity of the data passed.
 * @author rlarroque
 *
 */
@Service
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class.getName());
    
    @Autowired
    private CompanyDAO companyDAO;
    
    @Autowired
    private ComputerDAO computerDao;

    @Override
    public List<Company> getAll() {
        return (companyDAO.getAll());
    }

    @Override
    @Transactional(readOnly = false, transactionManager="transactionManagerMySql")
    public void delete(long id) {

        try {            
            companyDAO.delete(id);
            computerDao.deleteByCompany(id);

        } catch (SQLException e) {
            LOGGER.error("Delete company with id: " + id + " failed!!! rollbacking...");
        } finally {
            ConnectionFactory.closeConnection();
        }
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
