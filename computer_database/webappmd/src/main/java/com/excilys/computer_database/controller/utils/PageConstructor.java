package com.excilys.computer_database.controller.utils;

import com.excilys.computer_database.dto.PageDTO;
import com.excilys.computer_database.mapper.ComputerMapper;
import com.excilys.computer_database.mapper.PageMapper;
import com.excilys.computer_database.model.Page;
import com.excilys.computer_database.services.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class used to construct pageDTO base on its current information. For example the number of computer and the offset are needed to determine the
 * total number of page.
 * @author rlarroque
 */
@Component
public class PageConstructor {

    private static final int MAX_PAGES_DISPLAYED = 5;

    @Autowired
    private ComputerService computerService;

    @Autowired
    private ComputerMapper computerMapper;

    @Autowired
    private PageMapper pageMapper;

    /**
     * Construct the pageDto.
     * @param pageDto pageDto to construct
     * @return constructedPage the page constructed
     */

    public PageDTO construct(PageDTO pageDto) {

        PageDTO constructedPage = new PageDTO();

        // Retrieve pageDto information.
        constructedPage.setCurrentPage(pageDto.getCurrentPage());
        constructedPage.setOffset(pageDto.getOffset());
        constructedPage.setFilter(pageDto.getFilter());
        constructedPage.setOrder(pageDto.getOrder());
        constructedPage.setOrder_type(pageDto.getOrder_type());

        // Get the list of computer via a page.
        Page page = pageMapper.toPage(constructedPage);

        // Get the total count of computers and then find out total number of pages.
        constructedPage.setComputers(computerMapper.toDTO(computerService.getPage(page)));
        constructedPage.setTotalComputer(computerService.count(page));
        constructedPage.setTotalPage(constructedPage.getTotalComputer() / constructedPage.getOffset());

        if (constructedPage.getTotalComputer() % constructedPage.getOffset() != 0) {
            constructedPage.setTotalPage(constructedPage.getTotalPage() + 1);
        }

        // Calculate the starting page and the ending page.
        constructedPage.setStartPage(Math.max(constructedPage.getCurrentPage() - MAX_PAGES_DISPLAYED / 2, 1));
        constructedPage.setEndPage(MAX_PAGES_DISPLAYED + constructedPage.getStartPage());

        if (constructedPage.getEndPage() > constructedPage.getTotalPage()) {

            int diff = constructedPage.getEndPage() - constructedPage.getTotalPage();
            constructedPage.setStartPage(constructedPage.getStartPage() - (diff - 1));

            if (constructedPage.getStartPage() < 1) {
                constructedPage.setStartPage(1);
            }

            constructedPage.setEndPage(constructedPage.getTotalPage());
        }

        return constructedPage;
    }
}
