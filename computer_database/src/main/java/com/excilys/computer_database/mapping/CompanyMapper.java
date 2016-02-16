package com.excilys.computer_database.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computer_database.dto.CompanyDTO;
import com.excilys.computer_database.model.Company;

/**
 * Mapper used to convert a resultSet or DTO into a Company object.
 * @author excilys
 *
 */
public interface CompanyMapper {
	
	/**
	 * Take a resultSet as parameter and return a Company.
	 * @param rs resultSet that is returned from a SQL query
	 * @return a company corresponding to the resultSet
	 * @throws SQLException if a SQl exception occurred while reading the resultSet
	 */
	public static Company resultSetToCompany(ResultSet rs) throws SQLException {
		
		Company company = new Company();
		company.setId(rs.getInt("id"));
		company.setName(rs.getString("name"));
		
		return company;
	}
	
	/**
	 * Used to map a CompanyDTO into a company
	 * @param dto dto to map
	 * @return the comapny mapped
	 */
	public static Company dtoToCompany(CompanyDTO dto) {
		
		if(dto == null){
			return null;
		} else if(dto.name == null || dto.name == "" || dto.id == 0){
			return null;
		} else{
			return new Company(dto.id, dto.name);
		}
	}
	
	/**
	 * Used to map a list of CompanyDTO into a list of companies
	 * @param dtoList list to map
	 * @return the list of company mapped
	 */
	public static List<Company> listDTOToListCompany(List<CompanyDTO> dtoList) {
		List<Company> companyList = new ArrayList<>();
		
		for (CompanyDTO dto: dtoList) {
			companyList.add(dtoToCompany(dto));
		}
		
		return companyList;
	}

	/**
	 * Used to map a Company into a CompanyDTO
	 * @param company to map
	 * @return the dto mapped
	 */
	public static CompanyDTO companyToDTO(Company company) {
		
		if(company == null){
			return null;			
		} else if(company.getId() == null){
			return null;
		} else{
			if(company.getName() == "" || company.getName() == null){
				return new CompanyDTO(company.getId(), "");
			} else{
				return new CompanyDTO(company.getId(), company.getName());				
			}
		}
	}
	
	/**
	 * Used to map a list of Companies into a list of companyDTO
	 * @param companyList to map
	 * @return the list of dto mapped
	 */
	public static List<CompanyDTO> listCompanyToListDTO(List<Company> companyList) {
		List<CompanyDTO> companyDTOList = new ArrayList<>();
		
		for (Company company: companyList) {
			companyDTOList.add(companyToDTO(company));
		}
		
		return companyDTOList;
	}
}