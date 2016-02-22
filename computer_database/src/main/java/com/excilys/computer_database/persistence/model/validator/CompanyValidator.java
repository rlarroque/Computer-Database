package com.excilys.computer_database.persistence.model.validator;

import java.util.List;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.persistence.model.Company;

/**
 * Interface with static methods used to validate the integrity of a company
 * @author rlarroque
 */
public interface CompanyValidator {

	/**
	 * Validate the integrity of a company
	 * @param dto company to validate
	 * @throws IntegrityException thrown if the integrity is not respected
	 */
	public static void validate(Company company) throws IntegrityException{
		
		if(company == null){
			throw new IntegrityException("The company is null.");
		}
		
		if(company.getId() == null || company.getId() < 1){
			throw new IntegrityException("The company's id is not valid.");
		}
		
		if(company.getName() == null || "".equals(company.getName())){
			throw new IntegrityException("The company's name is not valid.");
		}
	}
	
	/**
	 * Validate the integrity of a list of companys
	 * @param list list to validate
	 * @throws IntegrityException thrown if the integrity is not respected
	 */
	public static void validate(List<Company> list) throws IntegrityException {
		for (Company company: list) {
			validate(company);
		}
	}
}
