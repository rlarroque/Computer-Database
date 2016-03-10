/**
 * 
 */
package com.excilys.computer_database.persistence.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.computer_database.persistence.model.Company;
import com.excilys.computer_database.persistence.model.Computer;

/**
 * Class used to map row into computer
 * @author rlarroque
 */
public class ComputerRowMapper implements RowMapper<Computer> {

    @Override
    public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Computer computer= new Computer();

        computer.setId(rs.getInt("id"));
        computer.setName(rs.getString("computer.name"));
        
        if (rs.getTimestamp("introduced") != null) {
            computer.setIntroduced(rs.getTimestamp("introduced").toLocalDateTime().toLocalDate());
        }

        if (rs.getTimestamp("discontinued") != null) {
            computer.setDiscontinued(
                    rs.getTimestamp("discontinued").toLocalDateTime().toLocalDate());
        }
        
        computer.setCompany(new Company(rs.getLong("company.id"), rs.getString("company.name")));

        return computer;
    }
}
