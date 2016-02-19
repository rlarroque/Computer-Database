package com.excilys.computer_database.servlet.utils;

import com.excilys.computer_database.dto.PageDTO;

public class PageConstructor {

	private static final int MAX_PAGES_DISPLAYED = 5;

	public static void construct(PageDTO page) {

		page.setTotal_page((page.getTotal_computer() / page.getOffset()));

		if ((page.getTotal_computer() % page.getOffset()) != 0) {
			page.setTotal_page(page.getTotal_page() + 1);
		}

		page.setStart_page(Math.max(page.getCurrent_page() - MAX_PAGES_DISPLAYED / 2, 1));
		page.setEnd_page(MAX_PAGES_DISPLAYED + page.getStart_page());

		if (page.getEnd_page() > page.getTotal_page()) {

			int diff = page.getEnd_page() - page.getTotal_page();
			page.setStart_page(page.getStart_page() - (diff - 1));

			if (page.getStart_page() < 1) {
				page.setStart_page(1);
			}

			page.setEnd_page(page.getTotal_page());
		}
	}
}
