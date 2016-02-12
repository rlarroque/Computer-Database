package com.excilys.computer_database.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.computer_database.dto.CompanyDTO;
import com.excilys.computer_database.model.Company;

/**
 * Mapper used to convert a resultSet into a Company object.
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
	public static Company map(ResultSet rs) throws SQLException {
		
		Company company = new Company();
		company.setId(rs.getInt("id"));
		company.setName(rs.getString("name"));
		
		return company;
	}
	
	public static Company dtoToCompany(CompanyDTO dto) {
		
		if(dto == null){
			return null;
		} else if(dto.getName() == null || dto.getName() == ""){
			return null;
		} else{
			return new Company(dto.getName());
		}
	}

	public static CompanyDTO companyToDTO(Company company) {
		
		if(company == null){
			return null;			
		} else if( company.getName() == "" || company.getName() == null){
			return new CompanyDTO("");
		} else{
			return new CompanyDTO(company.getName());
		}
	}
}