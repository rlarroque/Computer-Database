package com.excilys.computer_database.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.persistence.dao.ComputerDAO;
import com.excilys.computer_database.persistence.model.Computer;
import com.excilys.computer_database.persistence.model.Page;
import com.excilys.computer_database.persistence.model.validator.ComputerValidator;
import com.excilys.computer_database.service.ComputerService;

/**
 * This class is the implementation of the ComputerService interface. It is a singleton and contains a DAO that is also a singleton. The layer service
 * is calling the DAO methods and also contains some validation of the integrity of the data passed.
 * @author rlarroque
 *
 */
@Service
public class ComputerServiceImpl implements ComputerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceImpl.class.getName());

    @Autowired
    private ComputerDAO computerDAO;

    /**
     * Constructor.
     */
    private ComputerServiceImpl() {

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

        if (page.getTotalComputer() % page.getOffset() != 0) {
            page.setTotalPage(page.getPageNumber() + 1);
        }
    }

    @Override
    public Computer get(long id) {

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
    public long create(Computer computer) {
        ComputerValidator.validate(computer);

        return computerDAO.create(computer);
    }

    @Override
    public void update(Computer computer) {
        ComputerValidator.validate(computer);
        computerDAO.update(computer);
    }

    @Override
    public void delete(long id) {

        if (id < 1) {
            throw new IntegrityException("Id cannot be negativ.");
        }

        try {
            computerDAO.delete(id);
        } catch (SQLException e) {
            LOGGER.error("Cannot delete computer with id: " + id);
        }
    }

    @Override
    public int count(Page page) {
        return computerDAO.count(page);
    }

    @Override
    public void deleteByCompany(long id) throws SQLException {
        if (id < 1) {
            throw new IntegrityException("Id cannot be negativ.");
        }

        computerDAO.deleteByCompany(id);
    }
}