package com.excilys.computer_database.persistence.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.computer_database.persistence.dao.CompanyDAO;
import com.excilys.computer_database.persistence.dao.utils.QueryBuilder;
import com.excilys.computer_database.persistence.model.Company;
import com.excilys.computer_database.persistence.model.mapper.CompanyRowMapper;

/**
 * Implementation of CompanyDAO that is used to manipulate the db.
 * @author rlarroque
 */
@Repository
public class CompanyDAOImpl implements CompanyDAO {

    @Autowired
    DataSource dataSource;

    @Override
    public List<Company> getAll() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Company> companies = jdbcTemplate.query(QueryBuilder.getCompanyQuery(), new CompanyRowMapper());

        return companies;
    }

    // TODO test it
    @Override
    public Company get(long id) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        Company company = jdbcTemplate.queryForObject(QueryBuilder.getCompanyQuery(id),
                                                      new Long[] { id },
                                                      new CompanyRowMapper());

        return company;
    }

    @Override
    public Company get(String name) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        Company company = jdbcTemplate.queryForObject(QueryBuilder.getCompanyQuery(name),
                                                      new String[] { name },
                                                      new CompanyRowMapper());

        return company;
    }

    @Override
    public void delete(long id) {
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(QueryBuilder.deleteCompanyQuery(id));
    }
}
