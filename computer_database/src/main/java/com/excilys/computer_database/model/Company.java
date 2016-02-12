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
	
	public Company(String name) {
		this.name = name;
	}

	public Company() {
	}

	@Override
	public String toString() {

		return "Company [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
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

		if (name == null) {

			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}

		return true;
	}
}