package com.excilys.computer_database.dto.validator;

import com.excilys.computer_database.dto.PageDTO;
import com.excilys.computer_database.exception.IntegrityException;

public interface PageDTOValidator {

	public static void validate(PageDTO dto) throws IntegrityException {

		if (dto == null) {
			throw new IntegrityException("The Page is null");
		}

		if (dto.getPageNumber() < 1) {
			throw new IntegrityException("Page number information is not valid");
		}
		
		if (!(dto.getOffset() == 10 || dto.getOffset() == 50 || dto.getOffset() == 100)) {
			throw new IntegrityException("Offset information is not valid");
		}
	}
}