package com.excilys.computer_database.model;

import java.util.List;

import com.excilys.computer_database.model.utils.Order;

/**
 * Page model that store information of a page from the point of view of the database.
 * @author rlarroque
 */
public class Page {

    private int currentPage;
    private int offset;
    private int startIndex;
    private int totalPage;
    private Order order;
    private String filter;
    private List<Computer> computers;

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

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
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

    // Pattern builder
    public Page(PageBuilder builder) {
        this.currentPage = builder.currentPage;
        this.offset = builder.offset;
        this.startIndex = builder.startIndex;
        this.totalPage = builder.totalPage;
        this.order = builder.order;
        this.filter = builder.filter;
        this.computers = builder.computers;
    }

    public static class PageBuilder {
        private int currentPage;
        private int offset;
        private int startIndex;
        private int totalPage;
        private Order order;
        private String filter;
        private List<Computer> computers;

        public PageBuilder currentPage(int currentPage) {
            this.currentPage = currentPage;
            return this;
        }

        public PageBuilder offset(int offset) {
            this.offset = offset;
            return this;
        }

        public PageBuilder startIndex(int startIndex) {
            this.startIndex = startIndex;
            return this;
        }

        public PageBuilder totalPage(int totalPage) {
            this.totalPage = totalPage;
            return this;
        }

        public PageBuilder order(Order order) {
            this.order = order;
            return this;
        }

        public PageBuilder filter(String filter) {
            this.filter = filter;
            return this;
        }

        public PageBuilder computers(List<Computer> computers) {
            this.computers = computers;
            return this;
        }

        public Page build() {
            return new Page(this);
        }
    }
}
