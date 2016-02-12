package com.excilys.computer_database.dto;

public class CompanyDTO {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public CompanyDTO(String name){
		this.name = name;
	}
	
	public CompanyDTO(){
		
	}
}
