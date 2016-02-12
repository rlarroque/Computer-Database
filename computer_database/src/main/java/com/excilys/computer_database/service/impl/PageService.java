package com.excilys.computer_database.service.impl;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.model.Page;

/**
 * This class is used in order to perform a pagination on the list of computers.
 * According to the offset chosen is it possible to change page, retrieve the
 * number of the first and last computer, and so on so forth.
 * 
 * @author excilys
 *
 */
public class PageService {

	private static final int DEFAULT_OFFSET = 10;

	private static PageService instance;
	private static Page page;

	public static PageService getInstance() {
		if (instance == null) {
			instance = new PageService();
		}

		return instance;
	}

	private PageService() {
		page = new Page(1, DEFAULT_OFFSET);
	}
	
	/**
	 * Get the current page offset.
	 */
	public int getOffset() {
		return page.getOffset();
	}

	/**
	 * Set a new offset for the page.
	 * @param offset new offset
	 */
	public void setOffset(int offset) {
		page.setOffset(offset);
	}
	
	/**
	 * Set the new current page.
	 * @param offset new current page
	 */
	public void setCurrentPage(int currentPage) {
		page.setCurrentPage(currentPage);
	}

	/**
	 * Go to the next page.
	 */
	public void nextPage() {
		page.setCurrentPage(page.getCurrentPage() + 1);
	}

	/**
	 * Go to the previous page.
	 */
	public void previousPage() throws IntegrityException {

		if (page.getCurrentPage() == 1) {
			throw new IntegrityException("Cannot go to a page inferior to 1");
		} else {
			page.setCurrentPage(page.getCurrentPage() - 1);
		}
	}

	/**
	 * Number of the first computer of the page.
	 * @return the position of the first item to retrieve
	 */
	public int startIndex() {
		return (page.getCurrentPage() - 1) * page.getOffset();
	}
}
