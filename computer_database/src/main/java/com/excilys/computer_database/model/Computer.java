package com.excilys.computer_database.model;

import java.time.LocalDateTime;

/**
 * This class describe a computer with all its parameters. id, name, introduced date, discontinued date,
 * and company id. This is a simple POJO.
 * @author excilys
 *
 */
public class Computer {
	
	private int id;
	private String name;
	private LocalDateTime introduced;
	private LocalDateTime discontinued;
	private Company company;
	
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
	
	public LocalDateTime getIntroduced() {
		return introduced;
	}
	
	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
	}
	
	public LocalDateTime getDiscontinued() {
		return discontinued;
	}
	
	public void setDiscontinued(LocalDateTime discontinued) {
		this.discontinued = discontinued;
	}
	
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}	
	
	public Computer(int id, String name, LocalDateTime introduced, LocalDateTime discontinued, Company company) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}
	
	public Computer(String name) {
		this.name = name;
	}

	public Computer() {
	}
	
	@Override
	public String toString() {
		
		return "Computer [id=" + id + 
			   ", name=" + name + 
			   ", introduced=" + introduced + 
			   ", discontinued=" + discontinued + 
			   ", company=" + company + 
			   "]";
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;			
		}
		
		if (obj == null){
			return false;			
		}
		
		if (getClass() != obj.getClass()){
			return false;			
		}
		
		Computer other = (Computer) obj;
		
		if (id != other.id){
			return false;			
		}
		
		return true;
	}
}
