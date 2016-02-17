package com.excilys.computer_database.model.validator;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.model.Company;

public interface CompanyValidator {

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
}
