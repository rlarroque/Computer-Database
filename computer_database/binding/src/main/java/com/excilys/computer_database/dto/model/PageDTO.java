package com.excilys.computer_database.dto.model;

import java.util.List;

/**
 * Page item that contains all the manfatory information to build a page.
 * @author rlarroque
 */
public class PageDTO {
    
    private int currentPage;
    private int offset; 
    private int totalComputer;
    private int startPage;
    private int endPage;
    private int totalPage;
    private String order;
    private String filter;

    private List<ComputerDTO> computers;

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

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

    public List<ComputerDTO> getComputers() {
        return computers;
    }

    public void setComputers(List<ComputerDTO> computers) {
        this.computers = computers;
    }

    public int getTotalComputer() {
        return totalComputer;
    }

    public void setTotalComputer(int totalComputer) {
        this.totalComputer = totalComputer;
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

    /**
     * Constructor.
     * @param pageNumber page number
     * @param offset offset
     */
    public PageDTO(int pageNumber, int offset) {
        this.currentPage = pageNumber;
        this.offset = offset;
    }

    /**
     * Default constructor.
     */
    public PageDTO() {

    }

}
