package com.excilys.computer_database.persistence.model;

import java.util.List;

public class Page {
	
	private int pageNumber;
	private int offset;
	private int totalComputer;
	private List<Computer> computers;
	
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

	public int getTotalComputer() {
		return totalComputer;
	}

	public void setTotalComputer(int totalComputer) {
		this.totalComputer = totalComputer;
	}
	
	public List<Computer> getComputers() {
		return computers;
	}

	public void setComputers(List<Computer> computers) {
		this.computers = computers;
	}

	public Page(int pageNumber, int offset) {
		this.pageNumber = pageNumber;
		this.offset = offset;
	}
	
	public Page() {
		
	}
}
