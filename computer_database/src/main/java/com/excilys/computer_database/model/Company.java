package com.excilys.computer_database.model;

/**
 * This class describe a company with all its parameters. id, and name. This is
 * a simple POJO.
 * 
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
		
		return "Company [id=" + id + 
			   ", name=" + name + 
			   "]";
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		Company other = (Company) obj;

		if (id != other.id) {
			return false;
		}
		
		return true;
	}

}
