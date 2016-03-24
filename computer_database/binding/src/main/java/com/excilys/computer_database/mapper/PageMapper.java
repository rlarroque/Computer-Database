package com.excilys.computer_database.mapper;

import com.excilys.computer_database.dto.model.PageDTO;
import com.excilys.computer_database.dto.model.PageParams;
import com.excilys.computer_database.model.Page;
import com.excilys.computer_database.model.utils.Order;
import com.excilys.computer_database.model.utils.OrderColumn;
import com.excilys.computer_database.model.utils.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Mapper used to convert a page into DTO.
 * @author rlarroque
 */
@Component
public class PageMapper {
    
    @Autowired
    private ComputerMapper computerMapper;

    /**
     * Map a dto into a page.
     * @param dto dto to map
     * @return the mapped page
     */
    public Page toPage(PageDTO dto) {

        Page page = new Page.PageBuilder()
                            .currentPage(dto.getCurrentPage())
                            .offset(dto.getOffset())
                            .startIndex((dto.getCurrentPage() - 1) * dto.getOffset())
                            .totalPage(1)
                            .order(new Order(OrderType.valueOf(dto.getOrder_type()), OrderColumn.fromString(dto.getOrder())))
                            .filter(dto.getFilter())
                            .computers(computerMapper.toComputer(dto.getComputers()))
                            .build();

        return page;
    }

    /**
     * Map a page into a DTO.
     * @param page page to map
     * @return the mapped dto
     */
    public PageDTO toDTO(Page page) {

        PageDTO dto = new PageDTO();

        dto.setTotalComputer(page.getComputers().size());
        dto.setCurrentPage(page.getCurrentPage());
        dto.setOffset(page.getOffset());
        dto.setFilter(page.getFilter());
        dto.setComputers(computerMapper.toDTO(page.getComputers()));

        if (page.getOrder() != null) {
            dto.setOrder(page.getOrder().getCol().toString());
        }

        return dto;
    }

    /**
     * Map a PageParams into a pagDTO
     * @param params params to map
     * @return the mapped dto
     */
    public PageDTO toDTO(PageParams params) {
        return new PageDTO(params.getPage(), params.getOffset(), params.getOrder(), params.getOrder_type(), params.getFilter());
    }
    
}
