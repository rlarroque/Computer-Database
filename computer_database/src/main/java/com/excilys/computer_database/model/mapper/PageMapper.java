package com.excilys.computer_database.model.mapper;

import com.excilys.computer_database.dto.PageDTO;
import com.excilys.computer_database.dto.validator.PageDTOValidator;
import com.excilys.computer_database.model.Page;
import com.excilys.computer_database.model.validator.PageValidator;

/**
 * Mapper used to convert a page into DTO. 
 * @author rlarroque
 *
 */
public interface PageMapper {
	
	public static Page toPage(PageDTO dto) {
		
		PageDTOValidator.validate(dto);
		
		Page page = new Page(dto.getPageNumber(), dto.getOffset());
		page.setComputers(ComputerMapper.toComputer(dto.getComputers()));
		page.setTotalComputer(dto.getTotalComputer());
		
		return page;		
	}
	
	public static PageDTO toDTO(Page page) {
		
		PageValidator.validate(page);
		
		PageDTO dto = new PageDTO(page.getPageNumber(), page.getOffset());
		dto.setComputers(ComputerMapper.toDTO(page.getComputers()));
		dto.setTotalComputer(page.getTotalComputer());
		
		return dto;
	}
}
