package com.excilys.computer_database.persistence.model.mapper;

import javax.servlet.http.HttpServletRequest;

import com.excilys.computer_database.persistence.model.Page;
import com.excilys.computer_database.persistence.model.utils.Order;
import com.excilys.computer_database.persistence.model.utils.OrderColumn;
import com.excilys.computer_database.persistence.model.utils.OrderType;
import com.excilys.computer_database.persistence.model.validator.PageValidator;
import com.excilys.computer_database.webapp.dto.PageDTO;
import com.excilys.computer_database.webapp.dto.validator.PageDTOValidator;

/**
 * Mapper used to convert a page into DTO.
 * @author rlarroque
 *
 */
public interface PageMapper {

    /**
     * Map a dto into a page.
     * @param dto dto to map
     * @return the mapped page
     */
    static Page toPage(PageDTO dto) {

        PageDTOValidator.validate(dto);

        Page page = new Page(dto.getCurrentPage(), dto.getOffset(), dto.getFilter());
        page.setComputers(ComputerMapper.toComputer(dto.getComputers()));
        page.setTotalComputer(dto.getTotalComputer());
        page.setStartIndex((dto.getTotalPage() - 1) * dto.getOffset());

        System.out.println(page);

        return page;
    }
    
    /**
     * Used to map a servlet request into a page 
     * @param request request received
     * @return the mapped page
     */
    static Page toPage(HttpServletRequest request) {
       
        Page page = new Page();
        
        page.setPageNumber(Integer.parseInt(request.getParameter("page")));
        page.setOffset(Integer.parseInt(request.getParameter("offset")));
        page.setFilter(request.getParameter("filter"));

        if (request.getParameter("order") != null && !"".equals(request.getParameter("order"))) {

            if (request.getParameter("order_type") != null && !"".equals(request.getParameter("order_type"))) {
                page.setOrder(
                        new Order(OrderType.valueOf(request.getParameter("order_type")), OrderColumn.fromString(request.getParameter("order"))));
            } else {
                page.setOrder(new Order(OrderType.ASC, OrderColumn.fromString(request.getParameter("order"))));
            }
        } else {
            page.setOrder(new Order(OrderType.ASC, OrderColumn.ID));
        }
        
        return page;
    }

    /**
     * Map a page into a DTO.
     * @param page page to map
     * @return the mapped dto
     */
    static PageDTO toDTO(Page page) {

        PageValidator.validate(page);

        PageDTO dto = new PageDTO(page.getPageNumber(), page.getOffset());
        dto.setComputers(ComputerMapper.toDTO(page.getComputers()));
        dto.setTotalComputer(page.getTotalComputer());
        dto.setFilter(page.getFilter());
        if (page.getOrder() != null) {
            dto.setOrder(page.getOrder().getCol().toString());
        }

        return dto;
    }
    
}
