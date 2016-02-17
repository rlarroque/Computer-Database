package com.excilys.computer_database.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computer_database.dto.CompanyDTO;
import com.excilys.computer_database.dto.validator.CompanyDTOValidator;
import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.model.Company;
import com.excilys.computer_database.model.validator.CompanyValidator;

/**
 * Mapper used to convert a resultSet or DTO into a Company object.
 * @author rlarroque
 *
 */
public interface CompanyMapper {
	
	/**
	 * Take a resultSet as parameter and return a Company.
	 * @param rs resultSet that is returned from a SQL query
	 * @return a company corresponding to the resultSet
	 * @throws SQLException if a SQl exception occurred while reading the resultSet
	 */
	public static Company toCompany(ResultSet rs) throws SQLException, IntegrityException {
		Company company = new Company(rs.getInt("id"),rs.getString("name"));
		CompanyValidator.validate(company);
		
		return company;
	}
	
	/**
	 * Used to map a CompanyDTO into a company
	 * @param dto dto to map
	 * @return the comapny mapped
	 */
	public static Company toCompany(CompanyDTO dto) throws IntegrityException {
		CompanyDTOValidator.validate(dto);
		
		return new Company(dto.id, dto.name);
	}
	
	/**
	 * Used to map a list of CompanyDTO into a list of companies
	 * @param dtoList list to map
	 * @return the list of company mapped
	 */
	public static List<Company> toCompany(List<CompanyDTO> dtoList) {
		List<Company> companyList = new ArrayList<>();
		
		for (CompanyDTO dto: dtoList) {
			companyList.add(toCompany(dto));
		}
		
		return companyList;
	}

	/**
	 * Used to map a Company into a CompanyDTO
	 * @param company to map
	 * @return the dto mapped
	 */
	public static CompanyDTO toDTO(Company company) throws IntegrityException {
		CompanyValidator.validate(company);
		
		return new CompanyDTO(company.getId(), company.getName());	
	}
	
	/**
	 * Used to map a list of Companies into a list of companyDTO
	 * @param companyList to map
	 * @return the list of dto mapped
	 */
	public static List<CompanyDTO> toDTO(List<Company> companyList) {
		List<CompanyDTO> companyDTOList = new ArrayList<>();
		
		for (Company company: companyList) {
			companyDTOList.add(toDTO(company));
		}
		
		return companyDTOList;
	}
}