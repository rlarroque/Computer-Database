package com.excilys.computer_database.dto.validator;

import com.excilys.computer_database.dto.PageDTO;
import com.excilys.computer_database.exception.IntegrityException;

public interface PageDTOValidator {

	public static void validate(PageDTO dto) throws IntegrityException {
		
		if (dto == null) {
			throw new IntegrityException("The Page is null");
		}
		
		if (dto.getTotal_computer() < 0) {
			throw new IntegrityException("Total computer information is not valid");
		}

		if (dto.getCurrent_page() < 1) {
			throw new IntegrityException("Page number information is not valid");
		}
		
		if (!(dto.getOffset() == 10 || dto.getOffset() == 50 || dto.getOffset() == 100)) {
			throw new IntegrityException("Offset information is not valid");
		}
		
		if(dto.getTotal_page() < 0){
			throw new IntegrityException("Total page count is not valid");
		}
		
		if(dto.getStart_page() < 1){
			throw new IntegrityException("The start page is not valid");
		}
		
		if(dto.getEnd_page() > dto.getTotal_page()){
			throw new IntegrityException("The end page is not valid");
		}
		
		if(dto.getEnd_page() < dto.getStart_page()){
			throw new IntegrityException("End page cannot be before start page");
		}
	}
}