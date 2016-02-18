package com.excilys.computer_database.dto.validator;

import java.time.LocalDate;
import java.util.List;

import com.excilys.computer_database.dto.ComputerDTO;
import com.excilys.computer_database.exception.IntegrityException;

public interface ComputerDTOValidator {

	public static void validate(ComputerDTO dto) throws IntegrityException {

		if (dto == null) {
			throw new IntegrityException("The computer is null.");
		}

		if (dto.getName() == null || "".equals(dto.getName())) {
			throw new IntegrityException("The computer's name is null.");
		}

		if (dto.getDiscontinuedDate() != null && dto.getIntroducedDate() == null) {
			throw new IntegrityException("Discontinued date cannot exist if there is no introducing date for computer " + dto.getName());
		}
		
		//check if discontinued date isn't before introduced one
		if (dto.getDiscontinuedDate() != null && dto.getIntroducedDate() != null
				&& !"".equals(dto.getDiscontinuedDate()) && !"".equals(dto.getIntroducedDate())
				&& LocalDate.parse(dto.getDiscontinuedDate()).isBefore(LocalDate.parse(dto.getIntroducedDate()))) {

			throw new IntegrityException("Discontinued date cannot be earlier than introducing datefor computer " + dto.getName());
		}
	}
	
	public static void validate(List<ComputerDTO> list) throws IntegrityException {
		for (ComputerDTO dto : list) {
			validate(dto);
		}
	}
}
