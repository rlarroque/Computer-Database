package com.excilys.computer_database.persistence.model;

import java.util.List;

import com.excilys.computer_database.persistence.model.utils.Order;

public class Page {
	
	private int pageNumber;
	private int offset;
	private int totalComputer;
	private int startIndex;
	private int totalPage;
	private Order order;
	private String filter;
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

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
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
	
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Page(int pageNumber, int offset, String filter) {
		this.pageNumber = pageNumber;
		this.offset = offset;
		this.filter = filter;
	}

	public Page(int pageNumber, int offset) {
		this.pageNumber = pageNumber;
		this.offset = offset;
	}
	
	public Page() {
		
	}
	
}
