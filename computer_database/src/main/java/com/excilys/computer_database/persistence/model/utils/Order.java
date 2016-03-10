package com.excilys.computer_database.persistence.model.utils;

/**
 * Data about how a page is ordered.
 * @author rlarroque
 */
public class Order {

    private OrderType type;
    private OrderColumn col;

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public OrderColumn getCol() {
        return col;
    }

    public void setCol(OrderColumn col) {
        this.col = col;
    }

    /**
     * Constructor.
     * @param type type
     * @param col column
     */
    public Order(OrderType type, OrderColumn col) {
        this.type = type;
        this.col = col;
    }

    @Override
    public String toString() {
        return "Order [type=" + type + ", col=" + col + "]";
    }

}
