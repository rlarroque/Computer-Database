package com.excilys.computer_database.dto.model;

/**
 * @author rlarroque
 */
public class PageParams {

    private int page;
    private int offset;
    private String order;
    private String order_type;
    private String filter;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public PageParams(int page, int offset, String order, String order_type, String filter) {
        this.page = page;
        this.offset = offset;
        this.order = order;
        this.order_type = order_type;
        this.filter = filter;
    }
}
