package com.excilys.computer_database.controller.utils;

import com.excilys.computer_database.dto.model.PageDTO;

/**
 * Classe used to construct pageDTO base on its curent information. For example the number of computer and the offset are needed to determine the
 * total number of page.
 * @author rlarroque
 */
public class PageConstructor {

    private static final int MAX_PAGES_DISPLAYED = 5;

    /**
     * Construct the page.
     * @param page page to construct
     */
    public static void construct(PageDTO page) {

        page.setTotalPage((page.getTotalComputer() / page.getOffset()));

        if ((page.getTotalComputer() % page.getOffset()) != 0) {
            page.setTotalPage(page.getTotalPage() + 1);
        }

        page.setStartPage(Math.max(page.getCurrentPage() - MAX_PAGES_DISPLAYED / 2, 1));
        page.setEndPage(MAX_PAGES_DISPLAYED + page.getStartPage());

        if (page.getEndPage() > page.getTotalPage()) {

            int diff = page.getEndPage() - page.getTotalPage();
            page.setStartPage(page.getStartPage() - (diff - 1));

            if (page.getStartPage() < 1) {
                page.setStartPage(1);
            }

            page.setEndPage(page.getTotalPage());
        }
    }
}
