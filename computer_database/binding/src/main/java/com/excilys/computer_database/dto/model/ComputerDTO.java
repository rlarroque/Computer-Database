package com.excilys.computer_database.dto.model;

import org.hibernate.validator.constraints.NotBlank;

import com.excilys.computer_database.dto.validator.annotation.Computer;
import com.excilys.computer_database.dto.validator.annotation.Date;

/**
 * Data Transfer Object used to bring information from the server to the view. Only primitive types
 * are allowed here.
 * @author rlarroque
 *
 */
@Computer
public class ComputerDTO {

    public long id;
    @NotBlank(message = "Please enter a computer name.")
    public String name;
    @Date
    public String introducedDate;
    @Date
    public String discontinuedDate;
    public String companyName;
    public long companyId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getDiscontinuedDate() {
        return discontinuedDate;
    }

    public void setDiscontinuedDate(String discontinuedDate) {
        this.discontinuedDate = discontinuedDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public ComputerDTO(long id, String name, String introducedDate, String discontinuedDate, String companyName, long companyId) {
        this.id = id;
        this.name = name;
        this.introducedDate = introducedDate;
        this.discontinuedDate = discontinuedDate;
        this.companyName = companyName;
        this.companyId = companyId;
    }

    public ComputerDTO() {
    }

    @Override
    public String toString() {
        return "ComputerDTO [id=" + id + ", name=" + name + ", introducedDate=" + introducedDate
                + ", discontinuedDate=" + discontinuedDate + ", companyId=" + companyId + "]";
    }
}
