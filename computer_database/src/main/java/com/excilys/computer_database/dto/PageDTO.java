package com.excilys.computer_database.dto;

import java.util.List;

public class PageDTO {
	
	private int pageNumber;
	private int offset;
	private List<ComputerDTO> computers;
	
	public int getPageNumber() {
		return pageNumber;
	}
	
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public int getOffset() {
		return offset;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public List<ComputerDTO> getComputers() {
		return computers;
	}

	public void setComputers(List<ComputerDTO> computers) {
		this.computers = computers;
	}

	public PageDTO(int pageNumber, int offset) {
		this.pageNumber = pageNumber;
		this.offset = offset;
	}
	
	public PageDTO() {
		
	}

}
