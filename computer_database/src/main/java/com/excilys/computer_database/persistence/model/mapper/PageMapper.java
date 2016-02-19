package com.excilys.computer_database.persistence.model.mapper;

import com.excilys.computer_database.dto.PageDTO;
import com.excilys.computer_database.dto.validator.PageDTOValidator;
import com.excilys.computer_database.persistence.model.Page;
import com.excilys.computer_database.persistence.model.validator.PageValidator;

/**
 * Mapper used to convert a page into DTO. 
 * @author rlarroque
 *
 */
public interface PageMapper {
	
	public static Page toPage(PageDTO dto) {
		
		PageDTOValidator.validate(dto);
		
		Page page = new Page(dto.getCurrent_page(), dto.getOffset(), dto.getOrder());
		page.setComputers(ComputerMapper.toComputer(dto.getComputers()));
		page.setTotalComputer(dto.getTotal_computer());
		page.setStartIndex((dto.getTotal_page() - 1) * dto.getOffset());
		page.setFilter(dto.getFilter());
		
		System.out.println(page);
		
		return page;		
	}
	
	public static PageDTO toDTO(Page page) {
		
		PageValidator.validate(page);
		
		PageDTO dto = new PageDTO(page.getPageNumber(), page.getOffset(), page.getOrder());
		dto.setComputers(ComputerMapper.toDTO(page.getComputers()));
		dto.setTotal_computer(page.getTotalComputer());
		dto.setFilter(page.getFilter());
		
		return dto;
	}
}
