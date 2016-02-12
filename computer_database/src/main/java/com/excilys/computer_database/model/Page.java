package com.excilys.computer_database.model;

public class Page {
	
	private int currentPage;
	private int offset;
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public Page(int currentPage, int offset){
		this.currentPage = currentPage;
		this.offset = offset;
	}
}
