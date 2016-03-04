/**
 * 
 */
package com.excilys.computer_database.persistence.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.computer_database.persistence.model.Company;

/**
 * @author rlarroque
 */
public class CompanyRowMapper implements RowMapper<Company> {

    @Override
    public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
        Company company = new Company();
        
        company.setId(rs.getInt("id"));
        company.setName(rs.getString("name"));
        
        return company;
    }
}
