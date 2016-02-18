package com.excilys.computer_database.persistence.model.validator;

import java.util.List;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.persistence.model.Company;

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
	
	public static void validate(List<Company> list) throws IntegrityException {
		for (Company company: list) {
			validate(company);
		}
	}
}
