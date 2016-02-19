package com.excilys.computer_database.dto;

import java.util.List;

public class PageDTO {
	
	private int current_page;
	private int offset;
	private int total_computer;	
	private int start_page;
	private int end_page;
	private int total_page;
	private String order;
	private String filter;

	private List<ComputerDTO> computers;
	
	public int getStart_page() {
		return start_page;
	}

	public void setStart_page(int start_page) {
		this.start_page = start_page;
	}

	public int getEnd_page() {
		return end_page;
	}

	public void setEnd_page(int end_page) {
		this.end_page = end_page;
	}

	public int getTotal_page() {
		return total_page;
	}

	public void setTotal_page(int total_page) {
		this.total_page = total_page;
	}
	
	public int getCurrent_page() {
		return current_page;
	}
	
	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
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
	
	public int getTotal_computer() {
		return total_computer;
	}

	public void setTotal_computer(int total_computer) {
		this.total_computer = total_computer;
	}
	
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public PageDTO(int pageNumber, int offset, String order) {
		this.current_page = pageNumber;
		this.offset = offset;
		this.order = order;
	}
	
	public PageDTO() {
		
	}
	
}
