package com.excilys.computer_database.persistence.model.utils;

/**
 * Enumeration of the possible column ordering in a page.
 * @author rlarroque
 */
public enum OrderColumn {
    ID("id"), NAME("name"), INTRO("introduced"), DISC("discontinued"), COMPANY("company_id");

    private final String column;

    /**
     * Setter.
     * @param s string to set
     */
    OrderColumn(String s) {
        column = s;
    }

    /**
     * Define if two orderColumn are equals.
     * @param otherColumn orderColumn to compare
     * @return true if equals, else false
     */
    public boolean equalsColumn(String otherColumn) {
        return (otherColumn == null) ? false : column.equals(otherColumn);
    }

    @Override
    public String toString() {
        return this.column;
    }

    /**
     * Get the correct enum according to the passed string.
     * @param column string to convert
     * @return the order colmun
     */
    public static OrderColumn fromString(String column) {
        if (column != null) {
            for (OrderColumn c : OrderColumn.values()) {
                if (column.equalsIgnoreCase(c.column)) {
                    return c;
                }
            }
        }
        return null;
    }
}