package com.excilys.computer_database.persistence.model.utils;

public enum OrderColumn{
	ID ("id"),
	NAME ("name"),
	INTRO ("introduced"),
	DISC ("discontinued"),
	COMPANY ("company_id");
	
	private final String column;       

    private OrderColumn(String s) {
    	column = s;
    }

    public boolean equalsColumn(String otherColumn) {
        return (otherColumn == null) ? false : column.equals(otherColumn);
    }

    public String toString() {
       return this.column;
    }
    
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