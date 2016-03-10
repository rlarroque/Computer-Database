package com.excilys.computer_database.persistence.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.computer_database.persistence.dao.CompanyDAO;
import com.excilys.computer_database.persistence.dao.utils.QueryBuilder;
import com.excilys.computer_database.persistence.model.Company;
import com.excilys.computer_database.persistence.model.mapper.CompanyMapper;

/**
 * Implementation of CompanyDAO that is used to manipulate the db.
 * @author rlarroque
 */
@Repository
public class CompanyDAOImpl implements CompanyDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Company> getAll() {

        List<Company> companies = jdbcTemplate.query(QueryBuilder.getCompanyQuery(), new CompanyMapper());

        return companies;
    }

    @Override
    public Company get(long id) {

        Company company = jdbcTemplate.queryForObject(QueryBuilder.getCompanyQuery(id),
                                                      new Long[] { id },
                                                      new CompanyMapper());

        return company;
    }

    @Override
    public Company get(String name) {

        Company company = jdbcTemplate.queryForObject(QueryBuilder.getCompanyQuery(name),
                                                      new String[] { name },
                                                      new CompanyMapper());

        return company;
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(QueryBuilder.deleteCompanyQuery(id));
    }
}
