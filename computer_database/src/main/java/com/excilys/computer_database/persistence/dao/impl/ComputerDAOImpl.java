package com.excilys.computer_database.persistence.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.excilys.computer_database.persistence.dao.ComputerDAO;
import com.excilys.computer_database.persistence.dao.utils.QueryBuilder;
import com.excilys.computer_database.persistence.model.Computer;
import com.excilys.computer_database.persistence.model.Page;
import com.excilys.computer_database.persistence.model.mapper.ComputerRowMapper;

/**
 * Implementation of ComputerDAO that is used to manipulate the db.
 * @author rlarroque
 */
@Repository
public class ComputerDAOImpl implements ComputerDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Computer> getAll() {

        List<Computer> computers = jdbcTemplate.query(QueryBuilder.getComputersQuery(), new ComputerRowMapper());

        return computers;
    }

    @Override
    public List<Computer> getPage(Page page) {

        List<Computer> computers = jdbcTemplate.query(QueryBuilder.getComputerPageQuery(page), new ComputerRowMapper());

        return computers;
    }

    @Override
    public Computer get(long id) {

        Computer computer = jdbcTemplate.queryForObject(QueryBuilder.getComputerQuery(id),
                                                        new Long[] { id },
                                                        new ComputerRowMapper());

        return computer;
    }

    @Override
    public Computer get(String name) {

        Computer computer = jdbcTemplate.queryForObject(QueryBuilder.getComputerQuery(name),
                                                        new String[] { name },
                                                        new ComputerRowMapper());

        return computer;
    }

    @Override
    public long create(Computer computer) {
    	
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(QueryBuilder.createQuery(computer), keyHolder);
        
        return (long) keyHolder.getKey();
    }

    @Override
    public void update(Computer computer) {      
        jdbcTemplate.update(QueryBuilder.updateQuery(computer));
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(QueryBuilder.deleteComputerQuery(id));
    }

    @Override
    public void deleteByCompany(long id) {
        jdbcTemplate.update(QueryBuilder.deleteComputerByCompanyQuery(id));
    }

    @Override
    public int count(Page page) {
    	
        int computerNumber = jdbcTemplate.queryForObject(QueryBuilder.countComputerQuery(page), Integer.class);

        return computerNumber;
    }
}
