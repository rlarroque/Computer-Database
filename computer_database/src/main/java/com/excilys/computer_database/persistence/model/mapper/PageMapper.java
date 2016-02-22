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
	
	/**
	 * Map a dto into a page
	 * @param dto dto to map
	 * @return the mapped page
	 */
	public static Page toPage(PageDTO dto) {
		
		PageDTOValidator.validate(dto);
		
		Page page = new Page(dto.getCurrent_page(), dto.getOffset());
		page.setComputers(ComputerMapper.toComputer(dto.getComputers()));
		page.setTotalComputer(dto.getTotal_computer());
		page.setStartIndex((dto.getTotal_page() - 1) * dto.getOffset());
		page.setFilter(dto.getFilter());
		
		System.out.println(page);
		
		return page;		
	}
	
	/**
	 * Map a page into a DTO
	 * @param page page to map
	 * @return the mapped dto
	 */
	public static PageDTO toDTO(Page page) {
		
		PageValidator.validate(page);
		
		PageDTO dto = new PageDTO(page.getPageNumber(), page.getOffset());
		dto.setComputers(ComputerMapper.toDTO(page.getComputers()));
		dto.setTotal_computer(page.getTotalComputer());
		dto.setFilter(page.getFilter());
		dto.setOrder(page.getOrder().getCol().toString());
		
		return dto;
	}
}
