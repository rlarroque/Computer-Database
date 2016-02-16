package com.excilys.computer_database.dto;

/**
 * Data Transfer Object used to bring information from the server to the view.
 * Only primitive types are allowed here.
 * @author excilys
 *
 */
public class ComputerDTO {

	public String name;
	public String introducedDate;
	public String introducedTime;
	public String discontinuedDate;
	public String discontinuedTime;
	public String companyName;
	public int companyId;

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
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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
				+ ", companyId=" + companyId + "]";
	}
}
