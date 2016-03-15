package com.excilys.computer_database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.model.Company;
import com.excilys.computer_database.dto.validator.CompanyDTOValidator;
import com.excilys.computer_database.validator.CompanyValidator;
import com.excilys.computer_database.dto.model.CompanyDTO;

/**
 * Mapper used to convert a resultSet or DTO into a Company object.
 * @author rlarroque
 *
 */
@Component
public class CompanyMapper implements RowMapper<Company> {
    
    @Override
    public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
        Company company = new Company();
        
        company.setId(rs.getInt("id"));
        company.setName(rs.getString("name"));
        
        return company;
    }

    /**
     * Take a resultSet as parameter and return a Company.
     * @param rs resultSet that is returned from a SQL query
     * @return a company corresponding to the resultSet
     * @throws SQLException if a SQl exception occurred while reading the resultSet
     * @throws IntegrityException thrown if the integrity is not respected
     */
    public Company toCompany(ResultSet rs) throws SQLException, IntegrityException {
        Company company = new Company(rs.getLong("id"), rs.getString("name"));
        CompanyValidator.validate(company);

        return company;
    }

    /**
     * Used to map a CompanyDTO into a company.
     * @param dto dto to map
     * @return the comapny mapped
     * @throws IntegrityException thrown if the integrity is not respected
     */
    public Company toCompany(CompanyDTO dto) throws IntegrityException {
        CompanyDTOValidator.validate(dto);

        return new Company(dto.id, dto.name);
    }

    /**
     * Used to map a list of CompanyDTO into a list of companies.
     * @param dtoList list to map
     * @return the list of company mapped
     */
    public List<Company> toCompany(List<CompanyDTO> dtoList) throws SQLException {
        List<Company> companyList = new ArrayList<>();

        for (CompanyDTO dto : dtoList) {
            companyList.add(toCompany(dto));
        }

        return companyList;
    }
    
    /**
     * Used to map a Company into a CompanyDTO.
     * @param company to map
     * @return the dto mapped
     * @throws IntegrityException thrown if the integrity is not respected
     */
    public CompanyDTO toDTO(Company company) throws IntegrityException {
        CompanyValidator.validate(company);

        return new CompanyDTO(company.getId(), company.getName());
    }

    /**
     * Used to map a list of Companies into a list of companyDTO.
     * @param companyList to map
     * @return the list of dto mapped
     */
    public List<CompanyDTO> toDTO(List<Company> companyList) {
        List<CompanyDTO> companyDTOList = new ArrayList<>();

        for (Company company : companyList) {
            companyDTOList.add(toDTO(company));
        }

        return companyDTOList;
    }
}