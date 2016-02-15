package com.excilys.computer_database.dto;

public class CompanyDTO {

	public String name;
	public int id;

	public CompanyDTO(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public CompanyDTO() {

	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CompanyDTO [name=" + name + ", id=" + id + "]";
	}
}
