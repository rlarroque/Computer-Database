package com.excilys.computerDatabase.model;

/**
 * This class describe a company with all its parameters. id, and name. This is a simple POJO.
 * @author excilys
 *
 */
public class Company {
	
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Company(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Company() {
	}
	
	@Override
	public String toString() {
		return String.format("Company id: %d, name: %s.", this.id, this.name);
	}
}
