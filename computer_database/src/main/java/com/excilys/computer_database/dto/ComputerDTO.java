package com.excilys.computer_database.dto;

public class ComputerDTO {

	public String name;
	public String introducedDate;
	public String introducedTime;
	public String discontinuedDate;
	public String discontinuedTime;
	public String company_name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroducedDate() {
		return introducedDate;
	}

	public void setIntroducedDate(String introducedDate) {
		this.introducedDate = introducedDate;
	}

	public String getIntroducedTime() {
		return introducedTime;
	}

	public void setIntroducedTime(String introducedtime) {
		this.introducedTime = introducedtime;
	}

	public String getDiscontinuedDate() {
		return discontinuedDate;
	}

	public void setDiscontinuedDate(String discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}

	public String getDiscontinuedTime() {
		return discontinuedTime;
	}

	public void setDiscontinuedTime(String discontinuedTime) {
		this.discontinuedTime = discontinuedTime;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public ComputerDTO() {

	}

	public ComputerDTO(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ComputerDTO [name=" + name + ", introducedDate=" + introducedDate + ", introducedTime=" + introducedTime
				+ ", discontinuedDate=" + discontinuedDate + ", discontinuedTime=" + discontinuedTime
				+ ", company_name=" + company_name + "]";
	}
}
