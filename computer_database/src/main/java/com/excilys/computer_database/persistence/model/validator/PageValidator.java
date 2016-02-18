package com.excilys.computer_database.persistence.model.validator;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.persistence.model.Page;

public interface PageValidator {

	public static void validate(Page page) throws IntegrityException {
		
		if (page == null) {
			throw new IntegrityException("The Page is null");
		}
		
		if (page.getTotalComputer() < 0) {
			throw new IntegrityException("Total computer information is not valid");
		}

		if (page.getPageNumber() < 1) {
			throw new IntegrityException("Page number information is not valid");
		}
		
		if (!(page.getOffset() == 10 || page.getOffset() == 50 || page.getOffset() == 100)) {
			throw new IntegrityException("Offset information is not valid");
		}
	}
}
